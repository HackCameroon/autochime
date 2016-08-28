package com.autochime.autochimeapplication;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Process;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

interface SoundListener {
    void onSoundDetected();
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class SoundDetector implements Runnable {
    private boolean isRecording = false;

    private static SoundDetector mInstance = null;
    public static SoundDetector instance() {
        if (mInstance == null)
            mInstance = new SoundDetector();
        return mInstance;
    }
    SoundDetector() {}

    private List<SoundListener> mListeners = new ArrayList<SoundListener>();
    public void addListener(SoundListener listener) { mListeners.add(listener); }
    public void OnDetect() { for (SoundListener listener : mListeners) listener.onSoundDetected(); }

    public void Start() {
        isRecording = true;
    }

    public void Pause() {
        isRecording = false;
    }

    String str(double value) {
        return Double.toString(value);
    }

    void Process(short[] buffer) {
        double[] bufferD = new double[buffer.length];
        double[] bufferD2 = new double[buffer.length];
        for(int i = 0; i < buffer.length; i++) {
            bufferD[i] = (double)buffer[i] / 32767.0;
            bufferD2[i] = 0.0;
        }
        FFT fft = new FFT(128);
        fft.fft(bufferD, bufferD2);
        double total = 0;
        for(int i = 0; i < bufferD.length; ++i) {
            total += Math.abs(bufferD[i] * i * i);
        }
        if (total > 2000000) {
            OnDetect();
        }
    }

    @Override
    public void run() {
        android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
        AudioRecord recorder = null;
        short[][] buffers = new short[256][160];
        int ix = 0;
        try {
            int iSampleRate = AudioTrack.getNativeOutputSampleRate(AudioManager.STREAM_SYSTEM);
            int bufferSize = AudioRecord.getMinBufferSize(iSampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
            recorder = new AudioRecord(MediaRecorder.AudioSource.MIC, iSampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
            while (true) {
                if (isRecording) {
                    if (recorder.getRecordingState() == AudioRecord.RECORDSTATE_STOPPED)
                        recorder.startRecording();
                    short[] buffer = buffers[ix++ % buffers.length];
                    bufferSize = recorder.read(buffer, 0, buffer.length);
                    Process(buffer);
                } else {
                    if (recorder.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING)
                        recorder.stop();
                }
            }
        } catch (Throwable x) {
            Log.e("SoundDetector", "Error analyzing voice");
        } finally {
            close();
        }
    }

    private void close(){
        isRecording = false;
    }
}
