package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.zwl.animation.R;

/**
 * Created by weilongzhang on 17/3/9.
 */

public class BubbleUpViewGroup extends RelativeLayout {

    private int count = 30;

    private boolean destroy = false;

    public BubbleUpViewGroup(Context context) {
        super(context);
    }

    public BubbleUpViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
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
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dot.setLayoutParams(lp);
        dot.setBackgroundColor(Color.RED);
        addView(dot);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(dot, "translationY", 0, -this.getMeasuredHeight()
        ), ObjectAnimator.ofFloat(dot, "alpha", 0, 1, 0));
        set.setDuration(1000);
        set.setStartDelay((long) (Math.random() * 1000));
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                dot.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (destroy)
                    return;
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

    public void clear() {
        destroy = true;
        int count = this.getChildCount();
        if (count > 0) {
            for (int i = 0 ; i < count; i ++) {
                getChildAt(i).clearAnimation();
            }
        }
        this.removeAllViews();
    }
}
