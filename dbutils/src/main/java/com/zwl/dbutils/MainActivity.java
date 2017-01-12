package com.zwl.dbutils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zwl.dbutils.bean.UserBean;
import com.zwl.dbutils.dao.UserDao;

import java.util.List;

/**
 * Created by weilongzhang on 17/1/10.
 */

public class MainActivity extends Activity {

    private UserDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dao = DaoManagerFactory.getInstance().getDataHelper(UserDao.class, UserBean.class);
    }

    public void save(View view) {
        UserBean userBean = new UserBean();
        userBean.setName("zwl1");
        userBean.setPassword("mima1");
        dao.insert(userBean);

        userBean.setName("zwl2");
        userBean.setPassword("mima2");
        dao.insert(userBean);

        userBean.setName("zwl3");
        userBean.setPassword("mima3");
        dao.insert(userBean);

        logList();


    }

    private void logList() {
        List<UserBean> list = dao.query(new UserBean());
        if (list == null || list.isEmpty()) {
            Log.d("zwl", "null!!!");
        } else {
            for (int i = 0; i < list.size(); i ++) {
                Log.d("zwl", "list:" +  list.get(i).toString());
            }
        }
    }


    public void delete(View view) {
        UserBean userBean = new UserBean();
        userBean.setName("zwl3");
        dao.delete(userBean);
        logList();
    }
    public void update(View view) {
        UserBean userBean = new UserBean();
        userBean.setName("zwl1");
        UserBean updateBean = new UserBean();
        updateBean.setName("zwl1-zwlUpdate");
        dao.update(updateBean, userBean);
        logList();
    }
    public void query(View view) {
        logList();
    }
}
