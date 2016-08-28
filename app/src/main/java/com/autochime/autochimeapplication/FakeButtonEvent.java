package com.autochime.autochimeapplication;

import java.util.ArrayList;
import java.util.List;

interface FakeButtonListener {
    void onFakeButtonPress();
}

/**
 * Created by Wilbur on 08/28/16.
 */
public class FakeButtonEvent {
    private static FakeButtonEvent mInstance = null;
    public static FakeButtonEvent instance() {
        if (mInstance == null)
            mInstance = new FakeButtonEvent();
        return mInstance;
    }
    FakeButtonEvent() {};

    private List<FakeButtonListener> mListeners = new ArrayList<FakeButtonListener>();
    public void addListener(FakeButtonListener listener) { mListeners.add(listener); }
    public void OnPress() { for (FakeButtonListener listener : mListeners) listener.onFakeButtonPress(); }
}
