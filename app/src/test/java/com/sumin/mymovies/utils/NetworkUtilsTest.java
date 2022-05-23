package com.sumin.mymovies.utils;

import static org.junit.Assert.assertEquals;

import android.net.Uri;

import org.junit.Test;



public class NetworkUtilsTest {

    private static final String BASE_URL = "https://api.themoviedb.org/3/discover/movie";
    private static final String BASE_URL_VIDEOS = "https://api.themoviedb.org/3/movie/%s/videos";
    private static final String BASE_URL_REVIEWS = "https://api.themoviedb.org/3/movie/%s/reviews";

    private static final String PARAMS_API_KEY = "api_key";
    private static final String PARAMS_LANGUAGE = "language";
    private static final String PARAMS_SORT_BY = "sort_by";
    private static final String PARAMS_PAGE = "page";
    private static final String PARAMS_MIN_VOTE_COUNT = "vote_count.gte";
    private static final String MIN_VOTE_COUNT_VALUE = "1000";

    private static final String SEARCH_BASE_URL = "https://api.themoviedb.org/3/search/movie";
    private static final String SEARCH_PARAMS = "query";

    private static final String API_KEY = "3811dffbd8bc87f702dad54554f272a9";
    private static final String LANGUAGE_VALUE = "ru-RU";
    private static final String SORT_BY_POPULARITY = "popularity.desc";
    private static final String SORT_BY_TOP_RATED = "vote_average.desc";



    public static final int POPULARITY = 0;
    public static final int TOP_RATED = 1;

    @Test
    public void correctParseUrl() {


        String actual = "loc";
        String expected = "loc";

        assertEquals(actual, expected);
    }
}
