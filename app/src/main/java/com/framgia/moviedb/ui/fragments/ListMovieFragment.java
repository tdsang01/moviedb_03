package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.ListPrimaryMovieInfo;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.data.model.RetrofitClient;
import com.framgia.moviedb.ui.adapter.MoviePopularAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMovieFragment extends Fragment {
    private final String TAG = getClass().getSimpleName();
    private RecyclerView mRecyclerView;
    private List<PrimaryMovieInfo> mListPrimaryMovieInfo;
    private int mNumPage = 1;
    private String mTypeOfListMovie = ManagerConstant.Param.PARAM_TYPE_MOVIE_POPULAR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_movie, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_movie_popular);
        Bundle bundle = getArguments();
        if(bundle != null){
            mTypeOfListMovie = bundle.getString(ManagerConstant.ARGUMENT_TYPE_OF_LIST_MOVIE);
        }
        loadPopularMovie(mTypeOfListMovie);
        return view;
    }

    public static  ListMovieFragment newInstance(String argumentTypeOfListMovie){
        Fragment fragment = new ListMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putString(
            ManagerConstant.ARGUMENT_TYPE_OF_LIST_MOVIE, argumentTypeOfListMovie);
        fragment.setArguments(bundle);
        return (ListMovieFragment) fragment;
    }

    public void loadPopularMovie(String type) {
        Map<String, String> params = new HashMap<>();
        params.put(ManagerConstant.Param.PARAM_PAGE, String.valueOf(mNumPage));
        params.put(ManagerConstant.Param.PARAM_API_KEY, BuildConfig.API_KEY);
        params.put(ManagerConstant.Param.PARAM_LANGUAGE, ManagerConstant.LANGUAGE);
        RetrofitClient.getListMoviePopular(type, params, new Callback<ListPrimaryMovieInfo>() {
            @Override
            public void onResponse(Call<ListPrimaryMovieInfo> call,
                                   Response<ListPrimaryMovieInfo> response) {
                if (response == null) {
                    Toast.makeText(
                        getContext(),
                        ManagerConstant.MessengerManager.MSG_LOAD,
                        Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                if (response.body() == null) {
                    Toast.makeText(
                        getContext(),
                        ManagerConstant.MessengerManager.MSG_CONVERT,
                        Toast.LENGTH_SHORT
                    ).show();
                    return;
                }
                loadDataView(response.body());
            }

            @Override
            public void onFailure(Call<ListPrimaryMovieInfo> call, Throwable t) {
                Toast.makeText(
                    getContext(),
                    ManagerConstant.MessengerManager.MSG_LOAD,
                    Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void loadDataView(ListPrimaryMovieInfo listMP) {
        mListPrimaryMovieInfo = listMP.getMovieInfo();
        MoviePopularAdapter adapter = new MoviePopularAdapter(getContext(), mListPrimaryMovieInfo);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
