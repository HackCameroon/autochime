package com.autochime.autochimeapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Wilbur on 08/27/16.
 */
public class AudioRecorder extends Activity {
    private MediaRecorder m_mr = null;
    private MediaPlayer m_mp = null;
    private String m_filename = null;

    public AudioRecorder() {
        m_filename = Environment.getExternalStorageDirectory().getAbsolutePath();
        m_filename += "/audio.3gp";
    }

    public void StartRecord() {
        m_mr = new MediaRecorder();
        m_mr.setAudioSource(MediaRecorder.AudioSource.MIC);
        m_mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        m_mr.setOutputFile(m_filename);
        m_mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            m_mr.prepare();
        } catch (IOException e) {
            Log.e("AudioRecorder", "StartRecord failed");
        }
        m_mr.start();
    }

    public void StopRecord() {
        if (m_mr == null)
            return;
        try {
            m_mr.stop();
            m_mr.release();
        } catch (RuntimeException e) {
            Log.e("AudioRecorder", "StopRecord failed");
        }
        m_mr = null;
    }

    public void StartPlayback() {
        m_mp = new MediaPlayer();
        try {
            m_mp.setDataSource(m_filename);
            m_mp.prepare();
            m_mp.start();
        } catch (IOException e) {
            Log.e("AudioRecorder", "StartPlayback failed");
        }
    }

    public void StopPlayback() {
        if (m_mp == null)
            return;
        m_mp.release();
        m_mp = null;
    }
}