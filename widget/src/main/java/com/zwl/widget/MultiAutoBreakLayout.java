package com.zwl.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MultiAutoBreakLayout extends ViewGroup {

    private int interval = 2;//按钮之间的间隔
    private final static String TAG = "MultiAutoBreakLayout";
    private Context mContext;
    private int totalHeight;
    private int pL;
    private int pR;
    private int pT;
    private int pB;


    private Paint mPaint;
    private int row;
    private int viewGroupWidth;


    public MultiAutoBreakLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MultiAutoBreakLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec , heightMeasureSpec);
        pL = getPaddingLeft();
        pR = getPaddingRight();
        pT = getPaddingTop();
        pB = getPaddingBottom();
        totalHeight = 0;
        viewGroupWidth = MeasureSpec.getSize(widthMeasureSpec);
        int tempWidth = 0;
        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            // measure
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            if (tempWidth + childWidth + interval> viewGroupWidth - pL - pR) {
                totalHeight += childHeight;
                tempWidth = childWidth + interval;
            } else {
                tempWidth += childWidth + interval;
                if (totalHeight == 0) {
                    totalHeight += childHeight;
                }
            }
        }
        totalHeight = totalHeight + pB + pT;
        Log.d("zwl", "setMeasuredDimension(viewGroupWidth, totalHeight);" + viewGroupWidth + ":" + totalHeight);
        setMeasuredDimension(viewGroupWidth, totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int count = getChildCount();
        row = 0;
        int tempW = 0;
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();
            if (i == 0) {
                childView.layout(pL, pT, pL + childWidth, pT + childHeight);
                tempW += childWidth + interval;
            } else {
                if (tempW + pL + pR + childWidth + interval> viewGroupWidth) {
                    row++;
                    childView.layout(pL, pT + row * childHeight, pL + childWidth, pT + row *
                            childHeight + childHeight);
                    tempW = childWidth + interval;
                } else {
                    childView.layout(pL + tempW, row * childHeight + pT, pL + tempW + childWidth,
                            row * childHeight + pT + childHeight);
                    tempW += childWidth + interval;
                }
            }

        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (row == 0 || mPaint == null) {
            return;
        }
        int tempHeight = (totalHeight - pT - pB) / (row + 1);
        for (int i = 1; i <= row; i ++) {
            canvas.drawLine(pL, tempHeight * i + pT, viewGroupWidth - pR, tempHeight * i + pT, mPaint);
        }
    }

    public void setLineStyle(int color, int i) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(i);
    }
}