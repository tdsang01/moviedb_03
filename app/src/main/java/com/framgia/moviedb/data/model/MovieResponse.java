package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 12/29/16.
 */
public class MovieResponse {
    @SerializedName("results")
    private List<PrimaryMovieInfo> mResult;

    public List<PrimaryMovieInfo> getResult() {
        return mResult;
    }

    public void setResult(List<PrimaryMovieInfo> result) {
        mResult = result;
    }
}
