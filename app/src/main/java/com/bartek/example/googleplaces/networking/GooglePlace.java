package com.bartek.example.googleplaces.networking;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Bartek on 2014-11-29.
 */
public class GooglePlace {

    @SerializedName("formatted_address")
    String address;

    private Geometry geometry;

    public String getAddress() {
        return address;
    }

    public double getLocationLat() {
        return geometry.location.lat;
    }

    public double getLocationLng() {
        return geometry.location.lng;
    }

    class Geometry {
        private GLocation location;
    }

    class GLocation {
        double lat;
        double lng;
    }
}
