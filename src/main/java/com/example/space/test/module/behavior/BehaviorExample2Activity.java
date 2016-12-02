package com.example.space.test.module.behavior;

import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;

import com.example.space.test.R;
import com.example.space.test.annotation.ActivityFragmentInject;
import com.example.space.test.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityFragmentInject(contentViewId = R.layout.activity_behavior_example2)
public class BehaviorExample2Activity extends BaseActivity {

    @Bind(R.id.dependency)
    TextView dependency;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        dependency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetLeftAndRight(v,20);
            }
        });
    }
}
