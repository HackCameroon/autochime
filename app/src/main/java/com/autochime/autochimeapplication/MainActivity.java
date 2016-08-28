package com.autochime.autochimeapplication;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

import com.autochime.autochimeapplication.fragments.AddContactFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // just testing
        SmsManager smsManager1 = SmsManager.getDefault();
        smsManager1.sendTextMessage("5554", null, "ugh", null, null);
        smsManager1.sendTextMessage("5556", null, "ugh2", null, null); // send to both just in case the emulators get mixed up during demos

        SMSManager smsManager = new SMSManager();
        smsManager.send();

        // Show contact selector
//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.add(AddContactFragment.newInstance(), null);
//        ft.commit();
    }
}
