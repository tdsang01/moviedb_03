package com.framgia.moviedb.data.model;

public class ManagerConstant {
    public static final String LANGUAGE = "en-US";
    public static final String ARGUMENT_TYPE_OF_LIST_MOVIE = "ARGUMENT_TYPE_OF_LIST_MOVIE";

    public class UrlManager {
        public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
        public static final String ROOT_URL  = "https://api.themoviedb.org/3/";
        public static final String PATH_POPULAR_MOVIE = "movie/{load_movie_type}";
        public static final String PATH_TYPE_MOVIE = "load_movie_type";
    }

    public class MessengerManager {
        public static final String MSG_LOAD    = "Can't read data from server";
        public static final String MSG_CONVERT = "Can't convert data";
    }

    public class Param {
        public static final String PARAM_PAGE =  "page";
        public static final String PARAM_LANGUAGE = "language";
        public static final String PARAM_API_KEY = "api_key";
        public static final String PARAM_TYPE_MOVIE_POPULAR = "popular";
        public static final String PARAM_TYPE_MOVIE_TOP_RATED = "top_rated";
    }
}
