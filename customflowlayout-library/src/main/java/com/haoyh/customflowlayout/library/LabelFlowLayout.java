package com.haoyh.customflowlayout.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.android.flexbox.FlexboxLayout;
import com.haoyh.customflowlayout.library.BaseLabelAdapter.OnAdapterDataChanged;

/**
 * Created with Android Studio.
 * User: haoyanhui
 * Date: 2017/1/5
 * Time: 14:39
 * Desc: 显示标签控件，根据屏幕大小自动换行显示
 */

public class LabelFlowLayout extends FlexboxLayout implements OnAdapterDataChanged, OnClickListener {

    private static final int TAG_KEY = 0x111;
    private BaseLabelAdapter mBaseLabelAdapter;

    private OnLabelItemClickListener mOnLabelItemClickListener;

    public LabelFlowLayout(Context context) {
        super(context);
    }

    public LabelFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public LabelFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(BaseLabelAdapter adapter) {
        mBaseLabelAdapter = adapter;
        if (mBaseLabelAdapter != null) {
            mBaseLabelAdapter.setOnAdapterDataChanged(this);
        }
        dataChanged();
    }

    private void dataChanged() {
        removeAllViews();
        if (mBaseLabelAdapter == null) {
            return;
        }
        int count = mBaseLabelAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View itemView = mBaseLabelAdapter.getView(this, i);
            itemView.setTag(i);
            itemView.setOnClickListener(this);
            addView(itemView);
        }
    }

    @Override
    public void onClick(View view) {
        int id = this.getId();
        if (mOnLabelItemClickListener != null) {
            mOnLabelItemClickListener.onLabelItemClick(id, view, (int) view.getTag());
        }
    }

    @Override
    public void onChange() {
        dataChanged();
    }

    public void setOnLabelItemClickListener(OnLabelItemClickListener listener) {
        mOnLabelItemClickListener = listener;
    }

    public interface OnLabelItemClickListener {
        void onLabelItemClick(int parentId, View view, int position);
    }
}
