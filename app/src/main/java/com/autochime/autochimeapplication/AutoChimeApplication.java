package com.autochime.autochimeapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by amytsai on 8/28/16.
 */
public class AutoChimeApplication extends Application {

    private static AutoChimeApplication sApplicationInstance;

    public static Context getAppContext()
    {
        return sApplicationInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplicationInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
