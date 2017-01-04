package com.framgia.moviedb.data.model;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface APIServices {
    @GET(ManagerConstant.UrlManager.PATH_POPULAR_MOVIE)
    Call<ListPrimaryMovieInfo> getListMoviePopular(
        @Path(ManagerConstant.UrlManager.PATH_TYPE_MOVIE) String type,
        @QueryMap Map<String, String> params
    );
}
