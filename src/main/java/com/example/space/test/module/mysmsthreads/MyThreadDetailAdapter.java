package com.example.space.test.module.mysmsthreads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.space.test.R;

import java.util.ArrayList;

public class MyThreadDetailAdapter extends BaseAdapter{

	private Context mContext;
	private ArrayList<ThreadDetailBean> mDatas;
	public MyThreadDetailAdapter(Context mContext,
			ArrayList<ThreadDetailBean> mDatas) {
		this.mContext = mContext;
		this.mDatas = mDatas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ThreadDetailBean threadDetailBean = mDatas.get(position);
		int layoutId = threadDetailBean.getLayoutId();
		if (layoutId== R.layout.hei_item) {
			//type 1 ???
			convertView = View.inflate(mContext, R.layout.hei_item, null);
		}else if (layoutId==R.layout.me_item) {
			//type 2 ???
			convertView = View.inflate(mContext, R.layout.me_item, null);
		}
		TextView  name = (TextView) convertView.findViewById(R.id.name);
		TextView  content = (TextView) convertView.findViewById(R.id.content);
		
		name.setText(threadDetailBean.getPhone()+":"+threadDetailBean.getDate());
		content.setText(threadDetailBean.getContent());
		
		
		return convertView;
		
	}

	/**
	 * ?????????д2??? ????????????????????????????? ??????????
	 * ??????????
	 */
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}


	

}
