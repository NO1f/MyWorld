package org.myworld.qfhc.myworld.fragment;

import android.os.Bundle;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseFragment;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:08
 * @备注：
 */
public class SearchMainDanPinFragment extends BaseFragment{

    public static SearchMainDanPinFragment newInstance(String keyword) {

        Bundle args = new Bundle();

        SearchMainDanPinFragment fragment = new SearchMainDanPinFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getContentResid() {
        return R.layout.search_detail_one;
    }
}