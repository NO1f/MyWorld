package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.ThirdBottomDetailAdapter;
import org.myworld.qfhc.myworld.adapter.ThirdDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.custom.ThirdDetailHeadView;
import org.myworld.qfhc.myworld.entity.ThirdBottomDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/4 15:10
 * @备注：
 */
public class ThirdBottomActivity extends BaseActivity implements VolleyUtil.OnRequestListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, View.OnClickListener {

    private boolean isLoading;
    private boolean isBottom=false;
    private int currentPage = 0;
    private int count=0;

    private ListView mLv;
    private TextView tvTitle;
    private ImageView ivBack,ivShare;
    private SwipeRefreshLayout srl;
    private View footer;
    private ImageView ivRefresh;
    private ThirdBottomDetailAdapter adapter;
    private String formatUrl;
    private int id;

    @Override
    protected int getContentResid() {
        return R.layout.third_bottom_detail_layout;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_ID);
        id = Integer.valueOf(mid);
        String name = intent.getStringExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_NAME);

        String url =  String.format(Constant.URL.THIRD_ONE_BOTTOM,0, id);
        ThirdDetailHeadView headView=new ThirdDetailHeadView(this);
        headView.setUrl(url);

        formatUrl = String.format(Constant.URL.THIRD_ONE_BOTTOM,currentPage, id);

        ivBack= (ImageView) findViewById(R.id.iv_third_detail_bottom_back);
        ivShare= (ImageView) findViewById(R.id.iv_third_detail_bottom_share);
        ivBack.setOnClickListener(this);
        ivShare.setOnClickListener(this);

        tvTitle= (TextView) findViewById(R.id.tv_third_detail_title);
        tvTitle.setText(name);

        footer = LayoutInflater.from(this).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh= (ImageView)footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl_third_bottom_detail);
        srl.setColorSchemeResources(R.color.colorAccent);
        srl.setOnRefreshListener(this);

        mLv= (ListView) findViewById(R.id.lv_third_bottom_detail);
        mLv.addHeaderView(headView);
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

        if (response != null) {
            ThirdBottomDetailEntity.DataEntity thirdBottomDetailByJson = JSONUtil.getThirdBottomDetailByJson(response);
            List<ThirdBottomDetailEntity.DataEntity.PostListEntity> post_list = thirdBottomDetailByJson.getPost_list();
            //L.e(thirdDetailByJson + "==========================================");
            adapter.addDatas(post_list);
            if (thirdBottomDetailByJson != null) {
                srl.setRefreshing(false);
            }
            onLoadComplete();
            count+=10;
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        Toast.makeText(ThirdBottomActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        initData();
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
                formatUrl = String.format(Constant.URL.THIRD_ONE_BOTTOM, currentPage,id);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_third_detail_bottom_back:
                finish();
                break;
            case R.id.iv_third_detail_bottom_share:
                Toast.makeText(ThirdBottomActivity.this, "此处要分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
