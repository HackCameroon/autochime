package com.autochime.autochimeapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by Wilbur on 08/27/16.
 */
public class Alarm
{
    MediaPlayer m_mp;

    public Alarm(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        m_mp = MediaPlayer.create(context, notification);
    }

    public void SetState(final boolean state) {
        if (state) {
            m_mp.setLooping(true);
            m_mp.start();
        }else {
            m_mp.stop();
        }
    }
}
