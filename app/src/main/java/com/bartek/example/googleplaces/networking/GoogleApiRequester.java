package com.bartek.example.googleplaces.networking;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by Bartek on 2014-11-29.
 */
public class GoogleApiRequester {

    private final String ENDPOINT = "https://maps.googleapis.com/maps/api";
    private final String KEY = "AIzaSyCm5xzBuITzPlnaaEHR69gzA6DyD7OQKJY";

    public void requestPlaacesInCity(String placeName
            , String cityName
            , Callback<PlacesList> callback) {


        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();

        GoogleAPIService service = adapter.create(GoogleAPIService.class);


        String placeParam = placeName + "+in+" + cityName;
        service.getPlacesForCity(placeParam, KEY, callback);
    }
}


//https://maps.googleapis.com/maps/api
// /place/textsearch/json?query=restaurants+in+Sydney&key=AIzaSyCpolzbNq_XcAF78ofElBSU7zR-b0sYmfc