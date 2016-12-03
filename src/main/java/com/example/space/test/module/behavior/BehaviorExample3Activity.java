package com.example.space.test.module.behavior;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.space.test.R;
import com.example.space.test.annotation.ActivityFragmentInject;
import com.example.space.test.base.BaseActivity;
import com.example.space.test.base.BaseRecyclerAdapter;
import com.example.space.test.base.BaseRecyclerViewHolder;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

@ActivityFragmentInject(contentViewId = R.layout.activity_behavior_example3)
public class BehaviorExample3Activity extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    BaseRecyclerAdapter<String> adapter;
    private String[] str_name ;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        initRecyclerView();
    }

    public void initRecyclerView() {

        LinearLayoutManager manager = new LinearLayoutManager(BehaviorExample3Activity.this, LinearLayoutManager.VERTICAL, false);
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
