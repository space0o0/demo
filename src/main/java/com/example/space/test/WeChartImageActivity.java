package com.example.space.test;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeChartImageActivity extends AppCompatActivity {

    @Bind(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chart_image);

        ButterKnife.bind(this);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WeChartImageActivity.this, WeChartImage2Activity.class);
                startActivity(i, ActivityOptions.makeSceneTransitionAnimation(WeChartImageActivity.this,imageView,"image").toBundle());

            }
        });
    }
}
