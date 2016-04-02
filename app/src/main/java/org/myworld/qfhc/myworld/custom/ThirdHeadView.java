package org.myworld.qfhc.myworld.custom;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;
import org.myworld.qfhc.myworld.fragment.ThirdHeadFragment;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 19:29
 * @备注：
 */
public class ThirdHeadView extends LinearLayout implements VolleyUtil.OnRequestListener {

    private FragmentManager fragmentManager;
    private ViewPager mVp;
    private TabLayout mTl;
    private List<ThirdHeadEntity.DataEntity.ModuleElementsEntity> module_elements;

    public ThirdHeadView(Context context, FragmentManager fragmentManager) {
        super(context);
        this.fragmentManager=fragmentManager;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.third_head_layout,this,true);
        mVp= (ViewPager) findViewById(R.id.vp_third_one_top);
        mTl= (TabLayout) findViewById(R.id.tl_tab_third);
    }

    public void setUrl(String url){
        VolleyUtil.requestString(url,this);
    }

    @Override
    public void onResponse(String url, String response) {
        if(response!=null){
            ThirdHeadEntity.DataEntity thirdByJson = JSONUtil.getThirdByJson(response);
            module_elements = thirdByJson.getModule_elements();
            L.e(module_elements+"");
            ViewPagerAdapter adapter=new ViewPagerAdapter(fragmentManager,module_elements);
            mVp.setAdapter(adapter);

            mTl.setTabsFromPagerAdapter(adapter);
            mTl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
            });

            mTl.setupWithViewPager(mVp);


        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        List<ThirdHeadEntity.DataEntity.ModuleElementsEntity> datas;

        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position).getTitle();
        }

        public ViewPagerAdapter(FragmentManager fm, List<ThirdHeadEntity.DataEntity.ModuleElementsEntity> datas) {
            super(fm);
            this.datas = datas;
        }

        @Override
        public Fragment getItem(int position) {
            return ThirdHeadFragment.newInstance(module_elements.get(position));
        }

        @Override
        public int getCount() {
            return datas.size();
        }
    }
}
