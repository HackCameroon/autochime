package com.autochime.autochimeapplication;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

interface TimerListener {
    void onTimerExpire();
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class Timer implements TransitionListener {
    private static Timer mInstance = null;
    public static Timer instance() {
        if (mInstance == null)
            mInstance = new Timer();
        return mInstance;
    }
    Timer() {
        StateMachine.instance().addListener(this);
    }

    private List<TimerListener> mListeners = new ArrayList<TimerListener>();
    private void OnExpire() {
        for (TimerListener listener : mListeners) {
            listener.onTimerExpire();
        }
    }

    @Override public void onTransition(StateMachine.State state) {
        switch (state) {
            case AutoAlarm:
                Start(5000); // 5 second
                break;
            default:
                break;
        }
    }

    public void Start(int milliseconds) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                OnExpire();
            }
        }, milliseconds);
    }
}
