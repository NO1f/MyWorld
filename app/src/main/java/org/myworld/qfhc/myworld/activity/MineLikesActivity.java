package org.myworld.qfhc.myworld.activity;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 21:23
 * @备注：
 */
public class MineLikesActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBAck;
    private GridView mGv;

    @Override
    protected int getContentResid() {
        return R.layout.mine_likes;
    }

    @Override
    protected void init() {
        ivBAck = (ImageView) findViewById(R.id.iv_search_detail_back);
        ivBAck.setOnClickListener(this);

        /*mGv= (GridView) findViewById(R.id.gv_mine);

        mGv.setAdapter();*/
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
