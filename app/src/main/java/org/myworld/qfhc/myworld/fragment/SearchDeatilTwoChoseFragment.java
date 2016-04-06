package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.SearchChoseAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.MyItemDecoration;
import org.myworld.qfhc.myworld.entity.SearchDeatilTwoChoseEntity;
import org.myworld.qfhc.myworld.util.Constant;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 13:34
 * @备注：
 */
public class SearchDeatilTwoChoseFragment extends BaseFragment {

    private RecyclerView recyclerView;

    public static SearchDeatilTwoChoseFragment newInstance(Bundle bundle) {
        SearchDeatilTwoChoseFragment fragment = new SearchDeatilTwoChoseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.search_two_detail_chose;
    }

    @Override
    protected void init(View view) {

        recyclerView= (RecyclerView) view.findViewById(R.id.rv_search);

        //linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        //recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new MyItemDecoration(getActivity()));
        SearchChoseAdapter adapter=new SearchChoseAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();

        SearchDeatilTwoChoseEntity.DataEntity chose = (SearchDeatilTwoChoseEntity.DataEntity) bundle.getSerializable(Constant.KEYS.BUNDLE);
        if (chose != null) {
            List<SearchDeatilTwoChoseEntity.DataEntity.ProductEntity> product = chose.getProduct();
            adapter.setDatas(product);
        }

    }

}
