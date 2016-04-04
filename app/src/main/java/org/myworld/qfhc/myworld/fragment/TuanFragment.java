package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.ThirdBottomActivity;
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
    private SwipeRefreshLayout swipeRefreshLayout;

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

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.srl_third);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);

        ThirdHeadView headView = new ThirdHeadView(getActivity(), getChildFragmentManager());
        headView.setUrl(Constant.URL.THIRD_ONE);

        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.third_bottom_layout, null);
        tvChange = (TextView) footerView.findViewById(R.id.tv_third_change);
        tvChange.setOnClickListener(this);

        mLv = (ListView) view.findViewById(R.id.lv_third);
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
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
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
        getDatas();
    }

    @Override
    public void onRefresh() {
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ThirdHeadEntity.DataEntity.RecGroupsEntity recGroupsEntity = datas.get(position);
        String mid = recGroupsEntity.getId();
        String name = recGroupsEntity.getName();
        Intent intent=new Intent(getActivity(), ThirdBottomActivity.class);
        intent.putExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_ID,mid);
        intent.putExtra(Constant.KEYS.THIRD_BOTTOM_DETAIL_NAME,name);
        getActivity().startActivity(intent);
    }
}
