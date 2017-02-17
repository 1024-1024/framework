package com.zwl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weilongzhang on 17/1/20.
 */

public class MultiAutoBreakLayout extends ViewGroup {


    public MultiAutoBreakLayout(Context context) {
        super(context);
    }

    public MultiAutoBreakLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int index = 0; index < getChildCount(); index++) {
            final View child = getChildAt(index);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int height = 0;
        int width = 0;
        if (childCount > 0) {
            for (int i = 0; i < childCount; i ++) {
                View childView = getChildAt(i);
                int h = childView.getMeasuredHeight();
                int w = childView.getMeasuredWidth();
                width += w;
                if (width > 1092) {
                    height += h;
                    width = w;
                    childView.layout(0, height, w, h);
                } else {
                    childView.layout(0, height, w, h);
                }

            }
        }
    }

}
