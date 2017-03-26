package com.zwl.designpattern;

import android.app.Application;
import android.content.Context;

/**
 * Created by weilongzhang on 17/3/26.
 */

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
