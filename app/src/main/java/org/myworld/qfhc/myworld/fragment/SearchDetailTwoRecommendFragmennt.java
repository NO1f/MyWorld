package org.myworld.qfhc.myworld.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.SearchRecommentAdapter;
import org.myworld.qfhc.myworld.adapter.ThirdDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.MyItemDecoration;
import org.myworld.qfhc.myworld.custom.OnRcvScrollListener;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 13:37
 * @备注：
 */
public class SearchDetailTwoRecommendFragmennt extends BaseFragment implements VolleyUtil.OnRequestListener {

    private int pageSize = 10;
    private int count = 0;
    private int id;
    private RecyclerView recyclerView;
    private SearchRecommentAdapter adapter;
    private List<ThirdDetailEntity.DataEntity.ListEntity> datas;
    private String url;
    private GridLayoutManager gridLayoutManager;

    private ImageView ivRefresh;
    private LinearLayout llWangluo;

    public static SearchDetailTwoRecommendFragmennt newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(Constant.KEYS.SEARCH_RECOMMENT, id);
        SearchDetailTwoRecommendFragmennt fragment = new SearchDetailTwoRecommendFragmennt();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_two_detail_chose;
    }

    @Override
    protected void init(View view) {

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable)ivRefresh.getBackground();
        bg.start();
        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        id = bundle.getInt(Constant.KEYS.SEARCH_RECOMMENT, -1);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_search);
       /* linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);*/
        gridLayoutManager = new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        adapter = new SearchRecommentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                // 加载新数据
                url = String.format(Constant.URL.SEARCH_DETAIL_RECOMMEND, pageSize, id);
                VolleyUtil.requestString(url, SearchDetailTwoRecommendFragmennt.this);
            }
        });

    }

    @Override
    protected void loadData() {
        url = String.format(Constant.URL.SEARCH_DETAIL_RECOMMEND, pageSize, id);
        VolleyUtil.requestString(url, this);
    }

    @Override
    public void onResponse(String url, String response) {

        if (response != null) {
            ThirdDetailEntity.DataEntity thirdDetailByJson = JSONUtil.getThirdDetailByJson(response);
            List<ThirdDetailEntity.DataEntity.ListEntity> list = thirdDetailByJson.getList();
            datas.addAll(list);
            adapter.setDatas(list);
            gridLayoutManager.scrollToPositionWithOffset(count, 0);
            count+=10;

        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

}
