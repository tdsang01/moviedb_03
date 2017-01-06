package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.AccountDetail;
import com.framgia.moviedb.data.model.ListPrimaryMovieInfo;
import com.framgia.moviedb.data.model.ManagerConstant;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface APIServices {
    @GET(ManagerConstant.UrlManager.PATH_LIST_MOVIE)
    Call<ListPrimaryMovieInfo> getListMoviePopular(
        @Path(ManagerConstant.UrlManager.PATH_TYPE_MOVIE) String type,
        @QueryMap Map<String, String> params
    );

    @GET(ManagerConstant.UrlManager.PATH_MOVIE_DETAILS)
    Call<PrimaryMovieInfo> getMovieDetails(
        @Path(ManagerConstant.UrlManager.PATH_MOVIE_ID) String movieId,
        @QueryMap Map<String, String> params
    );

    @GET(ManagerConstant.UrlManager.PATH_ACCOUNT_DETAIL)
    Call<AccountDetail> getAccountDetail(
        @QueryMap Map<String, String> params
    );
}
