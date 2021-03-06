package org.myworld.qfhc.myworld.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailOneDetActivity;
import org.myworld.qfhc.myworld.adapter.SearchDanPinAdapter;
import org.myworld.qfhc.myworld.adapter.SearchDetailOneAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.SearchDanPinEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:08
 * @备注：
 */
public class SearchMainDanPinFragment extends BaseFragment implements VolleyUtil.OnRequestListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener, SwipeRefreshLayout.OnRefreshListener {

    private boolean isLoading;
    private boolean isBottom = false;
    private int currentPage = 0;
    private int count = 0;

    private String keyword;
    private String formatUrl;
    private ImageView ivRefresh,ivRefres;
    private ListView mLv;
    private View footer;
    private List<SearchDanPinEntity.DataEntity> datas;
    private SearchDanPinAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout llResult,llWangluo;

    public static SearchMainDanPinFragment newInstance(String keyword) {

        Bundle args = new Bundle();
        args.putString(Constant.KEYS.KEYWORD, keyword);
        SearchMainDanPinFragment fragment = new SearchMainDanPinFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_two_bottom;
    }

    @Override
    protected void init(View view) {
        ivRefres= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefres.getBackground();
        bg.start();

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        keyword = bundle.getString(Constant.KEYS.KEYWORD);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_search_detail_two);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        footer = LayoutInflater.from(getActivity()).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();

        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);
        llResult = (LinearLayout) view.findViewById(R.id.ll_result);
        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);

        llResult.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.INVISIBLE);

        mLv = (ListView) view.findViewById(R.id.lv_search_detail_two);
        mLv.setOnItemClickListener(this);
        mLv.setOnScrollListener(this);
        mLv.addFooterView(footer);
        mLv.setVisibility(View.INVISIBLE);
        adapter = new SearchDanPinAdapter(getActivity());
        mLv.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

        formatUrl = String.format(Constant.URL.SEARCH_DANPIN, keyword, currentPage);
        VolleyUtil.requestString(formatUrl, this);

    }

    @Override
    public void onResponse(String url, String response) {
        mLv.setVisibility(View.VISIBLE);
        ivRefres.setVisibility(View.INVISIBLE);
        if (response != null) {
            List<SearchDanPinEntity.DataEntity> searchDanpinByJson = JSONUtil.getSearchDanpinByJson(response);
            if (searchDanpinByJson.size() != 0) {
                datas.addAll(searchDanpinByJson);

                adapter.addDatas(searchDanpinByJson);
                if (searchDanpinByJson != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                onLoadComplete();
                count += 10;
            }else {
                llWangluo.setVisibility(View.INVISIBLE);
                llResult.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        swipeRefreshLayout.setRefreshing(false);
        mLv.setVisibility(View.INVISIBLE);
        ivRefres.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefres.setVisibility(View.VISIBLE);
                currentPage = 0;
                count = 0;
                datas.clear();
                formatUrl = String.format(Constant.URL.SEARCH_DANPIN, keyword, currentPage);
                VolleyUtil.requestString(formatUrl, SearchMainDanPinFragment.this);
            }
        });

    }

    @Override
    public void onRefresh() {
        currentPage = 0;
        count = 0;
        datas.clear();
        formatUrl = String.format(Constant.URL.SEARCH_DANPIN, keyword, currentPage);
        VolleyUtil.requestString(formatUrl, this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        L.e(position + "___________________________________________--");
        Intent intent = new Intent(getActivity(), SearchDetailOneDetActivity.class);
        String mid = datas.get(position).getId();
        String pic = datas.get(position).getPic();
        intent.putExtra(Constant.KEYS.SEARCH_ONE_DETAIL_ID, mid);
        intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_PIC,pic);
        startActivity(intent);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 屏幕空闲且数据到了底部
        if ((scrollState == SCROLL_STATE_IDLE) && isBottom) {
            swipeRefreshLayout.setRefreshing(false);
            currentPage += 1;

            if (!isLoading) {
                isLoading = true;
                footer.setVisibility(View.VISIBLE);

                // 加载新数据
                formatUrl = String.format(Constant.URL.SEARCH_DANPIN, keyword, currentPage);
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
