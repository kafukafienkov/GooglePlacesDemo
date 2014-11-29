package com.bartek.example.googleplaces.networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Bartek on 2014-11-29.
 */
public class PlacesList {

    @SerializedName("results")
    List<GooglePlace> places;

    public List<GooglePlace> getPlaces() {
        return places;
    }
}
