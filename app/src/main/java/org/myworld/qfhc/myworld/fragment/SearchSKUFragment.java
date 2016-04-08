package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailOneActivity;
import org.myworld.qfhc.myworld.adapter.SearchSKUGVAdapter;
import org.myworld.qfhc.myworld.adapter.SearchSKULVAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.SearchSKUEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 11:01
 * @备注：
 */
public class SearchSKUFragment extends BaseFragment implements VolleyUtil.OnRequestListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mLv;
    private GridView mGv;
    private SearchSKULVAdapter lvAdapter;
    private SearchSKUGVAdapter gvAdapter;
    private ImageView ivRefresh;
    private LinearLayout ll,llWangluo;

    private List<SearchSKUEntity.DataEntity> searchSKUByJson;
    private List<SearchSKUEntity.DataEntity.SubclassEntity> subclass;

    public static SearchSKUFragment newInstance() {

        Bundle args = new Bundle();

        SearchSKUFragment fragment = new SearchSKUFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_fragment_one;
    }

    @Override
    protected void init(View view) {

        ll= (LinearLayout) view.findViewById(R.id.ll);
        ll.setVisibility(View.INVISIBLE);

        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefresh.getBackground();
        bg.start();

        mLv = (ListView) view.findViewById(R.id.lv_search_one);
        mGv = (GridView) view.findViewById(R.id.gv_search_one);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), SearchDetailOneActivity.class);
                String name = subclass.get(position).getName();
                String mid = subclass.get(position).getId();
                intent.putExtra(Constant.KEYS.SEARCH_ONE_ID, mid);
                intent.putExtra(Constant.KEYS.SEARCH_ONE_NAME, name);
                startActivity(intent);
            }
        });

        lvAdapter = new SearchSKULVAdapter(getActivity());
        mLv.setOnItemClickListener(this);
        mLv.setAdapter(lvAdapter);

    }

    @Override
    protected void loadData() {

        VolleyUtil.requestString(Constant.URL.SEARCH_ONE, this);

    }

    @Override
    public void onResponse(String url, String response) {
        ll.setVisibility(View.VISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        if (response != null) {
            searchSKUByJson = JSONUtil.getSearchSKUByJson(response);
            if (searchSKUByJson != null) {
                lvAdapter.setDatas(searchSKUByJson);
                mLv.performItemClick(mLv.getAdapter().getView(0, null, null),
                        0, mLv.getItemIdAtPosition(0));
            }
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        ll.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                VolleyUtil.requestString(Constant.URL.SEARCH_ONE, SearchSKUFragment.this);
            }
        });
    }

    @Override
    public void onClick(View v) {
        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mLv.smoothScrollToPositionFromTop(position, 0);
        subclass = searchSKUByJson.get(position).getSubclass();
        gvAdapter = new SearchSKUGVAdapter(getActivity());
        gvAdapter.setDatas(subclass);
        mGv.setAdapter(gvAdapter);

    }
}
