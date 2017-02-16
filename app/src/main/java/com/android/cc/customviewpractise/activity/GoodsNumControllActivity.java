package com.android.cc.customviewpractise.activity;

import android.widget.Toast;

import com.android.cc.customviewpractise.R;
import com.android.cc.customviewpractise.view.GoodsNumControllerView;

public class GoodsNumControllActivity extends BaseActivity {
    private GoodsNumControllerView goodsNumControllerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_num_controll;
    }

    @Override
    protected void initView() {
        goodsNumControllerView = (GoodsNumControllerView) findViewById(R.id.tv_goods_num_control);
        goodsNumControllerView.setOnGoodsNumChangedListener(new GoodsNumControllerView.OnGoodsNumChangedListener() {
            @Override
            public void onClickLeftChanged(int numValue) {
                Toast.makeText(GoodsNumControllActivity.this, "value:" + numValue,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickRightChanged(int numValue) {
                Toast.makeText(GoodsNumControllActivity.this, "value:" + numValue,
                        Toast.LENGTH_SHORT).show();
            }
        });
     }

}
