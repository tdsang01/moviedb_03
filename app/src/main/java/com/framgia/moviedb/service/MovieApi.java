package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.AlternativeTitlesMovie;
import com.framgia.moviedb.data.model.MovieResponse;
import com.framgia.moviedb.data.model.PrimaryMovieInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by trungnguyens93gmail.com on 12/29/16.
 */
public interface MovieApi {
    @GET("movie/{movie_id}")
    Call<PrimaryMovieInfo> getDetail(@Path("movie_id") int movieId,
                                     @QueryMap Map<String, String> options);
    @GET("movie/{movie_id}/{option}")
    Call<AlternativeTitlesMovie> getAlternativeTitle(@Path("movie_id") int movieId,
                                                     @Path("option") String option,
                                                     @QueryMap Map<String, String> types);
    @GET("movie/{option}")
    Call<MovieResponse> getPopularMovies(@Path("option") String option,
                                         @QueryMap Map<String, String> types);
    @GET("movie/{option}")
    Call<MovieResponse> getTopRetedMovies(@Path("option") String option,
                                          @QueryMap Map<String, String> types);
    @GET("movie/{option}")
    Call<PrimaryMovieInfo> getLasted(@Path("option") String option,
                                     @QueryMap Map<String, String> types);
    enum Api {
        API_KEY("api_key"),
        AUTHORIZATION("alternative_titles"),
        LANGUAGE("language"),
        CREDITS("credits"),
        PAGE("page"),
        POPULAR("popular"),
        TOP_RATED("top_rated"),
        LATEST("latest");
        private String mValues;

        Api(String values) {
            mValues = values;
        }

        public String getValues() {
            return mValues;
        }
    }
}
