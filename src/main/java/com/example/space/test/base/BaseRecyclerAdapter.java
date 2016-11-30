package com.example.space.test.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.space.test.callback.OnItemClickListener;
import com.example.space.test.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by space on 16/3/31.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    private Context mContext;
    private List<T> mData;
    private boolean mUseAnimation;
    private RecyclerView.LayoutManager mLayoutManager;
    protected LayoutInflater mInflater;

    protected OnItemClickListener mClickListener;

    private static final int ITEM_VIEW_TYPE_EMPTY = 2001;
    private static final int ITEM_VIEW_TYPE_NORMAL = 2002;
    private int STATE = ITEM_VIEW_TYPE_NORMAL;

    private View emptyView;
    private String emptyInfo="暂无数据";

    public BaseRecyclerAdapter(Context context, List<T> data) {
        this(context, data, true);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, boolean useAnimation) {
        this(context, data, useAnimation, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> data, boolean useAnimation, RecyclerView.LayoutManager layoutManager) {
        this.mContext = context;
        this.mData = data == null ? new ArrayList<T>() : data;
        this.mUseAnimation = useAnimation;
        this.mLayoutManager = layoutManager;
        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        if (viewType == ITEM_VIEW_TYPE_EMPTY) {
            return new emptyView(mContext, getEmptyView());
        }

        final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemLayoutID(viewType), parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(final BaseRecyclerViewHolder holder, final int position) {


        switch (STATE) {
            case ITEM_VIEW_TYPE_NORMAL:

                if (mClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mClickListener.onItemClick(v, position);
                        }
                    });
                }

                bindData(holder, position, mData.get(position));

                if (mUseAnimation) {
                    setAnimation(holder.itemView, position);
                }

                break;
            case ITEM_VIEW_TYPE_EMPTY:

                onBindEmptyViewHolder(holder, position);

                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() == 0) {
            STATE = ITEM_VIEW_TYPE_EMPTY;

            return ITEM_VIEW_TYPE_EMPTY;
        } else {
            STATE = ITEM_VIEW_TYPE_NORMAL;
            int type = setItemViewType(position);
            return type != ITEM_VIEW_TYPE_EMPTY ? type : super.getItemViewType(position);
        }
    }

    public int setItemViewType(int position) {

        return ITEM_VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mData.size() == 0) {
            return 1;
        }
        return mData.size();
    }

    public void add(int positon, T item) {
        mData.add(positon, item);
        notifyDataSetChanged();
    }

    public void delete(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void addMore(List<T> datas) {
        int startPosition = mData.size();
        mData.addAll(datas);
        notifyItemRangeChanged(startPosition, datas.size());
    }

    public void setData(List<T> datas) {
        mData = datas;
        notifyDataSetChanged();
    }

    /**
     * 返回所有的数据
     *
     * @return
     */
    public List<T> getData() {
        return mData;
    }

    private int lastPosition = -1;

    public void setAnimation(View viewToAnimate, int position) {

//        if (position > lastPosition) {
//            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.item_bottom_in);
//            viewToAnimate.setAnimation(animation);
//            lastPosition = position;
//        }

    }

    /**
     * 设置item的layoutID
     *
     * @param viewType
     * @return
     */
    public abstract int getItemLayoutID(int viewType);

    /**
     * 绑定item的中控件显示
     *
     * @param holder
     * @param position
     * @param item
     */
    public abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);


    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    /**
     * 设置当recyclerview中没有数据时，显示的view
     *
     * @return
     */
    public View getEmptyView() {
        if (emptyView == null) {
            TextView textView = new TextView(mContext);
            textView.setText(emptyInfo);
            textView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            int padding_sp= DensityUtils.dp2px(mContext,20);
            textView.setPadding(padding_sp,0,padding_sp,0);
            return textView;
        } else {
            return emptyView;
        }
    }

    public void setEmptyView(View emptyView) {

        this.emptyView = emptyView;
    }

    public static class emptyView extends BaseRecyclerViewHolder {

        public emptyView(Context context, View itemView) {
            super(context, itemView);
        }
    }

    public void onBindEmptyViewHolder(BaseRecyclerViewHolder holder, int position) {
    }

    @Override
    public void onViewDetachedFromWindow(BaseRecyclerViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.getItemView().clearAnimation();
    }

    public void setEmptyInfo(String emptyInfo) {
        this.emptyInfo = emptyInfo;
    }

}
