package com.bartek.example.googleplaces;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.bartek.example.googleplaces.networking.GoogleAPIService;
import com.bartek.example.googleplaces.networking.GoogleApiRequester;
import com.bartek.example.googleplaces.networking.GooglePlace;
import com.bartek.example.googleplaces.networking.PlacesList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        requestPlaces();
    }

    private void requestPlaces() {

        GoogleApiRequester requester = new GoogleApiRequester();
        requester.requestPlaacesInCity("restaurant", "Warsaw", new Callback<PlacesList>() {
            @Override
            public void success(PlacesList placesList, Response response) {
                presentPlaces(placesList);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private void presentPlaces(PlacesList placesList) {

        for (GooglePlace place:placesList.getPlaces()) {
            mMap.addMarker(new MarkerOptions()
                    .position(
                            new LatLng(place.getLocationLat()
                                    , place.getLocationLng()))
                    .title(place.getAddress()));
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(52.4, 20.5)).zoom(12).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

        mMap.setMyLocationEnabled(true);
//        mMap.getUiSettings().setZoomControlsEnabled(false);  -> method turns off ZOOM in the map
//        mMap.addMarker(new MarkerOptions().position(new LatLng(52.4546843, 20.564963))
//                .title("My Marker")
//                .icon(BitmapDescriptorFactory
//                        .fromResource(R.drawable.atm)));

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52.4, 20.5), 13));


    }

    public void setSatellite(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void setTerrain(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }

    public void setHybrid(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    public void setNormal(View view) {
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
