package org.myworld.qfhc.myworld.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailTwoDetActivity;
import org.myworld.qfhc.myworld.adapter.SearchQingDanAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.SearchQingDanEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 00:30
 * @备注：
 */
public class SearchMainQingDanFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener, VolleyUtil.OnRequestListener {

    private boolean isLoading;
    private boolean isBottom = false;
    private int currentPage = 0;
    private int count = 0;

    private String keyword;
    private String url;

    private View footer;
    private ListView mLv;
    private ImageView ivRefresh;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchQingDanAdapter adapter;
    private List<SearchQingDanEntity.DataEntity> datas;
    private LinearLayout llResult,llWangluo;
    private ProgressDialog progressDialog;

    public static SearchMainQingDanFragment newInstance(String keyword) {

        Bundle args = new Bundle();
        args.putString(Constant.KEYS.KEYWORD, keyword);
        SearchMainQingDanFragment fragment = new SearchMainQingDanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_two_bottom;
    }

    @Override
    protected void init(View view) {

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.show();


        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        keyword = bundle.getString(Constant.KEYS.KEYWORD);

        footer = LayoutInflater.from(getActivity()).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_search_detail_two);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        llResult = (LinearLayout) view.findViewById(R.id.ll_result);
        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);

        llResult.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.INVISIBLE);


        mLv = (ListView) view.findViewById(R.id.lv_search_detail_two);
        adapter = new SearchQingDanAdapter(getActivity());
        mLv.addFooterView(footer);
        mLv.setOnScrollListener(this);
        mLv.setOnItemClickListener(this);
        mLv.setAdapter(adapter);

    }

    @Override
    protected void loadData() {
        url = String.format(Constant.URL.SEARCH_QINGDAN, keyword, currentPage);
        VolleyUtil.requestString(url, this);
    }

    @Override
    public void onResponse(String url, String response) {
        progressDialog.dismiss();
        if (response != null) {
            List<SearchQingDanEntity.DataEntity> searchQingdanByJson =  JSONUtil.getSearchQingdanByJson(response);
            datas.addAll(searchQingdanByJson);
            adapter.addDatas(searchQingdanByJson);
            if (searchQingdanByJson != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
            onLoadComplete();
            count += 10;
        }
    }

    @Override
    public void onErrorResponse(final String url1, VolleyError error) {

        progressDialog.dismiss();
        llResult.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                progressDialog.show();
                currentPage = 0;
                count = 0;
                datas.clear();

                url = String.format(Constant.URL.SEARCH_QINGDAN, keyword, currentPage);
                VolleyUtil.requestString(url1, SearchMainQingDanFragment.this);
            }
        });

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

                url = String.format(Constant.URL.SEARCH_QINGDAN, keyword, currentPage);
                VolleyUtil.requestString(url, this);
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

    @Override
    public void onRefresh() {

        currentPage = 0;
        count = 0;
        datas.clear();

        url = String.format(Constant.URL.SEARCH_QINGDAN, keyword, currentPage);
        VolleyUtil.requestString(url, this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mid = datas.get(position).getId();
        Intent intent = new Intent(getActivity(), SearchDetailTwoDetActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_ID, mid);
        startActivity(intent);
    }
}
