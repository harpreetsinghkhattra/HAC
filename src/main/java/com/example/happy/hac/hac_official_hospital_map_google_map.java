package com.example.happy.hac;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by Happy on 10/23/2016.
 */

public class hac_official_hospital_map_google_map extends Fragment {
    SupportMapFragment fragment;
    GoogleMap map;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hac_official_hospital_map_google_map,container , false);
        fragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.hac_official_hospital_google_map_id);
        try {
            // ADDRESS CODE TO FETCH IT BY STRING   ---------------------------
            // List<Address> addressList = geocoder.getFromLocationName(address, 5);
            // List<Address> addressList1 = geocoder.getFromLocationName(address2, 5);
            // Address address1_start = addressList.get(0);
            // Address address_destination = addressList1.get(0);
            // System.out.println("the system lat and lang"+address1.getLatitude() +"and "+address1.getLongitude());
            // Toast.makeText(getContext(), "the latitude of landran is"+address1.getLatitude()+"\n the longitude is"+address1.getLongitude()+"/n this is country name : "+address1.getCountryName(), Toast.LENGTH_LONG).show();
            // lat_start = address1_start.getLatitude();
            // lng_start = address1_start.getLongitude();
            // lat_destination = address_destination.getLatitude();
            // lng_destination = address_destination.getLongitude();

            //--------------------------------------

            fragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;

                    map.setMyLocationEnabled(true);
                   if(googleMap !=null) {
                        //  map.setMyLocationEnabled(true);

                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {

                                Toast.makeText(getContext(), "new", Toast.LENGTH_SHORT).show();

                                return true;
                            }
                        });
                    }else{
                        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
                    }

                    }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
