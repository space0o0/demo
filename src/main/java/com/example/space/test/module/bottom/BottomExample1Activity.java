package com.example.space.test.module.bottom;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.space.test.R;
import com.example.space.test.annotation.ActivityFragmentInject;
import com.example.space.test.base.BaseActivity;
import com.example.space.test.utils.RoatAnimUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityFragmentInject(contentViewId = R.layout.activity_bottom_example1)
public class BottomExample1Activity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.img_add)
    ImageView imgAdd;

    int state = 0;
    int state_IN = 0;
    int state_OUT = 1;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        Log.i("tag","启动activity");
        imgAdd.setOnClickListener(this);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
//        super.onClick(v);

        switch (v.getId()) {
            case R.id.img_add:
                if (state == state_IN) {
                    toast("out");
                    RoatAnimUtil.startAnimOut(v);
                    showDialog();

                    state = state_OUT;
                } else {
                    toast("in");
                    RoatAnimUtil.startAnimIn(v);
                    state = state_IN;
                }
                break;
        }
//        getWindowManager().removeView(imgAdd);

//        imgAdd.setZ(Integer.MAX_VALUE);
//        imgAdd.bringToFront();
    }

    DialogBottom1Fragment dialog;
    public void showDialog(){
        dialog=DialogBottom1Fragment.newInstance();
        dialog.show(getSupportFragmentManager(),"DialogBottom1Fragment");
    }

    public void showPop(){

    }

}
