package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weilongzhang on 16/12/12.
 */

public class FireView extends View {

    private Point mStartPoint;
    private Paint mPaint;
    private Path mPath;
    private int mCircleRadius = 300;
    private int[] mControllerRadius = new int[6];
    private List<ValueAnimator> animators = new ArrayList<ValueAnimator>();
    private double mStartAngle = Math.PI / 3;

    public FireView(Context context) {
        super(context);
        init();
    }

    public FireView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FireView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mStartPoint = new Point();
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mControllerRadius = new int[]{400, 380, 360, 360, 380, 400};
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mStartPoint.x = (int) event.getX();
            mStartPoint.y = (int) event.getY();
            resetControllerRadius();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mStartPoint.x = (int) event.getX();
            mStartPoint.y = (int) event.getY();
            clearAnimations();
            resetControllerRadius();
            postInvalidate();
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
        }
        return super.onTouchEvent(event);
    }

    private void clearAnimations() {
        if (animators.size() > 0) {
            for (int i = 0; i < animators.size() ; i ++) {
                animators.get(i).cancel();
            }
        }
        animators.clear();
    }


    private void resetControllerRadius() {
        for (int i = 0 ; i < mControllerRadius.length; i ++ ) {
            final int mCurrentIndex = i;
            int offset = (int) (Math.random() * 20);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(mControllerRadius[mCurrentIndex], mControllerRadius[mCurrentIndex] - offset, mControllerRadius[mCurrentIndex] + offset);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
            valueAnimator.setDuration(2000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    mControllerRadius[mCurrentIndex] = (int)valueAnimator.getAnimatedValue();
                    postInvalidate();
                }
            });
            valueAnimator.start();
            animators.add(valueAnimator);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mStartPoint.x == 0 && mStartPoint.y == 0) {
            return;
        }
        mPath.reset();
        //开始位置
        mPath.moveTo(
                (float) (mStartPoint.x + Math.cos(mStartAngle) * mCircleRadius),
                (float) (mStartPoint.y - Math.sin(mStartAngle) * mCircleRadius)
        );
        //第一个控制点和第二个基准点
        mPath.quadTo(
                (float) (mStartPoint.x + Math.sin(mStartAngle) * mControllerRadius[0]),
                (float) (mStartPoint.y - Math.cos(mStartAngle) * mControllerRadius[0]),
                (float) (mStartPoint.x + mCircleRadius),
                (float) (mStartPoint.y)
        );
        //第二个控制点和第三个基准点
        mPath.quadTo(
                (float) (mStartPoint.x + Math.sin(mStartAngle ) * mControllerRadius[1]),
                (float) (mStartPoint.y + Math.cos(mStartAngle ) * mControllerRadius[1]),
                (float) (mStartPoint.x + Math.cos(mStartAngle ) * mCircleRadius),
                (float) (mStartPoint.y + Math.sin(mStartAngle ) * mCircleRadius)
        );
        //第三个控制点和第四个基准点
        mPath.quadTo(
                (float) (mStartPoint.x),
                (float) (mStartPoint.y + mControllerRadius[2]),
                (float) (mStartPoint.x - Math.cos(mStartAngle ) * mCircleRadius),
                (float) (mStartPoint.y + Math.sin(mStartAngle) * mCircleRadius));

        //第四个控制点和第五个基准点
        mPath.quadTo(
                (float) (mStartPoint.x - Math.sin(mStartAngle) * mControllerRadius[3]),
                (float) (mStartPoint.y + Math.cos(mStartAngle) * mControllerRadius[3]),
                (float) (mStartPoint.x - mCircleRadius),
                (float) (mStartPoint.y));

        //第五个控制点和第六个基准点
        mPath.quadTo(
                (float) (mStartPoint.x - Math.sin(mStartAngle) * mControllerRadius[4]),
                (float) (mStartPoint.y - Math.cos(mStartAngle) * mControllerRadius[4]),
                (float) (mStartPoint.x - Math.cos(mStartAngle) * mCircleRadius),
                (float) (mStartPoint.y - Math.sin(mStartAngle) * mCircleRadius));

        //第六个控制点和第1个基准点
        mPath.quadTo(
                (float) (mStartPoint.x),
                (float) (mStartPoint.y - mControllerRadius[5]),
                (float) (mStartPoint.x + Math.cos(mStartAngle) * mCircleRadius),
                (float) (mStartPoint.y - Math.sin(mStartAngle) * mCircleRadius));

        canvas.drawPath(mPath, mPaint);
    }
}
