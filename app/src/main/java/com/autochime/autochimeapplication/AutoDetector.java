package com.autochime.autochimeapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

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
public class AutoDetector implements SensorEventListener {
    SensorManager mSensorManager = null;

    private static AutoDetector mInstance = null;
    public static AutoDetector instance() {
        if (mInstance == null)
            mInstance = new AutoDetector();
        return mInstance;
    }
    AutoDetector() {
        //mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    private List<AutoDetectListener> mListeners = new ArrayList<AutoDetectListener>();
    public void addListener(AutoDetectListener listener) {
        mListeners.add(listener);
    }
    private void OnDetectChange(boolean detected) {
        for (AutoDetectListener listener : mListeners) {
            listener.onAutoDetectChange(detected);
        }
    }
    private float mAccel = 0; // acceleration apart from gravity
    private float mAccelCurrent = SensorManager.GRAVITY_EARTH; // current
    // acceleration
    // including
    // gravity
    private float mAccelLast = SensorManager.GRAVITY_EARTH; // last
    // acceleration
    // including
    // gravity

    @Override
    public void onSensorChanged(SensorEvent se) {
        final long shs = 10;

        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];
        // M.l("x=" + x + " y=" + y + " z=" + z);
        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y));
        // mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z
        // * z));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        // if (mAccel * shs > 5 * 20) {
        if (mAccel * shs > 10 * 20) {
            OnDetectChange(true);
//					M.l("x=" + x + " y=" + y + " z=" + z);
//					M.l("mAccel=" + mAccel + " mAccelLast=" + mAccelLast
//							+ " mAccelCurrent=" + mAccelCurrent);

//					final SoundMeter sm = new SoundMeter();
//					sm.start();
//					final Handler handler = new Handler();
//					handler.postDelayed(new Runnable() {
//						@Override
//						public void run() {
//							if (sm.getAmplitude()> certainValue)
//								mFakeRingtone.play();
//							sm.stop();
//						}
//					}, 100);


        } else {
            OnDetectChange(false);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
