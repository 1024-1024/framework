package com.zwl.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zwl.http.interfaces.IDataListener;

public class MainActivity extends AppCompatActivity {
    public  static  final String url="http://192.168.100.24:8080/UserRecord/LoginServlet";
    private static final String TAG = "dongnao";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.content);

    }

    /**
     *  1
     *  2
     * @param view
     */
    public  void login(View view)
    {
        User user=new User();
        user.setName("13343491234");
        user.setPassword("123456");
        for (int i=0;i<50;i++)
        {
            Volley.sendRequest(user, url, LoginRespense.class, new IDataListener<LoginRespense>() {
                @Override
                public void onSuccess(LoginRespense loginRespense) {
                    Log.i(TAG,loginRespense.toString());
                }

                @Override
                public void onFail() {
                    Log.i(TAG,"获取失败");
                }
            });
        }



    }
}
