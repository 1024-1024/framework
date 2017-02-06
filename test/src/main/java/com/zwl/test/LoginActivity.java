package com.zwl.test;

/**
 * Created by weilongzhang on 17/2/6.
 */

public class LoginActivity extends LoginContract.Presenter  {
    @Override
    public void login(String username, String password) {

    }

    class LoginPage implements LoginContract.View {
        @Override
        public void loginSuccess() {

        }
    }


}
