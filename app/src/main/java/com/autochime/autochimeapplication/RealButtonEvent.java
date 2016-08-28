package com.autochime.autochimeapplication;

import java.util.ArrayList;
import java.util.List;

interface RealButtonListener {
    void onRealButtonPress();
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class RealButtonEvent {
    private static RealButtonEvent mInstance = null;
    public static RealButtonEvent instance() {
        if (mInstance == null)
            mInstance = new RealButtonEvent();
        return mInstance;
    }
    RealButtonEvent() {};

    private List<RealButtonListener> mListeners = new ArrayList<RealButtonListener>();
    public void OnPress() {
        for (RealButtonListener listener : mListeners) {
            listener.onRealButtonPress();
        }
    }
}
