package com.framgia.moviedb.data.model;

public class ManagerConstant {
    public static final String LANGUAGE = "en-US";
    public static final String ARGUMENT_TYPE_OF_LIST_MOVIE = "ARGUMENT_TYPE_OF_LIST_MOVIE";
    public static final String ARGUMENT_TYPE_OF_SEARCH = "ARGUMENT_TYPE_OF_SEARCH";
    public static final String ARGUMENT_MOVIE_ID = "ARGUMENT_MOVIE_ID";
    public static final String ARGUMENT_MOVIE_ID_DEFAULT = "0";

    public class UrlManager {
        public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
        public static final String ROOT_URL = "https://api.themoviedb.org/3/";
        public static final String PATH_LIST_MOVIE = "movie/{load_movie_type}";
        public static final String PATH_TYPE_MOVIE = "load_movie_type";
        public static final String PATH_MOVIE_DETAILS = "movie/{movie_id}";
        public static final String PATH_MOVIE_ID = "movie_id";
    }

    public class MessengerManager {
        public static final String MSG_LOAD = "Can't read data from server";
        public static final String MSG_CONVERT = "Can't convert data";
    }

    public class Param {
        public static final String PARAM_PAGE = "page";
        public static final String PARAM_LANGUAGE = "language";
        public static final String PARAM_API_KEY = "api_key";
        public static final String PARAM_TYPE_MOVIE_POPULAR = "popular";
        public static final String PARAM_TYPE_MOVIE_TOP_RATED = "top_rated";
        public static final String PARAM_TYPE_COMPANY = "company";
        public static final String PARAM_TYPE_COLLECTION = "collection";
    }
}
