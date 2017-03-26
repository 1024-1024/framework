package com.zwl.butterknife;

import android.app.Activity;

/**
 * Created by weilongzhang on 17/3/26.
 */

public class InjectView {

    public static void bind(Activity activity) {
        String clzName = activity.getClass().getName();
        try {
            Class clz = Class.forName(clzName + "$$ViewBinder");
            ViewBinder<Activity> viewBinder = (ViewBinder<Activity>) clz.newInstance();
            viewBinder.bind(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
