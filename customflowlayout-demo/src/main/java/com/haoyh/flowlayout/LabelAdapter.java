package com.haoyh.flowlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haoyh.customflowlayout.library.BaseLabelAdapter;

import java.util.List;

/**
 * Created with Android Studio.
 * User: haoyanhui
 * Date: 2017/1/5
 * Time: 15:50
 * Desc: 自定义adapter,label支持多选
 */

public class LabelAdapter extends BaseLabelAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mDataList;
    private List<String> mChooseList;

    public LabelAdapter(Context context, List<String> dataList, List<String> chooseList) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mDataList = dataList;
        this.mChooseList = chooseList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public View getView(ViewGroup parent, int position) {
        View contentView = mLayoutInflater.inflate(R.layout.item_label, null);
        TextView tvLabelName = (TextView) contentView.findViewById(R.id.tv_label_name);
        String tempName = mDataList.get(position);
        tvLabelName.setText(tempName);
        contentView.setTag(tvLabelName);
        if (mChooseList.contains(tempName)) {
            tvLabelName.setSelected(true);
        } else {
            tvLabelName.setSelected(false);
        }
        return contentView;
    }

    public void updateData(List<String> chooseList) {
        this.mChooseList = chooseList;
        notifyDataSetChanged();
    }
}
