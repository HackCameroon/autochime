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
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        mMp = MediaPlayer.create(AutoChimeApplication.getAppContext(), notification);
    }

    @Override public void onTransition(StateMachine.State state) {
        switch (state) {
            case Default:
                SetState(false);
                break;
            case AutoAlarm:
                SetState(true);
                break;
            case ManualAlarm:
                SetState(false);
                break;
            case Notify:
                break;
            case PostNotify:
                SetState(false);
                break;
            default:
                break;
        }
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
