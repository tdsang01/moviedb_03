package com.framgia.moviedb.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrimaryMovieInfo {
    @SerializedName("id")
    private int mId;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("adult")
    private boolean mAdult;

    @SerializedName("backdrop_path")
    private String mBackdropPath;

    @SerializedName("belongs_to_collection")
    private String mBelongsToCollection;

    @SerializedName("budget")
    private long mBudget;

    @SerializedName("genres")
    private List<Genre> mGenres;

    @SerializedName("homepage")
    private String mHomePage;

    @SerializedName("imdb_id")
    private String mImdbId;

    @SerializedName("original_language")
    private String mOriginalLanguage;

    @SerializedName("original_title")
    private String mOriginalTitle;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("popularity")
    private float mPopularity;

    @SerializedName("poster_path")
    private String mPosterPath;

    @SerializedName("production_companies")
    private List<Company> mCompanies;

    @SerializedName("release_date")
    private String mReleaseDate;

    @SerializedName("revenue")
    private long mRevenue;

    @SerializedName("runtime")
    private int mRuntime;

    @SerializedName("status")
    private String mStatus;

    @SerializedName("tagline")
    private String mTagLine;

    @SerializedName("video")
    private String mVideo;

    @SerializedName("vote_average")
    private float mVoteAverage;

    @SerializedName("vote_count")
    private int mVoteCount;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public String getBelongsToCollection() {
        return mBelongsToCollection;
    }

    public void setBelongsToCollection(String belongsToCollection) {
        mBelongsToCollection = belongsToCollection;
    }

    public long getBudget() {
        return mBudget;
    }

    public void setBudget(long budget) {
        mBudget = budget;
    }

    public List<Genre> getGenres() {
        return mGenres;
    }

    public void setGenres(List<Genre> genres) {
        mGenres = genres;
    }

    public String getHomePage() {
        return mHomePage;
    }

    public void setHomePage(String homePage) {
        mHomePage = homePage;
    }

    public String getImdbId() {
        return mImdbId;
    }

    public void setImdbId(String imdbId) {
        mImdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(float popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public void setCompanies(List<Company> companies) {
        mCompanies = companies;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public long getRevenue() {
        return mRevenue;
    }

    public void setRevenue(long revenue) {
        mRevenue = revenue;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public void setRuntime(int runtime) {
        mRuntime = runtime;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTagLine() {
        return mTagLine;
    }

    public void setTagLine(String tagLine) {
        mTagLine = tagLine;
    }

    public String getVideo() {
        return mVideo;
    }

    public void setVideo(String video) {
        mVideo = video;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }
}
