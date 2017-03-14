package com.zwl.animation;

import android.app.Application;
import android.content.Context;

/**
 * Created by weilongzhang on 17/3/14.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }

    public static Context getConText() {
        return mContext;
    }
}
