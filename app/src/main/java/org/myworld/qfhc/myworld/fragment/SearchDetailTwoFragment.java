package org.myworld.qfhc.myworld.fragment;

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
import org.myworld.qfhc.myworld.adapter.SearchTwoDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.SearchDetailOneEntity;
import org.myworld.qfhc.myworld.entity.SearchTwoEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 09:26
 * @备注：
 */
public class SearchDetailTwoFragment extends BaseFragment implements VolleyUtil.OnRequestListener, SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    private boolean isLoading;
    private boolean isBottom = false;
    private int currentPage = 0;
    private int count = 0;

    private int position;
    private String url;
    int id;

    private View footer;
    private ListView mLv;
    private ImageView ivRefresh,ivRefres;
    private LinearLayout llWangluo;
    private LinearLayout llResult;

    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchTwoDetailAdapter adapter;
    private List<SearchTwoEntity.DataEntity.TopicEntity> datas;

    public static SearchDetailTwoFragment newInstance(Bundle bundle) {

        SearchDetailTwoFragment fragment = new SearchDetailTwoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_two_bottom;
    }

    @Override
    protected void init(View view) {


        llResult= (LinearLayout) view.findViewById(R.id.ll_result);
        llResult.setVisibility(View.INVISIBLE);

        ivRefres= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefres.getBackground();
        bg.start();
        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        datas = new ArrayList<>();
        Bundle bundle = getArguments();
        position = bundle.getInt(Constant.KEYS.INDEX_CONTENT_POSITION, -1);
        id = bundle.getInt(Constant.KEYS.SEARCH_TWO_ID, -1);
        //L.e(id+"_____________________________________________________--");

        footer = LayoutInflater.from(getActivity()).inflate(R.layout.third_detail_footer_layout, null);
        ivRefresh = (ImageView) footer.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        footer.findViewById(R.id.ll_footer).setVisibility(View.GONE);

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.srl_search_detail_two);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        mLv= (ListView) view.findViewById(R.id.lv_search_detail_two);
        adapter = new SearchTwoDetailAdapter(getActivity());
        mLv.setVisibility(View.INVISIBLE);
        mLv.addFooterView(footer);
        mLv.setOnScrollListener(this);
        mLv.setOnItemClickListener(this);
        mLv.setAdapter(adapter);

    }

    @Override
    protected void loadData() {

        if (position==0){
            url = String.format(Constant.URL.SEARCH_DETAIL_TWO_NEWS,id,currentPage);
            VolleyUtil.requestString(url,this);
        }else if (position==1){
            url=String.format(Constant.URL.SEARCH_DETAIL_TWO_HOST,id,currentPage);
            VolleyUtil.requestString(url,this);
        }

    }

    @Override
    public void onResponse(String url, String response) {
        ivRefres.setVisibility(View.INVISIBLE);
        mLv.setVisibility(View.VISIBLE);
        if (response!=null){
            List<SearchTwoEntity.DataEntity.TopicEntity> searchDetailTwoByJson = JSONUtil.getSearchDetailTwoByJson(response);
            datas.addAll(searchDetailTwoByJson);
            adapter.addDatas(searchDetailTwoByJson);
            if (searchDetailTwoByJson != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
            onLoadComplete();
            count += 10;
        }
    }

    @Override
    public void onErrorResponse(String url1, VolleyError error) {
        mLv.setVisibility(View.INVISIBLE);
        ivRefres.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                currentPage=0;
                count=0;
                datas.clear();

                if (position==0){
                    url = String.format(Constant.URL.SEARCH_DETAIL_TWO_NEWS,id,currentPage);
                }else if (position==1){
                    url = String.format(Constant.URL.SEARCH_DETAIL_TWO_HOST,id,currentPage);
                }

                VolleyUtil.requestString(url,SearchDetailTwoFragment.this);
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
                if (position==0){
                    url = String.format(Constant.URL.SEARCH_DETAIL_TWO_NEWS,id,currentPage);
                }else if (position==1){
                    url = String.format(Constant.URL.SEARCH_DETAIL_TWO_HOST,id,currentPage);
                }
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

        currentPage=0;
        count=0;
        datas.clear();

        if (position==0){
            url = String.format(Constant.URL.SEARCH_DETAIL_TWO_NEWS,id,currentPage);
        }else if (position==1){
            url = String.format(Constant.URL.SEARCH_DETAIL_TWO_HOST,id,currentPage);
        }

        VolleyUtil.requestString(url,this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String mid = datas.get(position).getId();
        Intent intent=new Intent(getActivity(),SearchDetailTwoDetActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_ID,mid);
        startActivity(intent);
    }
}
