package com.zwl.test;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Button;

import com.why.litesuits.common.utils.DisplayUtil;
import com.zwl.widget.MultiAutoBreakLayout;

/**
 * Created by weilongzhang on 17/2/16.
 */

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        MultiAutoBreakLayout myViewGroup = (MultiAutoBreakLayout) this.findViewById(R.id.layout);
        for (int i = 0; i < 3; i++) {
            Button button = null;
            if (i % 2 == 0) {
                button = new Button(this);
                button.setText("偶数" + i);
            } else {
                String text = "这是一个非常长的句子，请仔细看好会再那里换行，这个很重要的" + i;
                button = new Button(this);
                StringBuilder builder = new StringBuilder();
                Paint paint = button.getPaint();
                int length = 0;
                for (int j = 0; j < text.length(); j++) {
                    if (length + 200> DisplayUtil.getDisplayMetrics(getApplication()).widthPixels) {
                        builder.append("\n");
                        length = 0;
                    } else {
                        length += paint.measureText(text.toCharArray(), j, 1);
                    }
                    builder.append(text.charAt(j));
                }
                button.setText(builder.toString());
            }
            myViewGroup.addView(button);
        }

    }
}
