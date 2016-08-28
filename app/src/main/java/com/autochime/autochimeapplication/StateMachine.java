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
        FakeButtonListener,
        TimerListener
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
        Timer.instance();

        SetState(State.Default);
    }

    // Allow specific setting of States
    public void SetState(final State newState) {
        Transition(newState);
        if (newState == State.Notify)
            SetState(State.PostNotify);
    }

    // Event Listeners
    @Override public void onAutoDetectChange(boolean detected) {
        switch (mState) {
            case Default:
                if (detected) SetState(State.AutoAlarm);
                break;
            default:
                break;
        }
    }
    @Override public void onManualDetectChange(boolean detected) {
        switch(mState) {
            case Default:
                if (detected) SetState(State.ManualAlarm);
                break;
            default:
                break;
        }
    }
    @Override public void onRealButtonPress() {
        switch (mState) {
            case AutoAlarm:
            case PostNotify:
                SetState(State.Default);
                break;
            default:
                break;
        }
    };
    @Override public void onFakeButtonPress() {
        switch (mState) {
            case AutoAlarm:
                SetState(State.Notify);
                break;
            default:
                break;
        }
    };
    @Override public void onTimerExpire() {
        switch (mState) {
            case AutoAlarm:
                SetState(State.Notify);
                break;
            default:
                break;
        }
    }

    // Event Handlers
    private List<TransitionListener> mListeners = new ArrayList<TransitionListener>();
    private void Transition(final State newState) {
        mState = newState;
        for (TransitionListener listener : mListeners) {
            listener.onTransition(mState);
        }
    }
}
