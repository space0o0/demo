package com.example.space.test.module.behavior;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.space.test.R;
import com.example.space.test.base.BaseRecyclerAdapter;
import com.example.space.test.base.BaseRecyclerViewHolder;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BehaviorExample1Activity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    BaseRecyclerAdapter<String> adapter;
    private String[] str_name ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_example1);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRecyclerView();

    }

    public void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(BehaviorExample1Activity.this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(manager);

        str_name = getResources().getStringArray(R.array.names);
        adapter = new BaseRecyclerAdapter<String>(this, Arrays.asList(str_name)) {
            @Override
            public int getItemLayoutID(int viewType) {
                return R.layout.item_recyclerview;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, String item) {
                holder.getTextView(R.id.item_text).setText(item);
            }
        };

        recyclerview.setAdapter(adapter);

    }


}
