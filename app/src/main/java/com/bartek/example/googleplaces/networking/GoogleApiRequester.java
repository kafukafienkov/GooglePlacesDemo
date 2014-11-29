package com.bartek.example.googleplaces.networking;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Bartek on 2014-11-29.
 */
public class GoogleApiRequester {

    private final String ENDPOINT = "https://maps.googleapis.com/maps/api";

    public void requestPlaacesInCity(String placeName
            , String cityName
            , Callback<PlacesList> callback) {


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        GoogleAPIService service = adapter.create(GoogleAPIService.class);


    }
}


//https://maps.googleapis.com/maps/api
// /place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyCpolzbNq_XcAF78ofElBSU7zR-b0sYmfc