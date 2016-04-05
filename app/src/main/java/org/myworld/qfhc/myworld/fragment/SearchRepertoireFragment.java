package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
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
public class SearchRepertoireFragment extends BaseFragment implements VolleyUtil.OnRequestListener {

    private GridView mGv;
    private SearchRepertoireAdapter adapter;

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
        mGv= (GridView) view.findViewById(R.id.gv_search_two);
        adapter = new SearchRepertoireAdapter(getActivity());
        mGv.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

        VolleyUtil.requestString(Constant.URL.SEARCH_TWO,this);

    }

    @Override
    public void onResponse(String url, String response) {
        if (response!=null){
            List<SearchRepertoireEntity.DataEntity> searchRepertoireByJson = JSONUtil.getSearchRepertoireByJson(response);
            adapter.setDatas(searchRepertoireByJson);
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }
}
