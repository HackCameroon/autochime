package com.autochime.autochimeapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.util.Date;

public class AudioRecorder implements TransitionListener
{
    private MediaRecorder m_mr = null;
    private MediaPlayer m_mp = null;
    private String m_filename = null;

    private static AudioRecorder mInstance = null;
    public static AudioRecorder instance() {
        if (mInstance == null)
            mInstance = new AudioRecorder();
        return mInstance;
    }
    AudioRecorder() {
        StateMachine.instance().addListener(this);
        m_filename = Environment.getExternalStorageDirectory().getAbsolutePath();
        m_filename += "/audio-" +
                      DateFormat.getDateTimeInstance().format(new Date()).replaceAll(":", "-") +
                      ".3gp";
    }

    public String getFileName() {
        return m_filename;
    }

    @Override public void onTransition(StateMachine.State state) {
        switch (state) {
            case Default:
                StopRecord();
                break;
            default:
                StartRecord();
                break;
        }
    }

    public void StartRecord() {
        if (m_mr != null)
            return;
        SoundDetector.instance().Pause();
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

    public String StopRecord() {
        if (m_mr == null)
            return null;
        try {
            m_mr.stop();
            m_mr.reset();
            m_mr.release();
        } catch (RuntimeException e) {
            Log.e("AudioRecorder", "StopRecord failed");
        }
        m_mr = null;
        SoundDetector.instance().Start();
        return m_filename;
    }

    public void StartPlayback(String fileName) {
        MediaPlayer mp = new MediaPlayer();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            mp.setDataSource(fileInputStream.getFD());
            mp.prepare();
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });
        } catch (IOException e) {
            Log.e("AudioRecorder", "StartPlayback failed");
            mp.release();
        }
    }

    public void StopPlayback() {
        if (m_mp == null)
            return;
        m_mp.release();
        m_mp = null;
    }
}
