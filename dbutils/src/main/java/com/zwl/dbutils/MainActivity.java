package com.zwl.dbutils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zwl.dbutils.bean.User;
import com.zwl.dbutils.dao.UserDao;

/**
 * Created by weilongzhang on 17/1/10.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void save(View view) {
        UserDao dao = DbUtilsFactory.getInstance().getDao(UserDao.class);
        dao.save(new User());
    }
    public void delete(View view) {}
    public void update(View view) {}
    public void query(View view) {}
}
