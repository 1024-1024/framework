package com.zwl;

import android.app.Application;
import android.content.Context;

/**
 * Created by weilongzhang on 17/1/12.
 */

public class App extends Application {

    public static Context appContext;
    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
