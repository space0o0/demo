package com.example.space.test.callback;


import com.example.space.test.base.BaseView;

/**
 * Created by space on 16/6/15.
 */
public class RequestUICallBackImpl implements RequestUICallBack {

    BaseView mView;
    RequestUICallBack requestUICallBack;

    public RequestUICallBackImpl(BaseView mview) {
        this.mView = mview;

    }

    /**
     * 请求前调用
     */
    @Override
    public void beforeRequest() {
        requestUICallBack.beforeRequest();
    }

    /**
     * 请求完成调用
     */
    @Override
    public void requestComplete() {
        requestUICallBack.requestComplete();
    }

    /**
     * 请求错误调用
     *
     * @param errorCode 错误代码
     * @param errorData
     */
    @Override
    public void requestError(String errorCode, Object errorData) {
        requestUICallBack.requestError(errorCode,errorData);
    }

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    @Override
    public void requestSuccess(Object data) {
        if (mView == null) {
            return;
        }

        requestUICallBack.requestSuccess(data);

    }
}
