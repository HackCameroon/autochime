package com.autochime.autochimeapplication;

import android.os.Build;
import android.telephony.SmsManager;

/**
 * Created by tianaim on 8/27/16.
 */
public class SMSManager {
    SmsManager smsManager = null;

    private String dummyPhoneNo = null;
    private String dummyPhoneNo2 = null;
    private String dummyMessageBody = null;

    public SMSManager() {
        smsManager = SmsManager.getDefault();

        dummyPhoneNo = "5554"; // first emulator
        dummyPhoneNo2 = "5556"; // second emulator
        dummyMessageBody = "Your friend Kelly may be in danger -  listen to this recording from her phone. https://rec.autochime.com/c3a564b6. If you think she is in danger....";
    }

    public void sendHardcode() {
        if (Build.VERSION.SDK_INT < 23) {
            try {
                smsManager.sendTextMessage(dummyPhoneNo, null, dummyMessageBody, null, null);
                smsManager.sendTextMessage(dummyPhoneNo2, null, dummyMessageBody, null, null); // send to both just in case the emulators get mixed up during demos
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
    }

    public void send(String userName, String phoneNo, String messageBody) {
        if (Build.VERSION.SDK_INT < 23) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, messageBody + " " + userName, null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
