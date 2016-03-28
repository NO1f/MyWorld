package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseFragment;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class TuanFragment extends BaseFragment {

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
}
