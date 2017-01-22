package com.zwl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by weilongzhang on 17/1/20.
 */

public class AutoBreakLayout extends ViewGroup {


    public AutoBreakLayout(Context context) {
        super(context);
    }

    public AutoBreakLayout(Context context, AttributeSet attrs) {
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
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                int height = childView.getMeasuredHeight();
                int width = childView.getMeasuredWidth();
                if (i % 4 == 0) {
                    childView.layout(width * (i % 4), (height + 8) * (i / 4), width * (i % 4) +
                            width, (height + 8) * (i / 4) + height);
                } else {
                    childView.layout((width + 12) * (i % 4), (height + 8) * (i / 4), (width + 12)
                            * (i % 4) + width, (height + 8) * (i / 4) + height);
                }
            }
        }
    }
}
