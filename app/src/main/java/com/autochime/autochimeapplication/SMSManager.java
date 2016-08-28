package com.autochime.autochimeapplication;

import android.os.Build;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by tianaim on 8/27/16.
 */
public class SMSManager {
    SmsManager smsManager = null;

    private String dummyPhoneNo = null;
    private String dummyPhoneNo2 = null;
    private String dummyMessageBody = null;
    private String dummyDownloadBody = null;


    public SMSManager() {
        smsManager = SmsManager.getDefault();

        dummyPhoneNo = "5554"; // first emulator
        dummyPhoneNo2 = "5556"; // second emulator
        dummyMessageBody = "Your friend Kelly may be in danger -  listen to this recording from her phone. https://rec.autochime.com/c3a564b6. Location: <27.1960, 78.0212>. <If you think she is in danger, click here to send her help. If you think she is fine, ignore this message.";
        dummyDownloadBody = "<Download audio>";
    }

    public void sendHardcode() {
        try {
            ArrayList<String> parts = smsManager.divideMessage(dummyMessageBody);
            smsManager.sendMultipartTextMessage(dummyPhoneNo, null, parts, null, null);
            smsManager.sendMultipartTextMessage(dummyPhoneNo2, null, parts, null, null);
            smsManager.sendTextMessage(dummyPhoneNo, null, dummyDownloadBody, null, null);
            smsManager.sendTextMessage(dummyPhoneNo2, null, dummyDownloadBody, null, null);

        } catch (Exception e) {
            e.printStackTrace();
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
