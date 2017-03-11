package com.zwl.animation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.why.utils.LogUtils;

/**
 * Created by weilongzhang on 17/3/11.
 */

public class BubbleView extends View {

    private Paint mPaint;
    private float mWidth;
    private float mHeight;
    private int mBigRadius;
    private int mSmallRadius;

    private float mInnerX, mInnerY;

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
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = this.getMeasuredWidth();
        this.mHeight = this.getMeasuredHeight();
        this.mBigRadius = (int) (mWidth / 2);
        this.mSmallRadius = mBigRadius / 2;
        calculateInnerCircle();
    }

    private void calculateInnerCircle() {
        int tempRadius = (int) (Math.random() * mBigRadius);
        double tempAngle = Math.random() * 2 * Math.PI;
        this.mInnerY = (float) (mBigRadius - tempRadius * Math.sin(tempAngle));
        this.mInnerX = (float) (mBigRadius + tempRadius * Math.sin(tempAngle));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mWidth == 0 || mHeight == 0) {
            return;
        }


        LogUtils.d("zwl", "mWidth:" + mWidth + ",mHeight:" + mHeight +
                ",mBigRadius:" + mBigRadius + ",mInnerX:" + mInnerX + ",mInnerY:" + mInnerY);

        // outer circle
        mPaint.setColor(Color.rgb(255, 170, 75));
        canvas.drawCircle(mWidth / 2, mHeight / 2, mBigRadius, mPaint);
        // inner circle
        mPaint.setColor(Color.rgb(255, 184, 101));
        canvas.drawCircle(mInnerX, mInnerY, mSmallRadius, mPaint);
    }
}
