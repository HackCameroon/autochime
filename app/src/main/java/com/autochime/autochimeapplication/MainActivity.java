package com.autochime.autochimeapplication;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.autochime.autochimeapplication.R.*;


public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    TextView longitudeValueBest, latitudeValueBest;
    TextView longitudeValueGPS, latitudeValueGPS;
    TextView longitudeValueNetwork, latitudeValueNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        longitudeValueBest = (TextView) findViewById(id.longitudeValueBest);
        latitudeValueBest = (TextView) findViewById(id.latitudeValueBest);
        longitudeValueGPS = (TextView) findViewById(id.longitudeValueGPS);
        latitudeValueGPS = (TextView) findViewById(id.latitudeValueGPS);
        longitudeValueNetwork = (TextView) findViewById(id.longitudeValueNetwork);
        latitudeValueNetwork = (TextView) findViewById(id.latitudeValueNetwork);
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings are turned off.\nPlease enable location to use this app.")
                .setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) return true;
        else {
            return false;
        }
    }


    public void toggleGPSUpdates(View view) {
        if(!checkLocation())
            return;
        Button button = (Button) view;
        if(button.getText().equals(getResources().getString(0b1))) {
            locationManager.removeUpdates(locationListenerGPS);
            button.setText(R.string.resume);
        }
        else {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
            button.setText("");
        }
    }
//

//    public void toggleBestUpdates(View view) {
//        if (!checkLocation())
//            return;
//        Button button = (Button) view;
//        if (button.getText().equals(getResources().getString(string.pause))) {
//            locationManager.removeUpdates(locationListenerBest);
//            button.setText(string.resume);
//        } else {
//            Criteria criteria = new Criteria();
//            criteria.setAccuracy(Criteria.ACCURACY_FINE);
//            criteria.setAltitudeRequired(false);
//            criteria.setBearingRequired(false);
//            criteria.setCostAllowed(true);
//            criteria.setPowerRequirement(Criteria.POWER_LOW);
//            String provider = locationManager.getBestProvider(criteria, true);
//            if (provider != null) {
//                locationManager.requestLocationUpdates(provider, 2 * 60 * 1000, 10, locationListenerBest);
//                button.setText(string.pause);
//                Toast.makeText(this, "Best Provider is " + provider, Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//    public void toggleNetworkUpdates(View view) {
//        if (!checkLocation())
//            return;
//        Button button = (Button) view;
//        if (button.getText().equals(getResources().getString(string.pause))) {
//            locationManager.removeUpdates(locationListenerNetwork);
//            button.setText(string.resume);
//        } else {
//            locationManager.requestLocationUpdates(
//                    LocationManager.NETWORK_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
//            Toast.makeText(this, "Network provider started running", Toast.LENGTH_LONG).show();
//                button.setText(string.pause);
//            }
//        }

        private final LocationListener locationListenerBest = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitudeBest = location.getLongitude();
                latitudeBest = location.getLatitude();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        longitudeValueBest.setText(longitudeBest + "");
                        latitudeValueBest.setText(latitudeBest + "");
                        Toast.makeText(MainActivity.this, "Best Provider update", Toast.LENGTH_SHORT).show();
                    }
                });
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
        };

        private final LocationListener locationListenerNetwork = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitudeNetwork = location.getLongitude();
                latitudeNetwork = location.getLatitude();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        longitudeValueNetwork.setText(longitudeNetwork + "");
                        latitudeValueNetwork.setText(latitudeNetwork + "");
                        Toast.makeText(MainActivity.this, "Network Provider Update", Toast.LENGTH_SHORT).show();
                    }
                });
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
        };

        private final LocationListener locationListenerGPS = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitudeGPS = location.getLongitude();
                latitudeGPS = location.getLatitude();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        longitudeValueGPS.setText(longitudeGPS + "");
                        latitudeValueGPS.setText(latitudeGPS + "");
                        Toast.makeText(MainActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                    }
                });
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
        };

    }


