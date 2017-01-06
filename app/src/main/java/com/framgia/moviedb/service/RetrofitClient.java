package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.AccountDetail;
import com.framgia.moviedb.data.model.ListPrimaryMovieInfo;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;

import java.util.Map;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RetrofitClient {
    private static final APIServices clientForecast = new Retrofit.Builder()
        .baseUrl(ManagerConstant.UrlManager.ROOT_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(APIServices.class);

    public static void getListMoviePopular(String type,
                                           Map<String, String> params,
                                           Callback<ListPrimaryMovieInfo> callback) {
        clientForecast.getListMoviePopular(type, params).enqueue(callback);
    }

    public static void getMovieDetails(String movieId,
                                       Map<String, String> params,
                                       Callback<PrimaryMovieInfo> callback) {
        clientForecast.getMovieDetails(movieId, params).enqueue(callback);
    }

    public static void getAccountDetail(Map<String, String> params,
                                       Callback<AccountDetail> callback) {
        clientForecast.getAccountDetail(params).enqueue(callback);
    }
}
