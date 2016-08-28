package com.autochime.autochimeapplication;

import android.os.Build;
import android.telephony.SmsManager;

/**
 * Created by tianaim on 8/27/16.
 */
public class SMSManager {

    public final String dummyPhoneNo = "5554"; // first emulator
    public final String dummyPhoneNo2 = "5556"; // second emulator
    public final String dummyMessageBody = "Your friend Kelly may be in danger -  listen to this audio recording from her phone. http://recording.autochime.com/r/c3a564b6 If you think she is in danger, click here to call 911. If you don't, ignore this message.";

    public SMSManager() {
    }

    // default method with hard coded messages
    public void send() {
        if (Build.VERSION.SDK_INT < 23) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
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
