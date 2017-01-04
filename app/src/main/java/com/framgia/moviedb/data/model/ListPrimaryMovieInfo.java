package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListPrimaryMovieInfo {
    @SerializedName("results")
    private ArrayList<PrimaryMovieInfo> mMovieInfo = new ArrayList<>();

    public ArrayList<PrimaryMovieInfo> getMovieInfo() {
        return mMovieInfo;
    }

    public void setMovieInfo(ArrayList<PrimaryMovieInfo> movieInfo) {
        mMovieInfo = movieInfo;
    }
}
