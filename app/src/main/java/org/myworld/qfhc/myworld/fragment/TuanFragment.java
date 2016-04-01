package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.ThirdAdapter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.custom.ThirdHeadView;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class TuanFragment extends BaseFragment {

    private ListView mLv;

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

        ThirdHeadView headView= new ThirdHeadView(getActivity(),getChildFragmentManager());

        mLv= (ListView) view.findViewById(R.id.lv_third);
        ThirdAdapter adapter=new ThirdAdapter(getActivity());
        mLv.addHeaderView(headView);
        mLv.setAdapter(adapter);

    }
}
