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
        ManualDetectListener,
        RealButtonListener,
        FakeButtonListener
{
    public enum State {
        Default,
        AutoAlarm,
        ManualAlarm,
        Notify,
        PostNotify
    }
    private State mState = State.Default;

    private static StateMachine mInstance = null;
    public static StateMachine instance() {
        if (mInstance == null)
            mInstance = new StateMachine();
        return mInstance;
    }
    StateMachine() {
        // Initialize all singletons here
        AutoDetector.instance();
        ManualDetector.instance();
        RealButtonEvent.instance();
        FakeButtonEvent.instance();
        Alarm.instance();
        AudioRecorder.instance();

        SetState(State.Default);
    }

    // Allow specific setting of States
    public void SetState(final State newState) {
        Transition(newState);
        if (newState == State.Notify)
            SetState(State.PostNotify);
    }

    // Event Listeners
    @Override public void onAutoDetectChange(boolean detected) { CheckState(); }
    @Override public void onManualDetectChange(boolean detected) { SetState(State.ManualAlarm); }
    @Override public void onRealButtonPress() {};
    @Override public void onFakeButtonPress() {};

    // Event Handlers
    private List<TransitionListener> mListeners = new ArrayList<TransitionListener>();
    public void addListener(TransitionListener listener) {
        mListeners.add(listener);
    }
    private void Transition(final State newState) {
        mState = newState;
        for (TransitionListener listener : mListeners) {
            listener.onTransition(mState);
        }
    }

    private void CheckState() {
        switch (mState) {
            default:
                break;
        }
    }
}
