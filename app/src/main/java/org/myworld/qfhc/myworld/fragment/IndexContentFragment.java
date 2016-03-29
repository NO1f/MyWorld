package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.IndexHeadActivity;
import org.myworld.qfhc.myworld.adapter.IndexContentAdapter;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.custom.MyItemDecoration;
import org.myworld.qfhc.myworld.entity.IndexDetailEntity;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 10:42
 * @备注：
 */
public class IndexContentFragment extends Fragment implements VolleyUtil.OnRequestListener, AbsAdapter.OnClickListener {

    private View view;
    private int id = 0;
    private int position;

    private RecyclerView recyclerView;
    private IndexContentAdapter adapter;
    private List<IndextContentEntity.DataEntity.PostListEntity.ListEntity> list;
    private int detail_id;

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

        recyclerView= (RecyclerView) view.findViewById(R.id.rv_first_content);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        adapter = new IndexContentAdapter(getActivity());
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
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

        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

        IndextContentEntity.DataEntity.PostListEntity.ListEntity listEntity = list.get(position);
        int detailId = listEntity.getId();
        String index_detail_url = String.format(Constant.URL.INDEX_DETAIL_JINGXUAN, detailId);
        VolleyUtil.requestString(index_detail_url, new VolleyUtil.OnRequestListener() {
            @Override
            public void onResponse(String url, String response) {
                if (response!=null){
                    IndexDetailEntity.DataEntity detailByJson = JSONUtil.getDetailByJson(response);

                    detail_id = detailByJson.getId();
                    /* String weiboShareText = detailByJson.getWeiboShareText();
                    String[] names = weiboShareText.split("，");
                    for (int i = 0; i < names.length; i++) {
                        L.e(names[i]+"_____________________");
                    }
                    String detail_url = names[1];*/


                }
            }

            @Override
            public void onErrorResponse(String url, VolleyError error) {

            }
        });

    }

    @Override
    public void onClick(View v, int position) {
        String detail_url=String.format(Constant.URL.IINDEX_DETAIL,detail_id);

        Intent intent = new Intent(getActivity(), IndexHeadActivity.class);
        intent.putExtra(Constant.KEYS.INDEX_DETAIL_URL, detail_url);
        startActivity(intent);

    }
}
