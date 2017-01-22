package com.example.space.test.module.transition;

import android.animation.Animator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.space.test.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
    @Bind(R.id.dialog)
    Button dialog;
    @Bind(R.id.oval)
    ImageView img_oval;
    @Bind(R.id.rect)
    ImageView img_rect;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition__a);

        ButterKnife.bind(this);
        intent = new Intent(this, Transition_BActivity.class);

        bt_explode.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                explode();
            }
        });

        bt_slide.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                slide();
            }
        });

        bt_fade.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                fade();
            }
        });

        bt_share.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                share(v);
            }
        });

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });

        img_oval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(v, v.getWidth() / 2, v.getHeight() / 2, v.getWidth(), 0);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        });

        img_rect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animator animator = ViewAnimationUtils.createCircularReveal(v, 0, 0, 0, (float) Math.hypot(v.getWidth(), v.getHeight()));
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void explode() {
        intent.putExtra("flag", 0);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void slide() {
        intent.putExtra("flag", 1);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void fade() {
        intent.putExtra("flag", 2);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void share(View view) {
        intent.putExtra("flag", 3);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Transition_AActivity.this,
                Pair.create((View) fabA, "fab"), Pair.create(view, "share")).toBundle());
    }


    public void dialog() {
        AlertDialog builder = new AlertDialog.Builder(this).setMessage("xxxx").create();
        builder.show();
    }

    @Override
    protected void onStart() {
        Log.i("@#@#", "-----onstart-----");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i("@#@#", "-----onresume-----");

        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("@#@#", "-----onpause-----");

        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("@#@#", "-----onstop-----");

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("@#@#", "-----ondestroy-----");

        super.onDestroy();
    }
}
