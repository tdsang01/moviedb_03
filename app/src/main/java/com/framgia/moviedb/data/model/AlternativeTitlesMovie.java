package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

public class AlternativeTitlesMovie {
    @SerializedName("iso_3166_1")
    private String mIso31661;
    
    @SerializedName("title")
    private String mTitle;

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
