package com.haoyh.flowlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.haoyh.customflowlayout.library.LabelFlowLayout;
import com.haoyh.customflowlayout.library.LabelFlowLayout.OnLabelItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnLabelItemClickListener {

    private LabelFlowLayout mLabelFlowLayout;
    private TextView mTvShowChooseLabel;

    private List<String> mDataList;
    private LabelAdapter mLabelAdapter;
    private List<String> mChooseList;

    private int MAX_CHOOSE_SIZE = 3; // 定义最大可以选择个数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mChooseList = new ArrayList<>();
        mDataList = new ArrayList<>();
        mDataList.add("不限");
        mDataList.add("移动互联网");
        mDataList.add("农业");
        mDataList.add("计算机");
        mDataList.add("工业");
        mDataList.add("Hello World");
        mLabelAdapter = new LabelAdapter(this, mDataList, mChooseList);
        mLabelFlowLayout.setAdapter(mLabelAdapter);
    }

    private void initViews() {
        mLabelFlowLayout = (LabelFlowLayout) findViewById(R.id.lable_flow_layout);
        mLabelFlowLayout.setOnLabelItemClickListener(this);
        mTvShowChooseLabel = (TextView) findViewById(R.id.tv_show_choose_label);
    }

    @Override
    public void onLabelItemClick(int parentId, View view, int position) {
        if (parentId == R.id.lable_flow_layout) {
            String chooseItem = (String) mLabelAdapter.getItem(position);
            if (mChooseList.contains(chooseItem)) {
                mChooseList.remove(chooseItem);
            } else {
                if (mChooseList.size() >= MAX_CHOOSE_SIZE) {
                    Toast.makeText(MainActivity.this, "最大可以选择数：" + MAX_CHOOSE_SIZE, Toast.LENGTH_LONG).show();
                    return;
                }
                mChooseList.add(chooseItem);
            }
            mLabelAdapter.updateData(mChooseList);
            String aa = "";
            for (String temp : mChooseList) {
                aa = aa + temp + ";";
            }
            mTvShowChooseLabel.setText(aa);
        }
    }
}
