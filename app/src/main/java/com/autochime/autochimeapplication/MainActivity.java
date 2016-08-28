package com.autochime.autochimeapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.autochime.autochimeapplication.R;

public class MainActivity extends AppCompatActivity {
    CheckBox mRecordCheckbox;


//    private Button b;
//    private TextView t;
//    private LocationManager locationManager;
//    private LocationListener listener;
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StateMachine stateMachine = StateMachine.instance();
    }

        // just testing
//        SMSManager smsManager = new SMSManager();
//        smsManager.sendHardcode();
//
//        mRecordCheckbox = (CheckBox) findViewById(R.id.main_record_button);
//        mRecordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    startManualRecording();
//                } else {
//                    stopManualRecording();
//                }
//            }
//        });

        // Show contact selector
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(AddContactFragment.newInstance(), null);
//        ft.commit();
//
//        t = (TextView) findViewById(R.id.textView);
//        b = (Button) findViewById(R.id.button);
//
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                t.append("\n " + location.getLongitude() + "  " + location.getLatitude());
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(i);
//            }
//        };
//
//        configure_button();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode){
//            case 10:
//                configure_button();
//                break;
//            default:
//                break;
//        }
//    }
//
//    void configure_button(){
//        // first check for permissions
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
//                        ,10);
//            }
//            return;
//        }
//        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, listener);
//            }
//        });
//    }

    public void startManualRecording() {
        // user instantiated recording
        mRecordCheckbox.setText(getString(R.string.recording_button));
    }

    private void stopManualRecording() {
        mRecordCheckbox.setText(getString(R.string.not_recording_button));
    }
}