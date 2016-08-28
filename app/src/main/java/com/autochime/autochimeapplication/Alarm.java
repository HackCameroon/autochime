package com.autochime.autochimeapplication;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Wilbur on 08/27/16.
 */
public class Alarm
{
    private MediaPlayer mMp;

    private static Alarm mInstance = null;
    public static Alarm instance() {
        if (mInstance == null)
            mInstance = new Alarm();
        return mInstance;
    }
    Alarm() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        //mMp = MediaPlayer.create(this, notification);
    }

    public void SetState(final boolean state) {
        if (state) {
            mMp.setLooping(true);
            mMp.start();
        } else {
            mMp.stop();
        }
    }
}
