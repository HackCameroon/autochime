package com.autochime.autochimeapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {
    CheckBox mRecordCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StateMachine stateMachine = new StateMachine(this);

        // just testing
        SMSManager smsManager = new SMSManager();
        smsManager.sendHardcode();

        mRecordCheckbox = (CheckBox) findViewById(R.id.main_record_button);
        mRecordCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startManualRecording();
                } else {
                    stopManualRecording();
                }
            }
        });

        // Show contact selector
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(AddContactFragment.newInstance(), null);
//        ft.commit();
    }

    public void startManualRecording() {
        // user instantiated recording
        mRecordCheckbox.setText(getString(R.string.recording_button));
    }

    private void stopManualRecording() {
        mRecordCheckbox.setText(getString(R.string.not_recording_button));
    }
}
