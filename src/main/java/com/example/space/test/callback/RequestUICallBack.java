package com.example.space.test.callback;

/**
 *在协议处理时，界面的显示处理
 * Created by space on 16/3/29.
 */
public interface RequestUICallBack<T> {

    /**
     * 请求前调用
     */
    void beforeRequest();

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求错误调用
     * @param errorCode 错误代码
     * @param errorData
     */
    void requestError(String errorCode, Object errorData);

    /**
     * 请求成功调用
     * @param data 数据
     */
    void requestSuccess(T data);
}
