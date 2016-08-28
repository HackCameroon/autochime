package com.autochime.autochimeapplication;

import java.util.ArrayList;
import java.util.List;

enum State {
    Default,
    AutoAlarm,
    ManualAlarm,
    Notify,
    PostNotify
}

interface TransitionListener {
    void onTransition(State state);
}

/**
 * Created by Wilbur on 08/27/16.
 */
public class StateMachine {
    private State mState = State.Default;

    private AudioRecorder mAudioRecorder = null;

    public StateMachine() {
        mAudioRecorder = new AudioRecorder();
    }

    private List<TransitionListener> mListeners = new ArrayList<TransitionListener>();
    public void addTransitionListener(TransitionListener listener) {
        mListeners.add(listener);
    }
    private void OnTransition() {
        for (TransitionListener listener : mListeners) {
            listener.onTransition(mState);
        }
    }

    private void Transition(final State newState) {
        mState = newState;
        switch (mState) {
            case Default:
                mAudioRecorder.StopRecord();
                break;
            case AutoAlarm:
                mAudioRecorder.StartRecord();
                break;
            case ManualAlarm:
                mAudioRecorder.StartRecord();
                break;
            case Notify:
                Transition(State.PostNotify);
                break;
            case PostNotify:
                break;
            default:
                break;
        }
        OnTransition();
    }

    private void CheckState() {

    }
}
