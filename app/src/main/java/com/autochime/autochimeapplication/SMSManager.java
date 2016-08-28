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
    private String testPhoneNo = null;

    private String dummyMessageBody = null;
    private String dummyDownloadBody = null;


    public SMSManager() {
        smsManager = SmsManager.getDefault();

        testPhoneNo = "5554"; // first emulator

        dummyPhoneNo = "5554"; // first emulator
        dummyPhoneNo2 = "5556"; // second emulator
        testPhoneNo = "17476667469";

        dummyMessageBody = "Your friend Wanda may be in danger. Please review: http://autochime.imtiana.com/ Location: 37.772533, -122.444014 near Facebook HQ, Menlo Park, CA. Reply 'HELP' to send her help.";
        dummyDownloadBody = "Review this recording from her phone: <Download audio>";
    }

    public void sendHardcode() {
        try {
//            ArrayList<String> parts = smsManager.divideMessage(dummyMessageBody);
//
//            smsManager.sendMultipartTextMessage(dummyPhoneNo, null, parts, null, null);
//            smsManager.sendMultipartTextMessage(dummyPhoneNo2, null, parts, null, null);
//            smsManager.sendTextMessage(dummyPhoneNo, null, dummyDownloadBody, null, null);
//            smsManager.sendTextMessage(dummyPhoneNo2, null, dummyDownloadBody, null, null);

            ArrayList<String> parts = smsManager.divideMessage(dummyMessageBody);

            smsManager.sendMultipartTextMessage(testPhoneNo, null, parts, null, null);
            smsManager.sendTextMessage(testPhoneNo, null, dummyDownloadBody, null, null);
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
