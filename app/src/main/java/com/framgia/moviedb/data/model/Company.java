package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

public class Company {
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("headquarters")
    private String mHeadquarters;

    @SerializedName("homepage")
    private String mHomePage;

    @SerializedName("logo_path")
    private String mLogoPath;

    @SerializedName("parent_company")
    private String mParentCompany;

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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getHeadquarters() {
        return mHeadquarters;
    }

    public void setHeadquarters(String headquarters) {
        mHeadquarters = headquarters;
    }

    public String getHomePage() {
        return mHomePage;
    }

    public void setHomePage(String homePage) {
        mHomePage = homePage;
    }

    public String getLogoPath() {
        return mLogoPath;
    }

    public void setLogoPath(String logoPath) {
        mLogoPath = logoPath;
    }

    public String getParentCompany() {
        return mParentCompany;
    }

    public void setParentCompany(String parentCompany) {
        mParentCompany = parentCompany;
    }
}
