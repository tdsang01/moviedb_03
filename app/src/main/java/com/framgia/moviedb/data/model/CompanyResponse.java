package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by trungnguyens93gmail.com on 1/6/17.
 */
public class CompanyResponse {
    @SerializedName("results")
    private List<Company> mCompanies;

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
    }
}
