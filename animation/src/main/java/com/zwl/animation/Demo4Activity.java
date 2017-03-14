package com.zwl.animation;

import android.app.Activity;
import android.os.Bundle;

import com.zwl.animation.view.BubbleSurfaceView;

/**
 * Created by weilongzhang on 16/12/12.
 */

public class Demo4Activity extends Activity {

    private BubbleSurfaceView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo4);
//        view = (BubbleSurfaceView)findViewById(R.id.surfaceView);
//        view.setCircleColors(R.color.color_fecf05, R.color.color_fee36c);
//        view.start();


//        View view = this.findViewById(R.id.view);
//        ObjectAnimator translationY = ObjectAnimator.ofFloat(view, "translationY", 500, 200);
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
//        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 3f, 1f);
//        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 3f, 1f);
//        translationY.setRepeatCount(ValueAnimator.INFINITE);
//        translationY.setRepeatMode(ValueAnimator.RESTART);
//        alpha.setRepeatCount(ValueAnimator.INFINITE);
//        alpha.setRepeatMode(ValueAnimator.RESTART);
//        scaleX.setRepeatCount(ValueAnimator.INFINITE);
//        scaleX.setRepeatMode(ValueAnimator.RESTART);
//        scaleY.setRepeatCount(ValueAnimator.INFINITE);
//        scaleY.setRepeatMode(ValueAnimator.RESTART);
//        AnimatorSet up = new AnimatorSet();
//        up.playTogether(translationY, alpha, scaleX, scaleY);
//        up.setDuration(3000);
//        up.setStartDelay((long) (Math.random() * 1500));
//        up.start();

    }
}
