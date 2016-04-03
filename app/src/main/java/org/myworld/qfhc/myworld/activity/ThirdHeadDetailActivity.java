package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.ThirdDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;
import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/2 00:38
 * @备注：
 */
public class ThirdHeadDetailActivity extends BaseActivity implements VolleyUtil.OnRequestListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView mLv;
    private TextView tvTitle;
    private SwipeRefreshLayout srl;
    private ThirdDetailAdapter adapter;
    private int page=0;
    private String formatUrl;

    @Override
    protected int getContentResid() {
        return R.layout.third_detail_layout;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String url = intent.getStringExtra(Constant.KEYS.INDEX_DETAIL_URL);
        String id = intent.getStringExtra(Constant.KEYS.THIRD_DETAIL_ID);
        String title = intent.getStringExtra(Constant.KEYS.THIRD_DETAIL_TITLE);

        formatUrl = String.format(url, page, id);

        tvTitle= (TextView) findViewById(R.id.tv_third_top_detail_title);
        tvTitle.setText(title);
        mLv = (ListView) findViewById(R.id.lv_third_detail);
        srl= (SwipeRefreshLayout) findViewById(R.id.srl_third_footer_detail);
        srl.setOnRefreshListener(this);

        adapter = new ThirdDetailAdapter(this);
        mLv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        VolleyUtil.requestString(formatUrl,this);
    }

    @Override
    public void onResponse(String url, String response) {
        if (response!=null){
            List<ThirdDetailEntity> thirdDetailByJson = JSONUtil.getThirdDetailByJson(response);
            adapter.setDatas(thirdDetailByJson);
            if (thirdDetailByJson!=null){
                srl.setRefreshing(false);
            }
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        Toast.makeText(ThirdHeadDetailActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        initData();
    }
}
