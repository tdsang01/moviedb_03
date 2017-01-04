package com.framgia.moviedb.data.model;

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
}
