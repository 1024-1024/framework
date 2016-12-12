package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by weilongzhang on 16/12/12.
 */

public class BezierWaterView extends View {

    private Path mPath;
    private Point mLeft1, mControlLeft1, mLeft2, mControlLeft2, mFirst, mControlFirst,
            mSecond, mControlSecond, mRight;
    private int mHeight;
    private Paint mPaint;
    private int offset;

    public BezierWaterView(Context context) {
        super(context);
    }

    public BezierWaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierWaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(final int w, final int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mHeight = h;
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        resetPoint(w, h);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, w);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                offset = value;
                refreshPoint(w, h);
                invalidate();
            }
        });
        valueAnimator.start();

    }

    private void resetPoint(int w, int h) {
        //5 个基础点
        mLeft1 = new Point(-w, h / 2);
        mLeft2 = new Point(-w / 2, h / 2);
        mFirst = new Point(0, h / 2);
        mSecond = new Point(w / 2, h / 2);
        mRight = new Point(w, h / 2);
        //4个控制点
        mControlLeft1 = new Point(-w * 3 / 4, h / 3);
        mControlLeft2 = new Point(-w * 1 / 4, h * 2 / 3);
        mControlFirst = new Point(w * 1 / 4, h / 3);
        mControlSecond = new Point(w * 3 / 4, h * 2 / 3);
    }

    private void refreshPoint(int w, int h) {
        mLeft1.x = -w + offset;
        mLeft2.x = -w / 2 + offset;
        mFirst.x = 0 + offset;
        mSecond.x = w / 2 + offset;
        mRight.x = w + offset;
        //4个控制点
        mControlLeft1.x = -w * 3 / 4 + offset;
        mControlLeft2.x = -w * 1 / 4 + offset;
        mControlFirst.x = w * 1 / 4 + offset;
        mControlSecond.x = w * 3 / 4 + offset;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("zwl", "xy:" + mLeft1.x + ":" + mLeft1.y);
        mPath.reset();
        mPath.moveTo(mLeft1.x, mLeft1.y);
        mPath.quadTo(mControlLeft1.x, mControlLeft1.y, mLeft2.x, mLeft2.y);
        mPath.quadTo(mControlLeft2.x, mControlLeft2.y, mFirst.x, mFirst.y);
        mPath.quadTo(mControlFirst.x, mControlFirst.y, mSecond.x, mSecond.y);
        mPath.quadTo(mControlSecond.x, mControlSecond.y, mRight.x, mRight.y);
        mPath.lineTo(mRight.x, mHeight);
        mPath.lineTo(mLeft1.x, mHeight);
        mPath.lineTo(mLeft1.x, mLeft1.y);
        canvas.drawPath(mPath, mPaint);
    }
}
