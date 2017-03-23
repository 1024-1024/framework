package com.zwl.animation.view.bubble;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by weilongzhang on 17/3/14.
 */

public class BubbleView extends View {

    private Paint mPaint;
    private int width;
    private int height;
    private ValueAnimator scale;
    private ValueAnimator up;
    private float scaleValue;
    private float upValue;
    private int count = 20;
    private float radius = 20;

    public BubbleView(Context context) {
        super(context);
        init();
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);

        scale = ValueAnimator.ofFloat(0, 1);
        scale.setDuration(2000);
        scale.setRepeatCount(ValueAnimator.INFINITE);
        scale.setRepeatMode(ValueAnimator.RESTART);
        scale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                scaleValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });

        up = ValueAnimator.ofFloat(0, 1);
        up.setDuration(1000);
        up.setRepeatCount(ValueAnimator.INFINITE);
        up.setRepeatMode(ValueAnimator.RESTART);
        up.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                upValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        scale.start();
//        up.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (width == 0 || height == 0) {
            return;
        }
        for (int i = 0; i < count; i ++) {
            canvas.drawCircle(width / count * i, height, scaleValue * radius, mPaint);
        }
    }
}
