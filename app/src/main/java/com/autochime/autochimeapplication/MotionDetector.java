package com.autochime.autochimeapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

interface MotionListener {
    void onMotionChange(boolean detected);
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class MotionDetector implements SensorEventListener {
    SensorManager mSensorManager = null;
    boolean mIsDetected = false;

    private static MotionDetector mInstance = null;
    public static MotionDetector instance() {
        if (mInstance == null)
            mInstance = new MotionDetector();
        return mInstance;
    }
    MotionDetector() {
        Context context = AutoChimeApplication.getAppContext();
        mSensorManager = (SensorManager)context.getSystemService(context.SENSOR_SERVICE);
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    private List<MotionListener> mListeners = new ArrayList<MotionListener>();
    public void addListener(MotionListener listener) { mListeners.add(listener); }
    public void OnDetectChange(boolean detected) {
        mIsDetected = detected;
        for (MotionListener listener : mListeners) {
            listener.onMotionChange(mIsDetected);
        }
    }
    public boolean IsDetected() { return mIsDetected; }

    private float mAccel = 0; // acceleration apart from gravity

    @Override
    public void onSensorChanged(SensorEvent se) {
        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];
        // M.l("x=" + x + " y=" + y + " z=" + z);
        float newAccel = (float) Math.sqrt((double) (x * x + y * y + z * z));
        // mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z
        // * z));
        float delta = 0.2f;
        mAccel = mAccel * delta + newAccel * (1.0f - delta); // perform low-cut filter
        float threshold = 15;
        float movement = mAccel;
        if (movement > threshold) {
            if (!mIsDetected)
                OnDetectChange(true);
        } else {
            if (mIsDetected)
                OnDetectChange(false);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
