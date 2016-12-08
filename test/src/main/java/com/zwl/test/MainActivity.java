package com.zwl.test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("开始");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0 ; i < 3; i ++) {
                    new AsyncTask<Void, Void, Void>() {
                        int i = 0;
                        @Override
                        protected Void doInBackground(Void... params) {
                            while (true) {
                                Log.d("zwl", Thread.currentThread().getName() + "---" + (i ++));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.execute();
                }
            }
        });
        setContentView(button);
    }
}
