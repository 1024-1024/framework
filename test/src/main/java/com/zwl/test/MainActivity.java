package com.zwl.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zwl.widget.AutoBreakLayout;

public class MainActivity extends AppCompatActivity {

    private AutoBreakLayout autoBreakLayout;
    private LinearLayout lll;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Button button = new Button(this);
//        button.setText("开始");
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0 ; i < 3; i ++) {
//                    new AsyncTask<Void, Void, Void>() {
//                        int i = 0;
//                        @Override
//                        protected Void doInBackground(Void... params) {
//                            while (true) {
//                                Log.d("zwl", Thread.currentThread().getName() + "---" + (i ++));
//                                try {
//                                    Thread.sleep(1000);
//                                } catch (InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }.execute();
//                }
//            }
//        });
        setContentView(R.layout.activity_main);
        lll = (LinearLayout) this.findViewById(R.id.lll);
        autoBreakLayout = (AutoBreakLayout) this.findViewById(R.id.AutoBreakLayout);
        scrollView = (ScrollView) this.findViewById(R.id.activity_main);
        scrollView.setFillViewport(true);

    }
    public void addText(View view) {

    }

    public void add(View view) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        textView.setText("waimian");
        autoBreakLayout.addView(textView);
    }


}
