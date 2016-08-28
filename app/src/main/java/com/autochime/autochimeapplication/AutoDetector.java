package com.autochime.autochimeapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

interface AutoDetectListener {
    void onAutoDetectChange(boolean detected);
}

/**
 * Created by kl on 8/27/16.
 * <p/>
 * Samples
 * 1. to initiate listener call mViolentDetector = new ViolentDetector(this);
 * <p/>
 * 2. to stop listner call mViolentDetector = null;
 * <p/>
 * 3. to stop Fake ring tone, call mViolentDetector.mute();
 */
public class AutoDetector implements MotionListener, SoundListener {
    SensorManager mSensorManager = null;



    private static AutoDetector mInstance = null;
    public static AutoDetector instance() {
        if (mInstance == null)
            mInstance = new AutoDetector();
        return mInstance;
    }
    AutoDetector() {
        MotionDetector.instance().addListener(this);
    }

    long mMotion = 0;
    long mPrevMotionTime = 0;
    long mSound = 0;
    long mPrevSoundTime = 0;

    @Override public void onMotionChange(boolean detected) {
        long currTime = System.currentTimeMillis();
        mMotion = (currTime - mPrevMotionTime) / 2 + mMotion / 2;
        mPrevMotionTime  = currTime;
        CheckTotal();
    }
    @Override public void onSoundDetected() {
        long currTime = System.currentTimeMillis();
        mSound = (currTime - mPrevSoundTime) / 2 + mSound / 2;
        mPrevSoundTime = currTime;
        CheckTotal();
    }

    private void CheckTotal() {
        if (mPrevMotionTime == 0 || mPrevSoundTime == 0)
            return;
        long check = mSound + mMotion;
        Log.d("ASD", Long.toString(check));
    }

    boolean mIsDetected = false;
    private List<AutoDetectListener> mListeners = new ArrayList<AutoDetectListener>();
    public void addListener(AutoDetectListener listener) { mListeners.add(listener); }
    public void OnDetectChange(boolean detected) {
        mIsDetected = detected;
        for (AutoDetectListener listener : mListeners) {
            listener.onAutoDetectChange(mIsDetected);
        }
    }
    public boolean IsDetected() { return mIsDetected; }
}
