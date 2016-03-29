package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.IndexContentAdapter;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 10:42
 * @备注：
 */
public class IndexContentFragment extends ListFragment implements VolleyUtil.OnRequestListener {

    private View view;
    private int id = 0;
    private int position;

    private ListView mLv;
    private IndexContentAdapter adapter;
    private List<IndextContentEntity.DataEntity.PostListEntity.ListEntity> list;

    public static IndexContentFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(Constant.KEYS.INDEX_CONTENT_POSITION, position);
        IndexContentFragment fragment = new IndexContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.indexcontent_fragment, null);
        init(view);
        initData();
        return view;
    }

    private void init(View view) {
        Bundle bundle = getArguments();
        position = (int) bundle.get(Constant.KEYS.INDEX_CONTENT_POSITION);

        mLv= (ListView) view.findViewById(android.R.id.list);
        adapter = new IndexContentAdapter(getActivity());
        mLv.setAdapter(adapter);
    }

    private void initData() {
        String index_content_url = null;
        switch (position) {
            case 0:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_JINGXUAN, id);
                break;
            case 1:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_FUSHI, id);
                break;
            case 2:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_MEIZHUANG, id);
                break;
            case 3:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_JIAJU, id);
                break;
            case 4:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_MEISHI, id);
                break;
        }
        VolleyUtil.requestString(index_content_url, this);

    }

    @Override
    public void onResponse(String url, String response) {

        if (response!=null){
            IndextContentEntity.DataEntity.PostListEntity postListEntity = JSONUtil.getContentByJson(response);
            id = postListEntity.getEndId();
            list = postListEntity.getList();
            adapter.setDatas(list);

            //setListAdapter(adapter);

        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }
}
