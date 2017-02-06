package com.zwl.test;

/**
 * Created by weilongzhang on 17/2/6.
 */

public interface LoginContract {

    interface View extends BaseView {
        void loginSuccess();
    }

    abstract class Presenter extends BasePresenter {
        public abstract void login(String username, String password);
    }

}
