package org.myworld.qfhc.myworld.activity;

import android.app.ProgressDialog;
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
import org.myworld.qfhc.myworld.adapter.ThirdDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/2 00:38
 * @备注：
 */
public class ThirdHeadDetailActivity extends BaseActivity implements VolleyUtil.OnRequestListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, AbsListView.OnScrollListener {

    private ListView mLv;
    private TextView tvTitle;
    private boolean isLoading;
    private boolean isBottom=false;
    private int currentPage = 0;
    private int count=0;
    private SwipeRefreshLayout srl;
    private ThirdDetailAdapter adapter;
    private String formatUrl;
    private ImageView imageView ,ivRefres;
    private String murl;
    private int extend;
    private View footer;
    private ImageView ivRefresh;
    private LinearLayout llWangluo;
    private List<ThirdDetailEntity.DataEntity.ListEntity> datas;

    @Override
    protected int getContentResid() {
        return R.layout.third_detail_layout;
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
        murl = intent.getStringExtra(Constant.KEYS.THIRD_DETAIL_URL);
        // L.e(url+"_______________________________________");
        String third_extend = intent.getStringExtra(Constant.KEYS.THIRD_DETAIL_ID);
        extend = Integer.valueOf(third_extend);
        String title = intent.getStringExtra(Constant.KEYS.THIRD_DETAIL_TITLE);

        formatUrl = String.format(murl, currentPage, extend);

        tvTitle = (TextView) findViewById(R.id.tv_third_top_detail_title);
        tvTitle.setText(title);
        imageView = (ImageView) findViewById(R.id.iv_third_detail_back);
        imageView.setOnClickListener(this);

        footer = LayoutInflater.from(this).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh= (ImageView)footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        mLv = (ListView) findViewById(R.id.lv_third_detail);
        srl = (SwipeRefreshLayout) findViewById(R.id.srl_third_footer_detail);
        srl.setColorSchemeResources(R.color.colorAccent);
        srl.setOnRefreshListener(this);

        mLv.setVisibility(View.INVISIBLE);
        mLv.addFooterView(footer);
        mLv.setOnScrollListener(this);
        adapter = new ThirdDetailAdapter(this);
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
            ThirdDetailEntity.DataEntity thirdDetailByJson = JSONUtil.getThirdDetailByJson(response);
            List<ThirdDetailEntity.DataEntity.ListEntity> list = thirdDetailByJson.getList();
            //L.e(thirdDetailByJson + "==========================================");
            datas.addAll(list);
            adapter.addDatas(list);
            if (thirdDetailByJson != null) {
                srl.setRefreshing(false);
            }
            onLoadComplete();
            count+=10;
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        srl.setRefreshing(false);
        mLv.setVisibility(View.INVISIBLE);
        ivRefres.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefres.setVisibility(View.VISIBLE);
                currentPage=0;
                count=0;
                datas.clear();
                formatUrl = String.format(murl, currentPage, extend);
                VolleyUtil.requestString(formatUrl,ThirdHeadDetailActivity.this);
            }
        });

    }

    @Override
    public void onRefresh() {
        currentPage=0;
        count=0;
        datas.clear();
        formatUrl = String.format(murl, currentPage, extend);
        VolleyUtil.requestString(formatUrl,this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 屏幕空闲且数据到了底部
        if ((scrollState == SCROLL_STATE_IDLE) && isBottom) {

            currentPage += 1;

            if (!isLoading){
                isLoading=true;
                footer.setVisibility(View.VISIBLE);

                // 加载新数据
                formatUrl = String.format(murl, currentPage, extend);
                VolleyUtil.requestString(formatUrl,this);

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

    public void onLoadComplete(){
        isLoading=false;
        this.footer.setVisibility(View.GONE);
        mLv.setSelection(count);
    }
}
