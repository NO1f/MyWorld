package org.myworld.qfhc.myworld.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.SearchRepertoireFragment;
import org.myworld.qfhc.myworld.fragment.SearchSKUFragment;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 01:26
 * @备注：
 */
public class SearchActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    private ImageView ivBack;
    private TextView tvSearch;
    private TabLayout mTl;
    private ViewPager mVp;
    private String[] title_icon;

    @Override
    protected int getContentResid() {
        return R.layout.search_layout;
    }

    @Override
    protected void init() {

        ivBack= (ImageView) findViewById(R.id.iv_search_back);
        ivBack.setOnClickListener(this);
        tvSearch= (TextView) findViewById(R.id.tv_search);
        mVp= (ViewPager) findViewById(R.id.vp_search);
        mTl= (TabLayout) findViewById(R.id.tl_search);

        title_icon = new String[]{"单品","清单"};

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);

        mTl.setOnTabSelectedListener(this);
        mTl.setupWithViewPager(mVp);
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

    @Override
    public void onClick(View v) {
        finish();
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return SearchSKUFragment.newInstance();
                case 1:
                    return SearchRepertoireFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title_icon[position];
        }
    }
}
