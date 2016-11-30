package com.example.space.test.module.login.presenter;

import com.example.space.test.module.login.model.IUser;
import com.example.space.test.module.login.view.ILoginView;

/**
 * Created by space on 16/3/15.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    ILoginView iLoginView;
    IUser user;

    public LoginPresenterImpl(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        user=new IUser();
    }

    @Override
    public void clear() {
        iLoginView.onClearText();//在loginactivity中具体实现了该方法

    }

    @Override
    public void Login(String name, String password) {
        String re = user.check(name, password);

        iLoginView.onLoginResult(true, re);
    }


    @Override
    public void setProgressBarVisiblity(int visiblity) {
        iLoginView.onSetProgressBarVisibility(visiblity);
    }
}
