package com.android.cc.customviewpractise.activity;

import android.content.Intent;
import android.view.View;

import com.android.cc.customviewpractise.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_circle_num).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CircleNumActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_goods_num_control).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoodsNumControllActivity.class);
                startActivity(intent);
            }
        });
    }




}
