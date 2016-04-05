package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.SearchDetailOneAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.SearchDetailOneEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 15:48
 * @备注：
 */
public class SearchDetailOneActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, VolleyUtil.OnRequestListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private boolean isLoading;
    private boolean isBottom = false;
    private int currentPage = 0;
    private int count = 0;

    private TextView tvTitle;
    private ImageView ivBack, ivRefresh;
    private ListView mLv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int id;
    private String formatUrl;
    private View footer;
    private SearchDetailOneAdapter adapter;
    private List<SearchDetailOneEntity.DataEntity.ListEntity> searchDetailOneByJson;

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_one;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.SEARCH_ONE_ID);
        String name = intent.getStringExtra(Constant.KEYS.SEARCH_ONE_NAME);
        id = Integer.valueOf(mid);

        formatUrl = String.format(Constant.URL.SEARCH_DETAIL_ONE, id, currentPage);

        tvTitle = (TextView) findViewById(R.id.tv_search_detail_title);
        tvTitle.setText(name);

        ivBack = (ImageView) findViewById(R.id.iv_search_detail_back);
        ivBack.setOnClickListener(this);

        footer = LayoutInflater.from(this).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_search_detail_one);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        mLv = (ListView) findViewById(R.id.lv_search_detail_one);
        mLv.setOnItemClickListener(this);
        mLv.setOnScrollListener(this);
        adapter = new SearchDetailOneAdapter(this);
        mLv.setAdapter(adapter);


    }

    @Override
    protected void initData() {
        VolleyUtil.requestString(formatUrl, this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    @Override
    public void onResponse(String url, String response) {
        searchDetailOneByJson = JSONUtil.getSearchDetailOneByJson(response);
        adapter.addDatas(searchDetailOneByJson);
        if (searchDetailOneByJson != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        onLoadComplete();
        count += 10;
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(SearchDetailOneActivity.this, SearchDetailOneDetActivity.class);
        String mid = searchDetailOneByJson.get(position).getId();
        intent.putExtra(Constant.KEYS.SEARCH_ONE_DETAIL_ID,mid);
        startActivity(intent);
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
                formatUrl = String.format(formatUrl, currentPage, id);
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
}
