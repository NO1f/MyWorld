package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.ThirdBottomDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.custom.ThirdDetailHeadView;
import org.myworld.qfhc.myworld.entity.ThirdBottomDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/4 15:10
 * @备注：
 */
public class ThirdBottomDetailActivity extends BaseActivity implements VolleyUtil.OnRequestListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {

    private boolean isLoading;
    private boolean isBottom = false;
    private int currentPage = 0;
    private int count = 0;

    private ListView mLv;
    private TextView tvTitle;
    private SwipeRefreshLayout srl;
    private View footer;
    private ImageView ivRefresh;
    private ImageView ivRefres;
    private LinearLayout llWangluo;
    private ThirdBottomDetailAdapter adapter;
    private String formatUrl;
    private int id;
    private List<ThirdBottomDetailEntity.DataEntity.PostListEntity> datas;
    private String murl;
    private ThirdDetailHeadView headView;

    @Override
    protected int getContentResid() {
        return R.layout.third_bottom_detail_layout;
    }

    @Override
    protected void init() {

        ivRefres= (ImageView)findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefres.getBackground();
        bg.start();
        llWangluo = (LinearLayout) findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        datas = new ArrayList<>();
        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_ID);
        id = Integer.valueOf(mid);
        String name = intent.getStringExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_NAME);

        murl = String.format(Constant.URL.THIRD_ONE_BOTTOM, 0, id);
        headView = new ThirdDetailHeadView(this);
        headView.setUrl(murl);

        formatUrl = String.format(Constant.URL.THIRD_ONE_BOTTOM, currentPage, id);

        tvTitle = (TextView) findViewById(R.id.tv_third_detail_title);
        tvTitle.setText(name);

        footer = LayoutInflater.from(this).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl_third_bottom_detail);
        srl.setColorSchemeResources(R.color.colorAccent);
        srl.setOnRefreshListener(this);

        mLv = (ListView) findViewById(R.id.lv_third_bottom_detail);
        mLv.addHeaderView(headView);
        mLv.addFooterView(footer);
        mLv.setVisibility(View.INVISIBLE);
        mLv.setOnScrollListener(this);
        adapter = new ThirdBottomDetailAdapter(this);
        mLv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        VolleyUtil.requestString(formatUrl, this);
    }

    @Override
    public void onResponse(String url, String response) {
        ivRefres.setVisibility(View.INVISIBLE);
        mLv.setVisibility(View.VISIBLE);
        if (response != null) {
            ThirdBottomDetailEntity.DataEntity thirdBottomDetailByJson = JSONUtil.getThirdBottomDetailByJson(response);
            List<ThirdBottomDetailEntity.DataEntity.PostListEntity> post_list = thirdBottomDetailByJson.getPost_list();
            //L.e(thirdDetailByJson + "==========================================");
            datas.addAll(post_list);
            adapter.addDatas(post_list);
            if (thirdBottomDetailByJson != null) {
                srl.setRefreshing(false);
            }
            onLoadComplete();
            count += 10;
        }
    }

    @Override
    public void onErrorResponse(final String url, VolleyError error) {
        mLv.setVisibility(View.INVISIBLE);
        ivRefres.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headView.setUrl(Constant.URL.THIRD_ONE);
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefres.setVisibility(View.VISIBLE);
                currentPage = 0;
                count = 0;
                datas.clear();
                headView.setUrl(murl);
                VolleyUtil.requestString(murl, ThirdBottomDetailActivity.this);
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = 0;
        count = 0;
        datas.clear();
        formatUrl = String.format(Constant.URL.THIRD_ONE_BOTTOM, currentPage, id);
        VolleyUtil.requestString(formatUrl, this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 屏幕空闲且数据到了底部
        if ((scrollState == SCROLL_STATE_IDLE) && isBottom) {

            currentPage += 1;

            if (!isLoading) {
                isLoading = true;
                footer.setVisibility(View.VISIBLE);

                // 加载新数据
                formatUrl = String.format(Constant.URL.THIRD_ONE_BOTTOM, currentPage, id);
                VolleyUtil.requestString(formatUrl, this);
            }
        }
        isBottom = false;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // 数据到了最后一条
        if (firstVisibleItem + visibleItemCount == totalItemCount) {
            isBottom = true;
        }
    }

    public void onLoadComplete() {
        isLoading = false;
        this.footer.setVisibility(View.GONE);
        mLv.setSelection(count);
    }

    public void jia(View v) {
        finish();
    }
}
