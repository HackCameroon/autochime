package com.autochime.autochimeapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

interface GPSListener {
    void onGPSUpdate(Location location);
}

public class GPSRetriever implements LocationListener
{
    LocationManager mLocationManager;
    Context mContext;

    static GPSRetriever mInstance = null;
    static GPSRetriever instance() {
        if (mInstance == null)
            mInstance = new GPSRetriever();
        return mInstance;
    }
    GPSRetriever() {
        mContext = AutoChimeApplication.getAppContext();
        mLocationManager = (LocationManager)mContext.getSystemService(mContext.LOCATION_SERVICE);
    }

    private List<GPSListener> mListeners = new ArrayList<GPSListener>();
    public void addListener(GPSListener listener) { mListeners.add(listener); }
    public void OnUpdate(Location location) { for(GPSListener listener : mListeners) listener.onGPSUpdate(location); }


    public void getLocation() {
        if (
            ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
            return;

        try {
            mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

            // Get GPS and network status
            boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled)
                return;

            // Prioritize GPS first
            if (isGPSEnabled)
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 1, this);

            if (isNetworkEnabled)
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 1, this);
        }
        catch (Exception ex)  {}
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            mLocationManager.removeUpdates(this);
        } catch (SecurityException e) {
            // uh.. location request was registered but cant remove?!
        }
        if (location != null) {
            OnUpdate(location);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String s) {}
}
