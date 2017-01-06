package com.framgia.moviedb.service;

import com.framgia.moviedb.data.model.CollectionResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by trungnguyens93gmail.com on 1/5/17.
 */
public interface SearchApi {
    @GET("search/{option}")
    Call<CollectionResponse> getCollections(@Path("option") String option,
                                            @QueryMap Map<String, String> types);
    enum Api {
        API_KEY("api_key"),
        COLLECTIONS("collection"),
        COMPANY("company"),
        QUERY("query"),
        PAGE("page"),
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
