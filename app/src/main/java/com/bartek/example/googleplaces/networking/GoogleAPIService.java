package com.bartek.example.googleplaces.networking;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Bartek on 2014-11-29.
 */
public interface GoogleAPIService {

    @GET("/place/textsearch/json")
    void getPlacesForCity(@Query("query") String cityParam
            , @Query("key") String key
            , Callback<PlacesList> callback);

}




//https://maps.googleapis.com/maps/api
// /place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyCpolzbNq_XcAF78ofElBSU7zR-b0sYmfc