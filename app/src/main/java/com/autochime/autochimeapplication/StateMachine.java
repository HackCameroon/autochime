package com.autochime.autochimeapplication;

class StateEventListener {
    public void onDefault() {};
    public void onAutoAlarm() {};
    public void onManualAlarm() {};
    public void onNotify() {};
    public void onPostNotify() {};
}

/**
 * Created by Wilbur on 08/27/16.
 */
public class StateMachine {
    enum State extends StateEventListener {
        Default {
            @Override public void onDefault() {}
            @Override public void onAutoAlarm() {}
            @Override public void onManualAlarm() {}
            @Override public void onNotify() {}
            @Override public void onPostNotify() {}
        },
        AutoAlarm {
            @Override public void onDefault() {}
            @Override public void onAutoAlarm() {}
            @Override public void onManualAlarm() {}
            @Override public void onNotify() {}
            @Override public void onPostNotify() {}
        },
        ManualAlarm {
            @Override public void onDefault() {}
            @Override public void onAutoAlarm() {}
            @Override public void onManualAlarm() {}
            @Override public void onNotify() {}
            @Override public void onPostNotify() {}
        },
        Notify {
            @Override public void onDefault() {}
            @Override public void onAutoAlarm() {}
            @Override public void onManualAlarm() {}
            @Override public void onNotify() {}
            @Override public void onPostNotify() {}
        },
        PostNotify {
            @Override public void onDefault() {}
            @Override public void onAutoAlarm() {}
            @Override public void onManualAlarm() {}
            @Override public void onNotify() {}
            @Override public void onPostNotify() {}
        }
    }
    private State mState;

    public StateMachine() {
        mState = State.Default;
    }

    private void Transition(final State newState) {
        mState = newState;
        switch(mState) {
            case Default:
                break;
            case AutoAlarm:
                break;
            case ManualAlarm:
                break;
            case Notify:
                break;
            case PostNotify:
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
