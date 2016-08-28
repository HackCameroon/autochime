package com.autochime.autochimeapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Wilbur on 08/27/16.
 */
public class AudioRecorderTester {
    AudioRecorder m_ar = null;
    boolean m_isRecording = false;
    boolean m_isPlaying = false;

    public AudioRecorderTester(AudioRecorder ar, Button record, Button play) {
        m_ar = ar;
        record.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (m_isRecording) {
                    Log.d("A", "STOP");
                    m_ar.StopRecord();
                } else {
                    Log.d("A", "START");
                    m_ar.StartRecord();
                }
                m_isRecording = !m_isRecording;
            }
        });
        play.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if (m_isPlaying) {
                    m_ar.StopPlayback();
                } else {
//                    m_ar.StartPlayback();
                }
                m_isPlaying = !m_isPlaying;
            }
        });
    }
}
