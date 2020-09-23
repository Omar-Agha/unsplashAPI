package com.example.android.test_application.api;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class UnsplashApi {

    private static final Uri BASE_URI = Uri.parse("https://api.unsplash.com");
    private static final String ACCESS_KEY = "V4NlWAUJSQmj4AE9uoz3J1a8sqeAyJt0_RAaYBJhi84";
    private static final String SECRET_KEY = "bZODAtax4fRnFk-FAjXb6nD4LyG-YKhVFHOiJ5PwlNs";
    private static final String CLIENT_ID_PARAM = "client_id";
    private static final String QUERY_PARAM = "query";
    private static final String PHOTOS_PATH = "photos";
    private static final String RANDOM_PATH = "random";
    private static final String COUNT_PARAM = "count";

    public static URL lookForListOfPhotosUrl(String query){
        Uri.Builder builder = BASE_URI.buildUpon();

        builder.appendPath(PHOTOS_PATH);
        builder.appendPath(RANDOM_PATH);
        builder.appendQueryParameter(CLIENT_ID_PARAM ,ACCESS_KEY);
        builder.appendQueryParameter(QUERY_PARAM,query);
        builder.appendQueryParameter(COUNT_PARAM,"30");
        builder.appendQueryParameter("orientation","squarish");
        URL url;
        try {
            url =new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            url = null;
            e.printStackTrace();
        }
        return url;
    }
}
