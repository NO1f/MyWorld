package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
public class SearchMainTieZiFragment extends BaseFragment implements VolleyUtil.OnRequestListener, AbsAdapter.OnClickListener {

    private int pageSize = 10;
    private int count = 0;
    private String keyword;
    private RecyclerView recyclerView;
    private SearchTieZiAdapter adapter;
    private List<SearchTieZiEntity.DataEntity> datas;
    private String url;
    private GridLayoutManager gridLayoutManager;

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

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        keyword = bundle.getString(Constant.KEYS.KEYWORD);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_search);

        gridLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
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

        if (response != null) {
            List<SearchTieZiEntity.DataEntity> searchTieziByJson =  JSONUtil.getSearchTieziByJson(response);

            datas.addAll(searchTieziByJson);
            adapter.setDatas(searchTieziByJson);
            gridLayoutManager.scrollToPositionWithOffset(count, 0);
            count+=10;

        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

    @Override
    public void onClick(View v, int position) {
        SearchTieZiEntity.DataEntity dataEntity = datas.get(position);
        String id = dataEntity.getId();
        Intent intent=new Intent(getActivity(),SearchTieZiDetailActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_ONE_ID,id);
        startActivity(intent);

    }
}
