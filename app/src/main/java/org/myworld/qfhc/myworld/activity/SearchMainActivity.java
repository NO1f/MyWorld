package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.SearchMainDanPinFragment;
import org.myworld.qfhc.myworld.fragment.SearchMainQingDanFragment;
import org.myworld.qfhc.myworld.fragment.SearchMainTieZiFragment;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.L;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 00:14
 * @备注：
 */
public class SearchMainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    private ImageView ivBack;
    private EditText etSearch;
    private TabLayout mTl;
    private ViewPager mVp;
    private String[] title_icon;
    private String keyword;
    private ViewPagerAdapter adapter;

    @Override
    protected int getContentResid() {
        return R.layout.search_layout;
    }

    @Override
    protected void init() {
        try {
            Intent intent = getIntent();
            keyword = intent.getStringExtra(Constant.KEYS.KEYWORD);
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        etSearch = (EditText) findViewById(R.id.et_search);
        mVp = (ViewPager) findViewById(R.id.vp_search);
        mTl = (TabLayout) findViewById(R.id.tl_search);

        title_icon = new String[]{"单品", "清单", "帖子"};

        adapter = new ViewPagerAdapter(getSupportFragmentManager());
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


    public void search(View v) {

        switch (v.getId()){

            case R.id.iv_search:

                String text = etSearch.getText().toString().trim();
                try {
                    keyword = URLEncoder.encode(text, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                mVp.removeAllViews();
                mVp.setAdapter(adapter);

                mTl.setOnTabSelectedListener(this);
                mTl.setupWithViewPager(mVp);

                break;

            case R.id.iv_search_back:
                finish();
                break;

        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    L.e(keyword + "++++++++++++++++++++++++++++++++++++");
                    return SearchMainDanPinFragment.newInstance(keyword);
                case 1:
                    return SearchMainQingDanFragment.newInstance(keyword);
                case 2:
                    return SearchMainTieZiFragment.newInstance(keyword);
            }
            return null;
        }

        @Override
        public int getCount() {
            return title_icon.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title_icon[position];
        }
    }
}
