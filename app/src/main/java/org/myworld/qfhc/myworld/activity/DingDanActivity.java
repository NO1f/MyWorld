package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.util.Constant;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/9 17:59
 * @备注：
 */
public class DingDanActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlTaobao, rlTianmao;

    @Override
    protected int getContentResid() {
        return R.layout.dingdan_layout;
    }

    @Override
    protected void init() {

        rlTaobao = (RelativeLayout) findViewById(R.id.rl_taobao);
        rlTaobao.setOnClickListener(this);
        rlTianmao = (RelativeLayout) findViewById(R.id.rl_tianmao);
        rlTianmao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.rl_taobao:
                Intent intent=new Intent(this,DingDanDetailActivity.class);
                intent.putExtra(Constant.KEYS.DINGDAN,"https://h5.m.taobao.com/mlapp/olist.html");
                startActivity(intent);
                break;
            case R.id.rl_tianmao:
                Intent intent1=new Intent(this,DingDanDetailActivity.class);
                intent1.putExtra(Constant.KEYS.DINGDAN,"http://home.m.jd.com/newAllOrders/newAllOrders.action");
                startActivity(intent1);
                break;
        }

    }
}
