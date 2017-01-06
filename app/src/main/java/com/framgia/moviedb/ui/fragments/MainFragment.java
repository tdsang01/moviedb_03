package com.framgia.moviedb.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.framgia.moviedb.BuildConfig;
import com.framgia.moviedb.R;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.MovieResponse;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;
import com.framgia.moviedb.service.ApiClient;
import com.framgia.moviedb.service.MovieApi;
import com.framgia.moviedb.service.RetrofitCallback;
import com.framgia.moviedb.ui.adapter.HorizontalMoviesAdapter;
import com.framgia.moviedb.ui.interactor.OnListenerCallback;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by trungnguyens93gmail.com on 12/27/16.
 */
public class MainFragment extends Fragment implements View.OnClickListener, OnListenerCallback {
    private ImageView mImageLastedMovie;
    private TextView mMorePopularMovies;
    private TextView mMoreTopRatedMovies;
    private RecyclerView mPopularRecycleView;
    private RecyclerView mTopRatedRecycleView;
    private MovieApi mMovieApi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        // Init Lasted Movie
        mImageLastedMovie = (ImageView) view.findViewById(R.id.image_lasted_movie);
        mMorePopularMovies = (TextView) view.findViewById(R.id.text_more_popular_movies);
        mMoreTopRatedMovies = (TextView) view.findViewById(R.id.text_more_top_rated_movies);
        // Init RecycleView
        mPopularRecycleView = (RecyclerView) view.findViewById(R.id.popular_movies);
        mTopRatedRecycleView = (RecyclerView) view.findViewById(R.id.top_rated_movies);
        // Setting RecycleView
        mPopularRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),
            LinearLayoutManager.HORIZONTAL, false));
        mTopRatedRecycleView.setLayoutManager(new LinearLayoutManager(getContext(),
            LinearLayoutManager.HORIZONTAL, false));
        // Get data for PopularRecycleView
        mMovieApi = ApiClient.getClient().create(MovieApi.class);
        // Get data
        getLastedMovie();
        getPopularMovies();
        getTopRatedMovies();
        // Get more movies
        mMorePopularMovies.setOnClickListener(this);
        mMoreTopRatedMovies.setOnClickListener(this);
        return view;
    }

    public void getLastedMovie() {
        Map<String, String> data = new HashMap<>();
        data.put(MovieApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(MovieApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        mMovieApi.getLasted(MovieApi.Api.LATEST.getValues(), data)
            .enqueue(new RetrofitCallback<PrimaryMovieInfo>(this) {
                @Override
                public void onResponse(Call<PrimaryMovieInfo> call,
                                       Response<PrimaryMovieInfo> response) {
                    super.onResponse(call, response);
                    PrimaryMovieInfo primaryMovieInfo = response.body();
                    Picasso.with(getContext()).load(ApiClient.BASE_IMAGE_URL +
                        primaryMovieInfo.getPosterPath())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(mImageLastedMovie);
                }
            });
    }

    public void getPopularMovies() {
        Map<String, String> data = new HashMap<>();
        data.put(MovieApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(MovieApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        data.put(MovieApi.Api.PAGE.getValues(), String.valueOf(1));
        mMovieApi.getPopularMovies(MovieApi.Api.POPULAR.getValues(), data)
            .enqueue(new RetrofitCallback<MovieResponse>(this) {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    super.onResponse(call, response);
                    List<PrimaryMovieInfo> primaryMovieInfos = response.body().getResult();
                    mPopularRecycleView.setAdapter(new HorizontalMoviesAdapter(getContext(),
                        primaryMovieInfos));
                }
            });
    }

    public void getTopRatedMovies() {
        Map<String, String> data = new HashMap<>();
        data.put(MovieApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(MovieApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        data.put(MovieApi.Api.PAGE.getValues(), String.valueOf(1));
        mMovieApi.getTopRetedMovies(MovieApi.Api.TOP_RATED.getValues(), data)
            .enqueue(new RetrofitCallback<MovieResponse>(this) {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    super.onResponse(call, response);
                    List<PrimaryMovieInfo> primaryMovieInfos = response.body().getResult();
                    mTopRatedRecycleView.setAdapter(new HorizontalMoviesAdapter(getContext(),
                        primaryMovieInfos));
                }
            });
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getActivity()
            .getSupportFragmentManager()
            .beginTransaction();
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.text_more_popular_movies:
                fragment = ListMovieFragment.newInstance(
                    ManagerConstant.Param.PARAM_TYPE_MOVIE_POPULAR);
                break;
            case R.id.text_more_top_rated_movies:
                fragment = ListMovieFragment.newInstance(
                    ManagerConstant.Param.PARAM_TYPE_MOVIE_TOP_RATED);
                break;
            default:
                break;
        }
        if (fragment != null) {
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), R.string.error_data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyData() {
        Toast.makeText(getContext(), R.string.error_load, Toast.LENGTH_SHORT).show();
    }
}
