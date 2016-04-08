package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchTieZiDetailActivity;
import org.myworld.qfhc.myworld.adapter.SearchRecommentAdapter;
import org.myworld.qfhc.myworld.adapter.SearchTieZiAdapter;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.MyItemDecoration;
import org.myworld.qfhc.myworld.custom.OnRcvScrollListener;
import org.myworld.qfhc.myworld.entity.SearchTieZiEntity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:05
 * @备注：
 */
public class SearchMainTieZiFragment extends BaseFragment implements VolleyUtil.OnRequestListener, AbsAdapter.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private int pageSize = 10;
    private int count = 0;
    private String keyword;
    private RecyclerView recyclerView;
    private SearchTieZiAdapter adapter;
    private List<SearchTieZiEntity.DataEntity> datas;
    private String url;
    private GridLayoutManager gridLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ImageView ivRefresh;
    private LinearLayout llWangluo;

    public static SearchMainTieZiFragment newInstance(String keyword) {

        Bundle args = new Bundle();
        args.putString(Constant.KEYS.KEYWORD, keyword);
        SearchMainTieZiFragment fragment = new SearchMainTieZiFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_two_detail_chose;
    }

    @Override
    protected void init(View view) {

        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefresh.getBackground();
        bg.start();

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        keyword = bundle.getString(Constant.KEYS.KEYWORD);

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl_search_detail_two);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_search);
        recyclerView.setVisibility(View.INVISIBLE);

        gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SearchTieZiAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(this);
        recyclerView.setOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                // 加载新数据
                url = String.format(Constant.URL.SEARCH_TIEZI, keyword, pageSize);
                VolleyUtil.requestString(url, SearchMainTieZiFragment.this);
            }
        });

    }

    @Override
    protected void loadData() {
        url = String.format(Constant.URL.SEARCH_TIEZI, keyword, pageSize);
        VolleyUtil.requestString(url, this);
    }

    @Override
    public void onResponse(String url, String response) {
        recyclerView.setVisibility(View.VISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        if (response != null) {
            List<SearchTieZiEntity.DataEntity> searchTieziByJson =  JSONUtil.getSearchTieziByJson(response);

            datas.addAll(searchTieziByJson);
            adapter.setDatas(searchTieziByJson);
            if (searchTieziByJson!=null){
                swipeRefreshLayout.setRefreshing(false);
            }
            gridLayoutManager.scrollToPositionWithOffset(count, 0);
            count+=10;

        }

    }

    @Override
    public void onErrorResponse(String url1, VolleyError error) {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                pageSize=10;
                count=0;
                datas.clear();
                url = String.format(Constant.URL.SEARCH_TIEZI, keyword, pageSize);
                VolleyUtil.requestString(url, SearchMainTieZiFragment.this);
            }
        });

    }

    @Override
    public void onClick(View v, int position) {
        SearchTieZiEntity.DataEntity dataEntity = datas.get(position);
        String id = dataEntity.getId();
        Intent intent=new Intent(getActivity(),SearchTieZiDetailActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_ONE_ID,id);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {
        pageSize = 10;
        count = 0;
        datas.clear();
        url = String.format(Constant.URL.SEARCH_TIEZI, keyword, pageSize);
        VolleyUtil.requestString(url, this);
    }
}
