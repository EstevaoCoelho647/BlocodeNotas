package com.sqisland.android.advanced_textview;

import android.app.Application;

public class NoteManagerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());
    }
}

