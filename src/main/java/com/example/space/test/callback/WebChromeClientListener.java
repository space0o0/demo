package com.example.space.test.callback;

/**
 * webview的接口
 * Created by space on 16/5/18.
 */
public interface WebChromeClientListener {

    /**
     * 设置标题
     *
     * @param title
     */
    void onReceivedTitle(String title);
}
