package com.autochime.autochimeapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

interface GPSListener {
    void onGPSUpdate(Location location);
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class GPSRetriever implements LocationListener, TransitionListener
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

    private Location GetLocation() {
        if (
            ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
            return null;

        try {
            mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

            // Get GPS and network status
            boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isNetworkEnabled && !isGPSEnabled)
                return null;

            // Prioritize GPS first
            if (isGPSEnabled)
                return mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (isNetworkEnabled)
                return mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        catch (Exception ex)  {}
        return null;
    }


    // TransitionListener Overrides
    @Override
    public void onTransition(StateMachine.State state) {
        switch (state) {
            case Notify:
                OnUpdate(GetLocation());
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String s) {}
}
