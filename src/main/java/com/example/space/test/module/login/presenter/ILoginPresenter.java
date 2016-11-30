package com.example.space.test.module.login.presenter;

/**
 * Created by space on 16/3/15.
 */
public interface ILoginPresenter {

    void clear();
    void Login(String name , String password);
    void setProgressBarVisiblity(int visiblity);
}
