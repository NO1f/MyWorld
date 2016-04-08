package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailTwoActivity;
import org.myworld.qfhc.myworld.adapter.SearchRepertoireAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.SearchRepertoireEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 11:01
 * @备注：
 */
public class SearchRepertoireFragment extends BaseFragment implements VolleyUtil.OnRequestListener, AdapterView.OnItemClickListener {

    private GridView mGv;
    private SearchRepertoireAdapter adapter;
    private List<SearchRepertoireEntity.DataEntity> searchRepertoireByJson;
    private ImageView ivRefresh;
    private LinearLayout llWangluo;

    public static SearchRepertoireFragment newInstance() {

        Bundle args = new Bundle();

        SearchRepertoireFragment fragment = new SearchRepertoireFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_fragment_two;
    }

    @Override
    protected void init(View view) {

        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefresh.getBackground();
        bg.start();

        mGv= (GridView) view.findViewById(R.id.gv_search_two);
        mGv.setVisibility(View.INVISIBLE);
        adapter = new SearchRepertoireAdapter(getActivity());
        mGv.setAdapter(adapter);
        mGv.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        VolleyUtil.requestString(Constant.URL.SEARCH_TWO,this);
    }

    @Override
    public void onResponse(String url, String response) {
        mGv.setVisibility(View.VISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        if (response!=null){
            searchRepertoireByJson = JSONUtil.getSearchRepertoireByJson(response);
            adapter.setDatas(searchRepertoireByJson);
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        mGv.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                VolleyUtil.requestString(Constant.URL.SEARCH_TWO,SearchRepertoireFragment.this);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = searchRepertoireByJson.get(position).getName();
        int mid = searchRepertoireByJson.get(position).getId();
        Intent intent=new Intent(getActivity(), SearchDetailTwoActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_TWO_ID,mid);
        intent.putExtra(Constant.KEYS.SEARCH_TWO_NAME,name);
        startActivity(intent);

    }
}
