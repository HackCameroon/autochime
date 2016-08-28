package com.autochime.autochimeapplication;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by Wilbur on 08/27/16.
 */
public class Alarm implements TransitionListener
{
    private MediaPlayer mMp;

    private static Alarm mInstance = null;
    public static Alarm instance() {
        if (mInstance == null)
            mInstance = new Alarm();
        return mInstance;
    }

    Alarm() {
        StateMachine.instance().addListener(this);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mMp = MediaPlayer.create(AutoChimeApplication.getAppContext(), notification);
        mMp.setLooping(true);
    }

    @Override public void onTransition(StateMachine.State state) {
        switch (state) {
            case AutoAlarm:
                SetState(true);
                break;
            default:
                SetState(false);
                break;
        }
    }

    public void SetState(final boolean state) {
        if (state) {
            if(!mMp.isPlaying()) {
                mMp.start();
            }
        } else {
            if (mMp.isPlaying()) {
                mMp.pause();
            }
        }
    }
}
