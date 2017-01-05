package com.haoyh.customflowlayout.library;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created with Android Studio.
 * User: haoyanhui
 * Date: 2017/1/5
 * Time: 14:54
 * Desc: LabelFlowLayout 对应的适配器
 */

public abstract class BaseLabelAdapter {

    private OnAdapterDataChanged mOnAdapterDataChanged;

    public abstract int getCount();

    public abstract Object getItem(int position);

    public abstract View getView(ViewGroup parent, int position);

    public void notifyDataSetChanged() {
        if (mOnAdapterDataChanged != null) {
            mOnAdapterDataChanged.onChange();
        }
    }

    void setOnAdapterDataChanged(OnAdapterDataChanged onAdapterDataChanged) {
        mOnAdapterDataChanged = onAdapterDataChanged;
    }

    interface OnAdapterDataChanged {
        void onChange();
    }
}
