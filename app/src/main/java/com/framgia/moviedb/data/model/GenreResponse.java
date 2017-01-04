package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 1/2/17.
 */
public class GenreResponse {
    @SerializedName("genres")
    private List<Genre> mGenres;

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }
}
