package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.GenreResponse;
import com.framgia.moviedb.data.model.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by trungnguyens93gmail.com on 1/2/17.
 */
public interface GenreApi {
    @GET("genre/{option1}/{option2}")
    Call<GenreResponse> getGenres(@Path("option1") String option1,
                                  @Path("option2") String option2,
                                  @QueryMap Map<String, String> types);
    @GET("genre/{id}/{option}")
    Call<MovieResponse> getMovies(@Path("id") int id,
                                  @Path("option") String option,
                                  @QueryMap Map<String, String> types);
    enum Api {
        API_KEY("api_key"),
        SINGLE_MOVIE("movie"),
        LIST_OF_MOVIES("list"),
        MOVIES("movies"),
        LANGUAGE("language");
        private String mValues;

        Api(String values) {
            mValues = values;
        }

        public String getValues() {
            return mValues;
        }
    }

    enum SortMovies {
        ASC("asc"),
        DESC("desc");
        private String mValues;

        SortMovies(String values) {
            mValues = values;
        }

        public String getValues() {
            return mValues;
        }
    }

    enum IncludeAdult {
        TRUE("true"),
        FALSE("false");
        private String mValues;

        IncludeAdult(String values) {
            mValues = values;
        }

        public String getValues() {
            return mValues;
        }
    }
}
