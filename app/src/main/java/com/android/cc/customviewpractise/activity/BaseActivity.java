package com.android.cc.customviewpractise.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.cc.customviewpractise.R;

/**
 * author: ChenWei
 * create date: 2017/1/15
 * description: Activity基类，封装了toolbar的处理
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initToolbar();
        initView();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.app_name));
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
}
