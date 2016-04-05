package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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

    private ImageView ivWait;
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

        ivWait = (ImageView) view.findViewById(R.id.iv_search_wait);
        AnimationDrawable background = (AnimationDrawable) ivWait.getBackground();
        background.start();
        ivWait.setVisibility(View.GONE);
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
        if (response != null) {
            searchSKUByJson = JSONUtil.getSearchSKUByJson(response);
            if (searchSKUByJson != null) {
                lvAdapter.setDatas(searchSKUByJson);
                mLv.performItemClick(mLv.getAdapter().getView(0, null, null),
                        0, mLv.getItemIdAtPosition(0));
            }

        } /*else {
            mGv.setVisibility(View.GONE);
            mLv.setVisibility(View.GONE);
            ivWait.setVisibility(View.VISIBLE);
        }*/
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        ivWait.setVisibility(View.GONE);
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.ic_net_error);
        iv.setOnClickListener(this);
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
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
