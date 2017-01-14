package com.example.happy.hac;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Happy on 10/23/2016.
 */

public class hacGPSUserLocation extends Service implements LocationListener{
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    Context context;
    double Latitude, Longitude;

    public hacGPSUserLocation(Context context){
        this.context = context;
        getUserLatLng();
    }

    public void getUserLatLng(){
        long mintime = 1000*60*1;
        float distance = 10;
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //manager.requestLocationUpdates(manager.GPS_PROVIDER, mintime, distance, this);
        //Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

               // getting GPS status
        isGPSEnabled = manager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = manager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if(!isGPSEnabled && !isNetworkEnabled){
            Toast.makeText(context, "hello"+isGPSEnabled +" "+isNetworkEnabled, Toast.LENGTH_SHORT).show();
        }else{
            if(isGPSEnabled){
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, mintime, distance, this);
                if(manager!=null){
                    Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null) {
                        setLatitude(location.getLatitude());
                        setLongitude(location.getLongitude());
                        Toast.makeText(context, "hello" + location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(context, "hello manager is empty", Toast.LENGTH_SHORT).show();
                }
            }
            if(isNetworkEnabled){
                if(isGPSEnabled){
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mintime, distance, this);
                    if(manager!=null){
                        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        setLatitude(location.getLatitude());
                        setLongitude(location.getLongitude());
                        Toast.makeText(context, "hello"+getLatitude()+" "+getLongitude(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "hello manager is empty", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    public double getLatitude() {
        return Latitude;
    }


    public double getLongitude() {
        return Longitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
