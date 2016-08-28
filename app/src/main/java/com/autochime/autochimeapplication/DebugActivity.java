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
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.autochime.autochimeapplication.R;

class StateTextChange implements TransitionListener {

    TextView mText;

    StateTextChange(TextView text) {
        mText = text;
        StateMachine.instance().addListener(this);
    }

    @Override
    public void onTransition(StateMachine.State state) {
        String str = "";
        switch (state) {
            case Default: str = "Default"; break;
            case AutoAlarm: str = "AutoAlarm"; break;
            case ManualAlarm: str = "ManualAlarm"; break;
            case Notify: str = "Notify"; break;
            case PostNotify: str = "PostNotify"; break;
            default: break;
        }
        mText.setText(str);
    }
}

public class DebugActivity extends MainActivity {
    CheckBox mRecordCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        StateTextChange stc = new StateTextChange(((TextView)findViewById(R.id.state)));
        ((Button)findViewById(R.id.btnAutoOn)).setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                AutoDetector.instance().OnDetectChange(true);
            }
        });
        ((Button)findViewById(R.id.btnManualOn)).setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) { ManualDetector.instance().OnDetectChange(true); }
        });
        ((Button)findViewById(R.id.btnReal)).setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) { RealButtonEvent.instance().OnPress(); }
        });
        ((Button)findViewById(R.id.btnFake)).setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) { FakeButtonEvent.instance().OnPress(); }
        });
    }

    public void startManualRecording() {
        // user instantiated recording
        mRecordCheckbox.setText(getString(R.string.recording_button));
    }

    private void stopManualRecording() {
        mRecordCheckbox.setText(getString(R.string.not_recording_button));
    }
}