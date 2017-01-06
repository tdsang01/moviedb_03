package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.Company;
import com.framgia.moviedb.data.model.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by trungnguyens93gmail.com on 1/2/17.
 */
public interface CompanyApi {
    @GET("company/{company_id}")
    Call<Company> getDetail(@Path("company_id") int id, @QueryMap Map<String, String> types);
    @GET("company/{company_id}/{option}")
    Call<MovieResponse> getMovies(@Path("company_id") int id,
                                  @Path("option") String option,
                                  @QueryMap Map<String, String> types);
    enum Api {
        API_KEY("api_key"),
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
}
