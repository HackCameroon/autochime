package com.autochime.autochimeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {
    CheckBox mRecordCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StateMachine.instance();
        Alarm.instance();
        AudioRecorder.instance();
        GPSRetriever.instance();

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        // just testing
//        SMSManager smsManager = new SMSManager();
//        smsManager.sendHardcode();

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
//
//        t = (TextView) findViewById(R.id.textView);
//        b = (Button) findViewById(R.id.button);
//
    }

    public void startManualRecording() {
        mRecordCheckbox.setText(getString(R.string.recording_button));
        ManualDetector.instance().OnDetectChange(true);
    }

    private void stopManualRecording() {
        mRecordCheckbox.setText(getString(R.string.not_recording_button));
        StateMachine.instance().SetState(StateMachine.State.Default);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_history:
                Intent intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
