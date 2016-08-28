package com.autochime.autochimeapplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

interface AutoDetectListener {
    void onAutoDetect();
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
public class AutoDetector {
    SensorEventListener mSL;
    Context mContext;
    private boolean mPlaying = false;

    public AutoDetector(Context c) {
        mContext = c;
        regVioListner();
    }

    private List<AutoDetectListener> mListeners = new ArrayList<AutoDetectListener>();
    public void addListener(AutoDetectListener listener) {
        mListeners.add(listener);
    }
    private void OnDetect() {
        for (AutoDetectListener listener : mListeners) {
            listener.onAutoDetect();
        }
    }

    /**
     * register violent listener
     */
    private void regVioListner() {
        Log.v("ViolentDetection", "regVioListner");
        mSL = new SensorEventListener() {
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
                if (!mPlaying && mAccel * shs > 10 * 20) {
                    mPlaying = true;
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


                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {


            }
        };
        SensorManager sm = (SensorManager)
                mContext.getSystemService(Context.SENSOR_SERVICE);
        sm.registerListener(mSL, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    private void unregVioListner() {
        if (mSL != null) {
            // M.l("ss0.");
            SensorManager sm = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
            sm.unregisterListener(mSL);
            mSL = null;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        unregVioListner();
    }
}
