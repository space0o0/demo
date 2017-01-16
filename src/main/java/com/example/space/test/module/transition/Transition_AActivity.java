package com.example.space.test.module.transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.space.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Transition_AActivity extends AppCompatActivity {

    @Bind(R.id.explode)
    Button bt_explode;
    @Bind(R.id.slide)
    Button bt_slide;
    @Bind(R.id.fade)
    Button bt_fade;
    @Bind(R.id.share)
    Button bt_share;
    @Bind(R.id.fabA)
    FloatingActionButton fabA;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition__a);

        ButterKnife.bind(this);
        intent = new Intent(this, Transition_BActivity.class);


        bt_explode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explode();
            }
        });

        bt_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slide();
            }
        });

        bt_fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fade();
            }
        });

        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(v);
            }
        });
    }


    public void explode() {
        intent.putExtra("flag", 0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void slide() {
        intent.putExtra("flag", 1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void fade() {
        intent.putExtra("flag", 2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void share(View view) {
        intent.putExtra("flag", 3);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Transition_AActivity.this,
                Pair.create((View) fabA, "fab"), Pair.create(view, "share")).toBundle());
    }

}
