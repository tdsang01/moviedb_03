package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

public class Avatar {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("iso_639_1")
    private String mIso6391;

    @SerializedName("iso_3166_1")
    private String mIso31661;

    @SerializedName("include_adult")
    private boolean mIncludeAdult;

    @SerializedName("username")
    private String mUserName;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getIso6391() {
        return mIso6391;
    }

    public void setIso6391(String iso6391) {
        mIso6391 = iso6391;
    }

    public String getIso31661() {
        return mIso31661;
    }

    public void setIso31661(String iso31661) {
        mIso31661 = iso31661;
    }

    public boolean isIncludeAdult() {
        return mIncludeAdult;
    }

    public void setIncludeAdult(boolean includeAdult) {
        mIncludeAdult = includeAdult;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
