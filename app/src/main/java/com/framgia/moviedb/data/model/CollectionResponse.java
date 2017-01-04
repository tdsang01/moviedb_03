package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 1/5/17.
 */
public class CollectionResponse {
    @SerializedName("results")
    private List<Collection> mCollections;

    public List<Collection> getCollections() {
        return mCollections;
    }

    public void setCollections(List<Collection> collections) {
        mCollections = collections;
    }
}
