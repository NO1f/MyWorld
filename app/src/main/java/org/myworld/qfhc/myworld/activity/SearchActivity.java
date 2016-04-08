package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.fragment.SearchRepertoireFragment;
import org.myworld.qfhc.myworld.fragment.SearchSKUFragment;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 01:26
 * @备注：
 */
public class SearchActivity extends BaseActivity implements TabLayout.OnTabSelectedListener, VolleyUtil.OnRequestListener {

    private EditText etSearch;
    private TabLayout mTl;
    private ViewPager mVp;
    private String[] title_icon;
    private View contentView;
    private LinearLayout ll;
    private Intent intent;
    private ImageView dismiss;
    private PopupWindow mWindow;

    @Override
    protected int getContentResid() {
        return R.layout.search_layout;
    }

    @Override
    protected void init() {
        intent = new Intent(this, SearchMainActivity.class);
        etSearch = (EditText) findViewById(R.id.et_search);
        //etSearch.setFocusable(true);
        mVp = (ViewPager) findViewById(R.id.vp_search);
        mTl = (TabLayout) findViewById(R.id.tl_search);
        contentView = LayoutInflater.from(this).inflate(R.layout.popu, null);
        ll = (LinearLayout) contentView.findViewById(R.id.ll_popu);
        dismiss = (ImageView) contentView.findViewById(R.id.popu_dismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.setAnimationStyle(R.style.popwin_anim_style);
                mWindow.dismiss();
            }
        });

        title_icon = new String[]{"单品", "清单"};

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVp.setAdapter(adapter);

        mTl.setOnTabSelectedListener(this);
        mTl.setupWithViewPager(mVp);
    }

    @Override
    protected void initData() {
        VolleyUtil.requestString(Constant.URL.SEARCH_POPU, this);
    }

    @Override
    public void onResponse(String url, String response) {
        if (response != null) {
            List<String> data = JSONUtil.getSearchPopuByJson(response);
            LinearLayout popu = null;

            for (int i = 0; i < data.size(); i++) {
                if (i % 4 == 0) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.topMargin = 15;
                    popu = new LinearLayout(this);
                    popu.setLayoutParams(params);

                    // L.e((popu==null)+"---------------------------");
                    ll.addView(popu);
                }
                String s = data.get(i);
                final TextView tv = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 20;
                tv.setLayoutParams(params);
                tv.setTextColor(Color.parseColor("#B7B7B7"));
                tv.setTextSize(14);
                tv.setText(s);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(16, 5, 16, 5);
                tv.setBackgroundResource(R.drawable.popu);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String resou = tv.getText().toString();
                        intent.putExtra(Constant.KEYS.KEYWORD, resou);
                        startActivity(intent);

                    }
                });

                if (popu != null) {
                    // L.e((popu == null) + "_________________________________________");
                    popu.addView(tv);
                }

            }
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

    public void search(View view) {

        switch (view.getId()) {
            case R.id.et_search:

                if (mWindow!=null&&mWindow.isShowing()){
                    mWindow.dismiss();
                }else {

                    int widthPixels = getResources().getDisplayMetrics().widthPixels;
                    int heigh = getResources().getDisplayMetrics().heightPixels / 7*3;
                    mWindow = new PopupWindow(contentView, widthPixels, heigh);
                    mWindow.setOutsideTouchable(true);

                    // 设置PopupWindow获得焦点的能力
                    // mWindow.setFocusable(true);

                    // 设置背景图片
                    Drawable background = getResources().getDrawable(
                            R.drawable.bg_popu);
                    mWindow.setBackgroundDrawable(background);

                    mWindow.setAnimationStyle(R.style.popwin_anim_style);
                    // 让PopupWindow显示出来
                    // mWindow.showAtLocation(parent, gravity, x, y);
                    mWindow.showAsDropDown(etSearch, 0, 2);
                }

                break;
            case R.id.iv_search:
                String text = etSearch.getText().toString().trim();
                intent.putExtra(Constant.KEYS.KEYWORD, text);
                startActivity(intent);
                break;

            case R.id.iv_search_back:
                finish();
                break;
            case R.id.iv_delete:
                etSearch.setText("");
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
                    return SearchSKUFragment.newInstance();
                case 1:
                    return SearchRepertoireFragment.newInstance();
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
}
