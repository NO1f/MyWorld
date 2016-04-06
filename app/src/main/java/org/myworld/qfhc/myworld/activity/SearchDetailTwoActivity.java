package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.SearchDetailTwoFragment;
import org.myworld.qfhc.myworld.util.Constant;
/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 09:01
 * @备注：
 */
public class SearchDetailTwoActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private TextView tvTitle;
    private TabLayout mTl;
    private ViewPager mVp;
    private String[] titles;
    int id;

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_two;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String name = intent.getStringExtra(Constant.KEYS.SEARCH_TWO_NAME);
        id = intent.getIntExtra(Constant.KEYS.SEARCH_TWO_ID, -1);
        tvTitle= (TextView) findViewById(R.id.tv_search_detail_two_title);
        tvTitle.setText(name);

        mVp= (ViewPager) findViewById(R.id.vp_search_detail_two);
        mTl= (TabLayout) findViewById(R.id.tl_search_two_detail);
        titles = new String[]{"最新","最热"};

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);

        mTl.setupWithViewPager(mVp);
        mTl.setOnTabSelectedListener(this);
        mTl.setTabsFromPagerAdapter(adapter);
    }

    public void back(View v){
        finish();
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
            Bundle bundle=new Bundle();
            bundle.putInt(Constant.KEYS.SEARCH_TWO_ID,id);
            bundle.putInt(Constant.KEYS.INDEX_CONTENT_POSITION,position);

            return SearchDetailTwoFragment.newInstance(bundle);
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
}
