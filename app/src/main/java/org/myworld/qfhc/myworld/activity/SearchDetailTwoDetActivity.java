package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.SearchDeatilTwoChoseEntity;
import org.myworld.qfhc.myworld.fragment.SearchDeatilTwoChoseFragment;
import org.myworld.qfhc.myworld.fragment.SearchDetailTwoRecommendFragmennt;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 11:44
 * @备注：
 */
public class SearchDetailTwoDetActivity extends BaseActivity implements VolleyUtil.OnRequestListener, TabLayout.OnTabSelectedListener {

    private SimpleDraweeView simpleDraweeView;
    private TextView tvTitle, tvDesc;
    private TabLayout mTl;
    private ViewPager mVp;
    private int id;
    private String[] titles;
    private ViewPagerAdapter adapter;
    private SearchDeatilTwoChoseEntity.DataEntity searchDetailChoseByJson;

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_two_det;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.SEARCH_TWO_DET_ID);
        id = Integer.valueOf(mid);

        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.sdv_search_detail_head);
        tvTitle = (TextView) findViewById(R.id.tv_search_detail_two_det_title);
        tvDesc = (TextView) findViewById(R.id.tv_search_detail_two_det_desc);
        mTl = (TabLayout) findViewById(R.id.tl_tab_detail);
        titles = new String[]{"精选","推荐"};


        mVp= (ViewPager) findViewById(R.id.vp_search_tab);

    }

    @Override
    protected void initData() {
        String url = String.format(Constant.URL.SEARCH_DETAIL_TWO_DETAIL, id);
        VolleyUtil.requestString(url,this);

    }

    @Override
    public void onResponse(String url, String response) {

        if (response!=null){
            searchDetailChoseByJson = JSONUtil.getSearchDetailChoseByJson(response);
            String desc = searchDetailChoseByJson.getDesc();
            String title = searchDetailChoseByJson.getTitle();
            String pic = searchDetailChoseByJson.getPic();

            tvTitle.setText(title);
            tvDesc.setText(desc);
            simpleDraweeView.setImageURI(Uri.parse(pic));

            adapter = new ViewPagerAdapter(getSupportFragmentManager());
            mVp.setAdapter(adapter);

            mTl.setTabsFromPagerAdapter(adapter);
            mTl.setOnTabSelectedListener(this);
            mTl.setupWithViewPager(mVp);
        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Bundle bundle=new Bundle();
                    bundle.putSerializable(Constant.KEYS.BUNDLE,searchDetailChoseByJson);
                    L.e(searchDetailChoseByJson+"____________________________________________-");
                    return SearchDeatilTwoChoseFragment.newInstance(bundle);
                case 1:
                    return SearchDetailTwoRecommendFragmennt.newInstance(id);
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mVp.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public void gouwuqingdan(View v) {
        switch (v.getId()) {
            case R.id.tv_search_detail_two_back:
                finish();
                break;

            case R.id.iv_third_detail_two_share:
                String share_url = searchDetailChoseByJson.getShare_url();
                break;
        }
    }


}
