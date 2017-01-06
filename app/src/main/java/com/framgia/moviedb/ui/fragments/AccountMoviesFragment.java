package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.ListPrimaryMovieInfo;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.service.RetrofitClient;
import com.framgia.moviedb.ui.adapter.MoviePopularAdapter;
import com.framgia.moviedb.ultils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountMoviesFragment extends Fragment implements MoviePopularAdapter.OnFragmentEvent {
    private String mTypeOfMoviesAccount;
    private List<PrimaryMovieInfo> mListAccountMovies;
    private RecyclerView mRecyclerView;
    private String mAccountId = ManagerConstant.ARGUMENT_ACCOUNT_ID;

    public static AccountMoviesFragment newInstance(String argumentTypeOfAccountMovie) {
        Fragment fragment = new AccountMoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(
            ManagerConstant.ARGUMENT_TYPE_OF_MOVIES_ACCOUNT, argumentTypeOfAccountMovie);
        fragment.setArguments(bundle);
        return (AccountMoviesFragment) fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_movies, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_account_movies);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mTypeOfMoviesAccount =
                bundle.getString(ManagerConstant.ARGUMENT_TYPE_OF_MOVIES_ACCOUNT);
        }
        loadMoviesAccount(mTypeOfMoviesAccount);
        return view;
    }

    private void loadMoviesAccount(String typeOfMoviesAccount) {
        Map<String, String> params = new HashMap<>();
        params.put(ManagerConstant.Param.PARAM_API_KEY, BuildConfig.API_KEY);
        params.put(ManagerConstant.Param.PARAM_LANGUAGE, ManagerConstant.LANGUAGE);
        params.put(ManagerConstant.Param.PARAM_SESSION_ID, BuildConfig.SESSION_ID);
        RetrofitClient.getAccountMovies(mAccountId, mTypeOfMoviesAccount, params,
            new Callback<ListPrimaryMovieInfo>() {
                @Override
                public void onResponse(Call<ListPrimaryMovieInfo> call,
                                       Response<ListPrimaryMovieInfo> response) {
                    if (response == null) {
                        Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
                        return;
                    }
                    if (response.body() == null) {
                        Utils
                            .makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_CONVERT);
                        return;
                    }
                    loadDataView(response.body());
                }

                @Override
                public void onFailure(Call<ListPrimaryMovieInfo> call, Throwable t) {
                    Utils.makeToast(getActivity(), ManagerConstant.MessengerManager.MSG_LOAD);
                }
            });
    }

    private void loadDataView(ListPrimaryMovieInfo listAM) {
        mListAccountMovies = listAM.getMovieInfo();
        MoviePopularAdapter adapter = new MoviePopularAdapter(getContext(),
            mListAccountMovies, this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onDataSelected(PrimaryMovieInfo movieInfo) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
            .beginTransaction();
        Fragment fragment = DetailsMovieFeaturesFragment.newInstance(movieInfo);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
