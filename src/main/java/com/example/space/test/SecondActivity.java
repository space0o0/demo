package com.example.space.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends Activity {


    TextView tv_dependency;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv_dependency = (TextView) findViewById(R.id.depentent);

//        toolbarBehavior=new toolbarBehavior();
//
//        CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) tv_auto.getLayoutParams();
//
//        params.setBehavior(toolbarBehavior);

//        tv_dependency.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ViewCompat.offsetTopAndBottom(v,5);
//            }
//        });

        tv_dependency.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x=event.getX();

                float lastX=0f;
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX=x;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        int offsetx= (int) (x-lastX);

                        Log.i("offsetx","x:"+x+"lastX:"+lastX+"offsetX:"+offsetx);

                        //----------------------------

                        ViewCompat.offsetTopAndBottom(v, 5);

                        break;
                }
                return true;
            }
        });
    }

}
