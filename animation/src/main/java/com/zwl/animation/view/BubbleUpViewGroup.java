package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by weilongzhang on 17/3/9.
 */

public class BubbleUpViewGroup extends RelativeLayout {

    private int count = 20;

    public BubbleUpViewGroup(Context context) {
        super(context);
    }

    public BubbleUpViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        init();
    }

    private void init() {
        for (int i = 0; i < count; i++) {
            startBubbleUpView();
        }
    }

    private void startBubbleUpView() {
        final View dot = new View(getContext());
        RelativeLayout.LayoutParams lp = new LayoutParams(8, 8);
        lp.leftMargin = (int) (Math.random() * this.getMeasuredWidth());
        lp.bottomMargin = 8;
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dot.setLayoutParams(lp);
        dot.setBackgroundColor(Color.RED);
        dot.setVisibility(GONE);
        addView(dot);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(dot, "translationY", 0, -this.getMeasuredHeight()
        ), ObjectAnimator.ofFloat(dot, "alpha", 1, 0));
        set.setDuration(1000);
        set.setStartDelay((long) (Math.random() * 1000));
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                dot.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                dot.clearAnimation();
                BubbleUpViewGroup.this.removeView(dot);
                startBubbleUpView();
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        set.start();
    }

}
