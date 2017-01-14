package com.example.happy.hac;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Happy on 10/22/2016.
 */

public class LatLongGetSet {
    List<LatLng> latLngList;

    public void setLatLngList(List<LatLng> latLngList) {
        this.latLngList = latLngList;
    }

    public List<LatLng> getLatLngList() {
        return latLngList;
    }
}
