package com.autochime.autochimeapplication;

import java.util.ArrayList;
import java.util.List;

interface ManualDetectListener {
    void onManualDetectChange(boolean detected);
}
/**
 * Created by Wilbur on 08/28/16.
 */
public class ManualDetector {
    private static ManualDetector mInstance = null;
    public static ManualDetector instance() {
        if (mInstance == null)
            mInstance = new ManualDetector();
        return mInstance;
    }
    ManualDetector() {}

    private boolean mIsDetected;
    private List<ManualDetectListener> mListeners = new ArrayList<ManualDetectListener>();
    public void addListener(ManualDetectListener listener) {
        mListeners.add(listener);
    }
    private void OnDetectChange(boolean detected) {
        mIsDetected = detected;
        for (ManualDetectListener listener : mListeners) {
            listener.onManualDetectChange(mIsDetected);
        }
    }
    public boolean IsDetected() { return mIsDetected; }
}
