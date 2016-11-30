package com.example.space.test.base;

import android.content.Context;

/**
 * Created by space on 16/3/18.
 * 视图基类借口
 */
public interface BaseView {

    void toast(String msg);

    void showProgress();

    void hideProgress();

    void finishView();

    Context getcontext();

}
