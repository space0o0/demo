package com.example.space.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeChartImage2Activity extends AppCompatActivity {

    @Bind(R.id.image)
    ImageView imageView;

    int lastY=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chart_image2);
        ButterKnife.bind(this);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int y = (int) event.getY();

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY=y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        imageView.offsetTopAndBottom(y-lastY);
//                        if (y-lastY>v.getHeight()/3){
//                            finish();
//                        }
                        break;
                    case MotionEvent.ACTION_UP:
//                        imageView.offsetTopAndBottom(lastY-y);
                        break;
                }

                return true;
            }
        });


    }
}
