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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StateMachine stateMachine = StateMachine.instance();
        Alarm.instance();
        AudioRecorder.instance();
        GPSRetriever.instance();
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

    public void startManualRecording() {
        // user instantiated recording
        mRecordCheckbox.setText(getString(R.string.recording_button));
    }

    private void stopManualRecording() {
        mRecordCheckbox.setText(getString(R.string.not_recording_button));
    }
}