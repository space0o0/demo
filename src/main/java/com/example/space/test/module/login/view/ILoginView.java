package com.example.space.test.module.login.view;

/**
 * Created by space on 16/3/15.
 */
public interface ILoginView {

    void onClearText();

    void onLoginResult(Boolean result, String code);

    void onSetProgressBarVisibility(int visibility);
}
