package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchActivity;
import org.myworld.qfhc.myworld.activity.ThirdBottomDetailActivity;
import org.myworld.qfhc.myworld.adapter.ThirdAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.ThirdHeadView;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class TuanFragment extends BaseFragment implements VolleyUtil.OnRequestListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private ListView mLv;
    private List<ThirdHeadEntity.DataEntity.RecGroupsEntity> rec_groups;
    private ThirdAdapter adapter;
    private List<ThirdHeadEntity.DataEntity.RecGroupsEntity> datas;

    private int p = 0;
    private View footerView;
    private TextView tvChange;
    private ImageView ivGuang,ivRefresh;
    private LinearLayout llWangluo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ThirdHeadView headView;

    public static TuanFragment newInstance() {
        Bundle args = new Bundle();
        TuanFragment fragment = new TuanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.tuan_fragment;
    }

    @Override
    protected void init(View view) {

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        ivGuang= (ImageView) view.findViewById(R.id.iv_guangchang);
        ivGuang.setOnClickListener(this);

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl_third);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        headView = new ThirdHeadView(getActivity(), getChildFragmentManager());
        headView.setUrl(Constant.URL.THIRD_ONE);

        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.third_bottom_layout, null);
        footerView.setVisibility(View.INVISIBLE);
        tvChange = (TextView) footerView.findViewById(R.id.tv_third_change);
        tvChange.setOnClickListener(this);

        mLv = (ListView) view.findViewById(R.id.lv_third);
        mLv.setVisibility(View.INVISIBLE);
        mLv.addHeaderView(headView);
        mLv.addFooterView(footerView);
        mLv.setOnItemClickListener(this);
        adapter = new ThirdAdapter(getActivity());
        mLv.setAdapter(adapter);

    }


    @Override
    protected void loadData() {
        VolleyUtil.requestString(Constant.URL.THIRD_ONE, this);
    }

    @Override
    public void onResponse(String url, String response) {
        ivRefresh.setVisibility(View.INVISIBLE);
        mLv.setVisibility(View.VISIBLE);
        footerView.setVisibility(View.VISIBLE);
        if (response != null) {
            ThirdHeadEntity.DataEntity thirdByJson = JSONUtil.getThirdByJson(response);
            rec_groups = thirdByJson.getRec_groups();
            datas = new ArrayList<>();
            getDatas();
            if (datas!=null){
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        swipeRefreshLayout.setRefreshing(false);
        mLv.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headView.setUrl(Constant.URL.THIRD_ONE);
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                VolleyUtil.requestString(Constant.URL.THIRD_ONE, TuanFragment.this);
            }
        });
    }

    private void getDatas() {
        datas.clear();
        if (p<rec_groups.size()){
            for (int i = p ; i < p + 5; i++) {
                ThirdHeadEntity.DataEntity.RecGroupsEntity recGroupsEntity = rec_groups.get(i);
                datas.add(recGroupsEntity);
            }
            p += 5;
        } else {
            p = 0;

            if (p <= rec_groups.size()) {
                for (int i = p; i < p + 5; i++) {
                    ThirdHeadEntity.DataEntity.RecGroupsEntity recGroupsEntity = rec_groups.get(i);
                    datas.add(recGroupsEntity);
                }
                p += 5;
            }
        }

        adapter.setDatas(datas);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_third_change:
                getDatas();
                break;
            case R.id.iv_guangchang:
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ThirdHeadEntity.DataEntity.RecGroupsEntity recGroupsEntity = datas.get(position-1);
        String mid = recGroupsEntity.getId();
        String name = recGroupsEntity.getName();
        Intent intent=new Intent(getActivity(), ThirdBottomDetailActivity.class);
        intent.putExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_ID,mid);
        intent.putExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_NAME,name);
        getActivity().startActivity(intent);
    }

}
