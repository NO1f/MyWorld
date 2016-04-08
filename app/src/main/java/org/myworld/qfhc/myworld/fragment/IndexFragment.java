package org.myworld.qfhc.myworld.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.IndexHeadActivity;
import org.myworld.qfhc.myworld.activity.MainActivity;
import org.myworld.qfhc.myworld.activity.SearchActivity;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.IndexHeadEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class IndexFragment extends BaseFragment implements VolleyUtil.OnRequestListener, OnItemClickListener, TabLayout.OnTabSelectedListener, View.OnClickListener {

    private TabLayout mTl;
    private ViewPager mVp;
    private ImageView ivSearch;

    private ConvenientBanner convenientBanner;
    private List<String> sdvUrls;
    private List<IndexHeadEntity.DataEntity.BannerEntity> banner;
    private List<IndexHeadEntity.DataEntity.TabsEntity> tabs;
    private ViewPagerAdapter adapter;

    private ImageView ivRefresh;
    private LinearLayout llWangluo;

    public static IndexFragment newInstance() {

        Bundle args = new Bundle();

        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.index_fragment;
    }

    /************初始化数据**************/
    @Override
    protected void init(View view) {

        llWangluo = (LinearLayout) view.findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        ivRefresh= (ImageView)view.findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable bg = (AnimationDrawable) ivRefresh.getBackground();
        bg.start();

        convenientBanner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        sdvUrls = new ArrayList<>();

        ivSearch= (ImageView) view.findViewById(R.id.iv_first_search);
        ivSearch.setOnClickListener(this);
        mTl = (TabLayout) view.findViewById(R.id.tl_tab_first);
        mVp = (ViewPager) view.findViewById(R.id.vp_first_tab);
        mTl.setOnTabSelectedListener(this);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());


        convenientBanner.setVisibility(View.INVISIBLE);
        mTl.setVisibility(View.INVISIBLE);
        mVp.setVisibility(View.INVISIBLE);

    }

    /*********************************下载数据******************************/
    @Override
    protected void loadData() {
        VolleyUtil.requestString(Constant.URL.INDEX_HEAD, this);
    }

    /*********************************解析数据******************************/
    @Override
    public void onResponse(String url, String response) {
        convenientBanner.setVisibility(View.VISIBLE);
        mTl.setVisibility(View.VISIBLE);
        mVp.setVisibility(View.VISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        if (response != null) {
            IndexHeadEntity.DataEntity contentByJSON = JSONUtil.getHeadByJSON(response);
            tabs = contentByJSON.getTabs();
            L.e(tabs+"");
            //titles = new String[]{tabs.get(0).getName(), tabs.get(2).getName(), tabs.get(4).getName(), tabs.get(5).getName()};
            //net data
            mVp.setAdapter(adapter);
            mTl.setTabsFromPagerAdapter(adapter);
            mTl.setupWithViewPager(mVp);

            L.e("" + contentByJSON);
            banner = contentByJSON.getBanner();
            bindConvenientBanner(banner);
        }
        convenientBanner.setOnItemClickListener(this);
    }

    @Override
    public void onErrorResponse(String url1, VolleyError error) {

        convenientBanner.setVisibility(View.INVISIBLE);
        mTl.setVisibility(View.INVISIBLE);
        mVp.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                VolleyUtil.requestString(Constant.URL.INDEX_HEAD, IndexFragment.this);
            }
        });

    }

    /*************************头部的循滑与点击****************************/
    private void bindConvenientBanner(List<IndexHeadEntity.DataEntity.BannerEntity> banner) {
        for (int i = 0; i < banner.size(); i++) {
            sdvUrls.add(banner.get(i).getImageUrl());
        }

        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, sdvUrls)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.ind_tuan_banner_inactive, R.drawable.ind_tuan_banner_active})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
        //convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.startTurning(2000);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), SearchActivity.class);
        getActivity().startActivity(intent);
    }

    public class LocalImageHolderView implements Holder<String> {

        private SimpleDraweeView simpleDraweeView;

        @Override
        public View createView(Context context) {
            simpleDraweeView = new SimpleDraweeView(getActivity());
            GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.shadow_bottom);// 修改占位图
            return simpleDraweeView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String imgUrl) {
            simpleDraweeView.setImageURI(Uri.parse(imgUrl));
        }
    }

    @Override
    public void onItemClick(int position) {

        String url = banner.get(position).getUrl();
        //L.e(url+"=====================================");
        Intent intent = new Intent(getActivity(), IndexHeadActivity.class);
        intent.putExtra(Constant.KEYS.INDEX_HEAD_CONTENT, url);
        startActivity(intent);
    }



    /**********************ViewPager与TabLayout的关连************************/
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

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return IndexContentFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }

}
