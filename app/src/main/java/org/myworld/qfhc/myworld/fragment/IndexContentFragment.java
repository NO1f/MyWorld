package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.IndexHeadActivity;
import org.myworld.qfhc.myworld.activity.IndextDetailActivity;
import org.myworld.qfhc.myworld.adapter.IndexContentAdapter;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.custom.MyItemDecoration;
import org.myworld.qfhc.myworld.custom.OnRcvScrollListener;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 10:42
 * @备注：
 */
public class IndexContentFragment extends Fragment implements VolleyUtil.OnRequestListener, AbsAdapter.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private View view;
    private int id = 0;
    private int position;
    private List<IndextContentEntity.DataEntity.PostListEntity.ListEntity> allDatas = new ArrayList<>();

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private IndexContentAdapter adapter;
    private List<IndextContentEntity.DataEntity.PostListEntity.ListEntity> datas;
    private LinearLayoutManager linearLayoutManager;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_first_content);
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        adapter = new IndexContentAdapter(getActivity());
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnScrollListener(new OnRcvScrollListener() {
            @Override
            public void onBottom() {
                super.onBottom();
                initData();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_first);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        String index_content_url = null;
        switch (position) {
            case 0:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_JINGXUAN, id);
                break;
            case 1:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_LUYOUSHUO, id);
                break;
            case 2:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_FUSHI, id);
                break;
            case 3:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_MEIZHUANG, id);
                break;
            case 4:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_JIAJU, id);
                break;
            case 5:
                index_content_url = String.format(Constant.URL.INDEX_CONTENT_MEISHI, id);
                break;
        }
        VolleyUtil.requestString(index_content_url, this);

    }

    @Override
    public void onResponse(String url, String response) {

        if (response != null) {
            IndextContentEntity.DataEntity.PostListEntity postListEntity = JSONUtil.getContentByJson(response);
            id = postListEntity.getEndId();
            L.e((postListEntity == null) + response + "________________________________________________");
            datas = postListEntity.getList();
            allDatas.addAll(datas);
            adapter.setDatas(allDatas);

            swipeRefreshLayout.setRefreshing(false);

        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        L.e(url);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v, int position) {

        IndextContentEntity.DataEntity.PostListEntity.ListEntity listEntity = datas.get(position);
        int detailId = listEntity.getId();
        String introduction = null;

        String index_detail_url = String.format(Constant.URL.INDEX_DETAIL, detailId);
        if (index_detail_url != null) {
            Intent intent = new Intent(getActivity(), IndextDetailActivity.class);
            intent.putExtra(Constant.KEYS.INDEX_DETAIL_URL, index_detail_url);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.KEYS.LISTENTITY, listEntity);
            intent.putExtra(Constant.KEYS.LISTENTITYS, bundle);
            if (listEntity.getAlbum() != null) {
                introduction = listEntity.getAlbum().getIntroduction();
                intent.putExtra(Constant.KEYS.INTRODUCTION, introduction);
            }
            startActivity(intent);
        } else {
            Toast.makeText(getActivity(), "数据加载失败", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onRefresh() {
        id = 0;
        initData();
    }


}