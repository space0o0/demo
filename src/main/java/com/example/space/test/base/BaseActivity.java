package com.example.space.test.base;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.space.test.BuildConfig;
import com.example.space.test.R;
import com.example.space.test.annotation.ActivityFragmentInject;
import com.example.space.test.utils.sysUtils;
import com.example.space.test.widget.YTFjrToast;


/**
 * Created by space on 16/3/18.
 * activity 基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView, View.OnClickListener {

    private int contentViewId;//顶部布局id
    private int toolbarTitle;//toolbar标题
    private int toolbarIndicator;//toolbar左边的按钮图片
    private int menuId;//菜单
    public Menu menu;
    /**
     * 控制滑动与否的接口
     */
//    protected SlidrInterface mSlidrInterface;

    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 获取子activity的初始设置
         */
        if (getClass().isAnnotationPresent(ActivityFragmentInject.class)) {

            ActivityFragmentInject annotation = getClass().getAnnotation(ActivityFragmentInject.class);

            contentViewId = annotation.contentViewId();
            toolbarTitle = annotation.toolbarTitle();
            toolbarIndicator = annotation.toolbarIndicator();
            menuId = annotation.menuId();

        } else {
            throw new RuntimeException("Class must add annotation of ActivityFragmentInject.class");
        }

        /**
         * 设置严苛模式的调试特效
         */
        if (BuildConfig.DEBUG) {
            /**
             * 线程策略 针对主线程进行磁盘和网络访问，获得警告
             */
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
        } else {
            /**
             * 虚拟机策略 检查内存泄露
             */
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
        }

        setContentView(contentViewId);

        /** -------------------------------------
         * 后续如果需要添加侧滑，在此处初始化
         * --------------------------------------
         */
//        if (constant.isOpenSliderFinishActivity){
//            mSlidrInterface= SlidrUtil.initSlidrDefaultConfig(this,true);
//        }

        initToolbar();

        initView();

//        ETFApplication.getInstance().addAcitivity(this);

    }

    protected abstract void initView();

    /**
     * 初始化toolbar，包括设置title和indicator
     */
    Toolbar toolbar;

    public void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (toolbarTitle != -1) {
                setToolbarTitle(toolbarTitle);
            }
            if (toolbarIndicator != -1) {
                setToolbarIndicator(toolbarIndicator);
            } else {
                //todo 如果没有图片，则设置indicator不显示
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                setToolbarIndicator(-1);
            }
        }
    }


    /**
     * 设置toolbar的title
     *
     * @param toolbarTitle
     */
    public void setToolbarTitle(int toolbarTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(toolbarTitle);
        }
    }

    /**
     * 程序中直接设置toolbar
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setToolbarSubTitle(String subTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(subTitle);
        }
    }

    /**
     * 设置toolbar左边的按钮
     *
     * @param toolbarIndicator
     */
    public void setToolbarIndicator(int toolbarIndicator) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(toolbarIndicator);
        }
    }

    /**
     * 获取顶层布局
     *
     * @return
     */
    public View getDecorView() {
        return getWindow().getDecorView();
    }

    /**
     * 获取toolbar
     *
     * @return
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 显示snackbar
     *
     * @param msg
     */
    public void showSnackBar(String msg) {
        Snackbar.make(getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (sysUtils.isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        LogUtils.i("onTouchEvent","-----------------------");
//        if (null!=this.getCurrentFocus()){
//            LogUtils.i("onTouchEvent","----------进入判断-------------");
//            // TODO: 16/7/19 当点击空白区域时，输入框隐藏
//            InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            return inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
//        }
//        return super.onTouchEvent(event);
//    }

    /**
     * 子activity实现baseview接口
     *
     * @param msg
     */
    @Override
    public void toast(String msg) {
        YTFjrToast.show(this, msg);
    }

    @Override
    public void showProgress() {
//        YtfjrProcessDialog.showLoading(this, true);
    }

    @Override
    public void hideProgress() {
//        YtfjrProcessDialog.showLoading(this, false);
    }

    @Override
    public Context getcontext() {
        return this;
    }

    @Override
    public void finishView() {
        this.finish();
    }


    //-------------------------------------------------------------------------------------------


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        if (menuId != -1) {
            getMenuInflater().inflate(menuId, menu);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//返回
            finish();
        }
//        else if (item.getItemId() == R.id.bmc_menu_main_search) {//搜索基金界面
//            Intent i = new Intent(this, SelectActivity.class);
//            startActivity(i);
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
//        RefWatcher refWatcher = ETFApplication.getRefWatcher(this);
//        refWatcher.watch(this);
    }
}
