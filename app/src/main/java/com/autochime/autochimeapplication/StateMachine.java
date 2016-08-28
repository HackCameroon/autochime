package com.autochime.autochimeapplication;

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
        ManualDetectListener
{
    public enum State {
        Default,
        AutoAlarm,
        ManualAlarm,
        Notify,
        PostNotify
    }
    private State mState = State.Default;

    // All useful members here
    private AudioRecorder mAudioRecorder = null;
    private AutoDetector mAutoDetector = null;
    private ManualDetector mManualDetector = null;
    private Alarm mAlarm = null;

    private static StateMachine mInstance = null;
    public static StateMachine instance() {
        if (mInstance == null)
            mInstance = new StateMachine();
        return mInstance;
    }
    StateMachine() {
        // Initialize all members here
        mAudioRecorder = AudioRecorder.instance();
        mAutoDetector = AutoDetector.instance();
        mAlarm = Alarm.instance();
        //Transition(State.Default);
    }

    // Allow specific setting of States
    public void SetState(final State newState) {
        Transition(newState);
    }

    // Event Listeners
    @Override public void onAutoDetectChange(boolean detected) { CheckState(); }
    @Override public void onManualDetectChange(boolean detected) { SetState(State.ManualAlarm); }

    // Event Handlers
    private List<TransitionListener> mListeners = new ArrayList<TransitionListener>();
    public void addListener(TransitionListener listener) {
        mListeners.add(listener);
    }
    private void OnTransition() {
        for (TransitionListener listener : mListeners) {
            listener.onTransition(mState);
        }
    }

    // Toggle specific behaviors at transition
    private void Transition(final State newState) {
        mState = newState;
        switch (mState) {
            case Default:
                if (mAudioRecorder != null) mAudioRecorder.StopRecord();
                if (mAlarm != null) mAlarm.SetState(false);
                break;
            case AutoAlarm:
                mAudioRecorder.StartRecord();
                mAlarm.SetState(true);
                break;
            case ManualAlarm:
                mAudioRecorder.StartRecord();
                mAlarm.SetState(false);
                break;
            case Notify:
                Transition(State.PostNotify);
                break;
            case PostNotify:
                mAlarm.SetState(false);
                break;
            default:
                break;
        }
        OnTransition();
    }

    private void CheckState() {
        switch (mState) {
            default:
                break;
        }
    }
}
