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
import com.framgia.moviedb.data.model.Genre;
import com.framgia.moviedb.data.model.GenreResponse;
import com.framgia.moviedb.service.ApiClient;
import com.framgia.moviedb.service.GenreApi;
import com.framgia.moviedb.service.RetrofitCallback;
import com.framgia.moviedb.ui.adapter.GenreAdapter;
import com.framgia.moviedb.ui.interactor.OnListenerCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class GenreFragment extends Fragment implements OnListenerCallback {
    private RecyclerView mGenresRecyclerView;
    private GenreApi mGenreApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_genre, container, false);
        mGenresRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_genres);
        mGenresRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mGenreApi = ApiClient.getClient().create(GenreApi.class);
        loadGenres();
        return view;
    }

    public void loadGenres() {
        Map<String, String> data = new HashMap<>();
        data.put(GenreApi.Api.API_KEY.getValues(), BuildConfig.API_KEY);
        data.put(GenreApi.Api.LANGUAGE.getValues(), ApiClient.LANGUAGE);
        mGenreApi.getGenres(GenreApi.Api.SINGLE_MOVIE.getValues(),
                            GenreApi.Api.LIST_OF_MOVIES.getValues(),
                            data)
            .enqueue(new RetrofitCallback<GenreResponse>(this) {
                @Override
                public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                    super.onResponse(call, response);
                    List<Genre> genres = response.body().getGenres();
                    mGenresRecyclerView.setAdapter(new GenreAdapter(getContext(), genres));
                }
            });
    }

    @Override
    public void onFailure() {
        Toast.makeText(getContext(), R.string.error_load, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyData() {
        Toast.makeText(getContext(), R.string.error_data, Toast.LENGTH_SHORT).show();
    }
}
