package com.autochime.autochimeapplication;

import android.location.Location;
import android.media.AudioManager;

import com.autochime.autochimeapplication.database.Database;

import java.util.ArrayList;
import java.util.List;

interface TransitionListener {
    void onTransition(StateMachine.State state);
}

/**
 * Created by Wilbur on 08/27/16.
 */
public class StateMachine implements
        AutoDetectListener,
        ManualDetectListener,
        RealButtonListener,
        FakeButtonListener,
        TimerListener,
        GPSListener
{
    public enum State {
        Default,
        AutoAlarm,
        ManualAlarm,
        Notify,
        PostNotify
    }
    private State mState = State.Default;

    private Location mLocation;

    private static StateMachine mInstance = null;
    public static StateMachine instance() {
        if (mInstance == null)
            mInstance = new StateMachine();
        return mInstance;
    }
    StateMachine() {
        // Initialize all singletons here
        AutoDetector.instance().addListener(this);
        ManualDetector.instance().addListener(this);
        RealButtonEvent.instance().addListener(this);
        FakeButtonEvent.instance().addListener(this);
        Timer.instance().addListener(this);
        GPSRetriever.instance().addListener(this);
        SoundDetector sound = SoundDetector.instance();
        new Thread(sound).start();
        sound.Start();

        SetState(State.Default);
    }

    // Allow specific setting of States
    public void SetState(final State newState) {
        Transition(newState);
        switch (newState) {
            case AutoAlarm:
                Timer.instance().Start(5000);
                break;
            case ManualAlarm:
                SetState(State.Notify);
                break;
            case Notify:
                sendSMS();
                SetState(State.PostNotify);
                break;
            default:
                break;
        }
    }

    // Event Listeners
    @Override public void onAutoDetectChange(boolean detected) {
        switch (mState) {
            case Default:
                if (detected) {
                    startCollectingEvidence();
                    SetState(State.AutoAlarm);
                }
                break;
            default:
                break;
        }
    }
    @Override public void onManualDetectChange(boolean detected) {
        switch(mState) {
            case Default:
                if (detected) {
                    startCollectingEvidence();
                    SetState(State.ManualAlarm);
                }
                break;
            default:
                break;
        }
    }
    @Override public void onRealButtonPress() {
        switch (mState) {
            case AutoAlarm:
            case ManualAlarm:
            case PostNotify:
                SetState(State.Default);
                stopCollectingEvidence();
                break;
            default:
                break;
        }
    }
    @Override public void onFakeButtonPress() {
        switch (mState) {
            case AutoAlarm:
                SetState(State.Notify);
                break;
            default:
                break;
        }
    }
    @Override public void onTimerExpire() {
        switch (mState) {
            case AutoAlarm:
                SetState(State.Notify);
                break;
            default:
                break;
        }
    }

    @Override
    public void onGPSUpdate(Location location) {
        mLocation = location;
    }

    private void startCollectingEvidence() {
        AudioRecorder.instance().StartRecord();
        GPSRetriever.instance().getLocation();
    }

    private void stopCollectingEvidence() {
        AudioRecorder.instance().StopRecord();
        if (mLocation != null) {
            Database.getInstance().saveRecordingEntry(
                    mLocation.getLatitude(),
                    mLocation.getLongitude(),
                    AudioRecorder.instance().getFileName());
        } else {
            Database.getInstance().saveRecordingEntry(
                    null,
                    null,
                    AudioRecorder.instance().getFileName());
        }
    }

    private void sendSMS() {
        SMSManager smsManager = new SMSManager();
        smsManager.sendHardcode();
    }

    // Event Handlers
    private List<TransitionListener> mListeners = new ArrayList<TransitionListener>();
    public void addListener(TransitionListener listener) { mListeners.add(listener); }
    private void Transition(final State newState) {
        mState = newState;
        for (TransitionListener listener : mListeners) {
            listener.onTransition(mState);
        }
    }
}
