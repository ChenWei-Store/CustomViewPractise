package com.android.cc.customviewpractise.activity;


import android.view.View;

import com.android.cc.customviewpractise.R;
import com.android.cc.customviewpractise.view.CircleNumView;

public class CircleNumActivity extends BaseActivity {
    private CircleNumView circleNumView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_circle_num;
    }

    @Override
    protected void initView() {
        circleNumView = (CircleNumView)findViewById(R.id.view_circle_num);
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = circleNumView.getNum();
                circleNumView.setNum(++num);
            }
        });
    }

}
