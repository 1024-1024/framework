package com.zwl.animation.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.why.utils.SizeUtils;


/**
 * Created by weilongzhang on 17/3/9.
 */

public class BubbleUpViewGroup extends RelativeLayout {

    private int count = 50;

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
        final BubbleView dot = new BubbleView(getContext());
        RelativeLayout.LayoutParams lp = new LayoutParams(SizeUtils.dp2px(getContext(), 4),
                SizeUtils.dp2px(getContext(), 4));
        lp.leftMargin = (int) (Math.random() * this.getMeasuredWidth());
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        dot.setLayoutParams(lp);
        addView(dot);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(dot, "translationY", 0f, -this.getMeasuredHeight())
                , ObjectAnimator.ofFloat(dot, "alpha", 0f, 1f, 0f)
                , ObjectAnimator.ofFloat(dot, "scaleX", 2f, 1f)
                , ObjectAnimator.ofFloat(dot, "scaleY", 2f, 1f));
        AnimatorSet set1 = new AnimatorSet();
        set1.playTogether(ObjectAnimator.ofFloat(dot, "scaleX", 1f, 4f)
                , ObjectAnimator.ofFloat(dot, "scaleY", 1f, 4f));

        AnimatorSet first = new AnimatorSet();
        first.play(set).after(set1);
        first.setDuration(1500);
        first.setStartDelay((long) (Math.random() * 2000));
        first.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                dot.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (destroy) return;
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
        first.start();
    }

    public void clear() {
        destroy = true;
        int count = this.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                getChildAt(i).clearAnimation();
            }
        }
        this.removeAllViews();
    }
}
