package com.example.happy.hac;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Happy on 10/13/2016.
 */

public class hac_front_fragment extends Fragment {


    GoogleMap map;
    Location location;
    LatLng start_location;
    ProgressDialog progressBar;
    TextView textView;
    SupportMapFragment fragment;

    double latlang = 0;
    double loglat= 0;

    long MIN_DIST = 15;
    long MIN_TIME = 1000*2*60;

    LocationManager manager;
    List<LatLng> latLngList = new ArrayList<>();
    List<LatLng> Location_first_to_destination_original = new ArrayList<>();
    static String url = null;


    //TEST URLS
   // String url_of_hospital = "http://harpreetsingh.s156.eatj.com/HAC/helloo.jsp";
  //url = "http://maps.googleapis.com/maps/api/directions/json?origin=sharma+hospital+Kharar+Punjab&destination="+address+"&sensor=true";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hac_front_page, container, false);
        fragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.google_map_id);
        textView = (TextView) view.findViewById(R.id.google_map_text_view);
        Geocoder geocoder = new Geocoder(getContext());
        manager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        final hacGPSUserLocation hacGPSUserLocation = new hacGPSUserLocation(getContext());

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

            final LatLongGetSet latLongGetSet = new LatLongGetSet();

            fragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;

                    map.setMyLocationEnabled(true);
                    //location = map.getMyLocation();
                    //  Location_first_to_destination_original = latLongGetSet.getLatLngList();
                    LatLng start_location = new LatLng(hacGPSUserLocation.getLatitude(), hacGPSUserLocation.getLongitude());
                    //  System.out.println("the value of list is latlng --------------------------------------------------:"+Location_first_to_destination_original.size());
                    latlang = hacGPSUserLocation.getLatitude();
                    loglat =  hacGPSUserLocation.getLongitude();
                    new mapserfing().execute();

                    if(googleMap !=null) {
                        if(start_location != null){
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(start_location, 11));
                            map.addMarker(new MarkerOptions().position(start_location)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.hac_user_marker)));
                        }else{
                            Toast.makeText(getContext(), "null of start_locationnn", Toast.LENGTH_SHORT).show();
                        }
                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                            @Override
                            public boolean onMarkerClick(Marker marker) {

                                if(latLngList.size() != 0){
                                    LatLng destination = latLngList.get(latLngList.size()-1);
                                    map.addMarker(new MarkerOptions().position(destination)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.hac_marker_icon)));

                                }
                                Toast.makeText(getContext(), "new"+latLngList.size(), Toast.LENGTH_SHORT).show();
                                map.addPolyline(new PolylineOptions()
                                        .addAll(latLngList)
                                        .width(12)
                                        .color(Color.BLUE).geodesic(true));

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

    class mapserfing extends AsyncTask{

        @Override
        protected void onPreExecute() {
            progressBar = new ProgressDialog(getContext());
            progressBar.setMessage("Loading Please Wait...");
            progressBar.setProgress(0);
            progressBar.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            JSONObject jsonObject = hac_json_parser.getHosptialAdress("post", "http://harpreetsingh.s156.eatj.com/HAC/helloo.jsp");
            System.out.println("th ene x tvalue of is -------------------------------------------------------------------"+ jsonObject);
            String address = null;
            try {
                address = jsonObject.getString("hosptial_name").toString()+""+jsonObject.getString("city")+""+jsonObject.getString("state");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            url = "http://maps.googleapis.com/maps/api/directions/json?origin="+latlang+","+loglat+"&destination="+address+"&sensor=true";

            JSONObject object = hac_json_parser.getPathLocation("post", url);
            try {
                JSONArray array = (JSONArray) object.getJSONArray("routes");
                JSONObject onbjJsonObject = null;

                JSONArray onbjJsonObject_steps = null;
                JSONObject jsonObject_steps = null;
                JSONArray jsonObject_steps_leg_steps = null;
                JSONArray jsonObject_steps_leg_steps_end_start = null;
                JSONObject jsonobject_steps_leg_steps_start_location = null;
                JSONObject jsonobject_steps_leg_steps_end_location = null;
                JSONObject jsonObjectt = null;

                double southwest_latt = 0;
                double southwest_lngg = 0;

                double northeast_lat = 0;
                double northeast_lng = 0;

                List<LatLng> latLngs = new ArrayList<>();
                for(int i = 0; i<array.length(); i++){
                    onbjJsonObject = array.getJSONObject(i).getJSONObject("bounds");
                    onbjJsonObject_steps = array.getJSONObject(i).getJSONArray("legs");

                    for(int j =0; j<onbjJsonObject_steps.length(); j++){
                        jsonObject_steps_leg_steps = onbjJsonObject_steps.getJSONObject(j).getJSONArray("steps");
                        for(int k = 0; k<jsonObject_steps_leg_steps.length(); k++){
                            jsonobject_steps_leg_steps_start_location = jsonObject_steps_leg_steps.getJSONObject(k).getJSONObject("start_location");
                            if(k == 0 ){

                                // NORTHEAST FIRST LOCATION -- START POINT LOCATION
                                northeast_lat = jsonobject_steps_leg_steps_start_location.getDouble("lat");
                                northeast_lng = jsonobject_steps_leg_steps_start_location.getDouble("lng");

                            }
                            southwest_latt = jsonobject_steps_leg_steps_start_location.getDouble("lat");
                            southwest_lngg = jsonobject_steps_leg_steps_start_location.getDouble("lng");
                            latLngList.add(new LatLng(jsonobject_steps_leg_steps_start_location.getDouble("lat"), jsonobject_steps_leg_steps_start_location.getDouble("lng")));
                            jsonObject_steps_leg_steps = onbjJsonObject_steps.getJSONObject(j).getJSONArray("steps");
                            for(int t = 0; t<jsonObject_steps_leg_steps.length(); t++){
                                jsonobject_steps_leg_steps_end_location = jsonObject_steps_leg_steps.getJSONObject(k).getJSONObject("end_location");
                                if(k == t ){
                                    latLngList.add(new LatLng(jsonobject_steps_leg_steps_end_location.getDouble("lat"), jsonobject_steps_leg_steps_end_location.getDouble("lng")));
                                    break;
                                }
                            }
                        }
                    }

                    // SOUTHEAST END POINT LOCATION
                    // jsonObjectt = onbjJsonObject.getJSONObject("southwest");
                    // southwest_latt = jsonObjectt.getDouble("lat");
                    // southwest_lngg = jsonObjectt.getDouble("lng");

                }
                //Location_first_to_destination_original.add(new LatLng(northeast_lat, northeast_lng));
                //Location_first_to_destination_original.add(new LatLng(southwest_latt, southwest_lngg));
                //latLongGetSet.setLatLngList(Location_first_to_destination);
                //for(int i = 0; i<latLngList.size(); i++){
                //    System.out.println("the value of list is latlng --------------------------------------------------:"+latLngList.size()+" "+latLngList.get(i));
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            progressBar.hide();
        }
    }
}
