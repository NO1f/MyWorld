package org.myworld.qfhc.myworld.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.IndexEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/28 15:19
 * @备注：
 */
public class IndexFragment extends BaseFragment implements VolleyUtil.OnRequestListener {

    private TabLayout mTl;
    private ConvenientBanner convenientBanner;
    private List<String> sdvUrls;
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

    @Override
    protected void init(View view) {
        mTl = (TabLayout) view.findViewById(R.id.tl_tab_first);
        mTl.addTab(mTl.newTab().setText("精选"));
        mTl.addTab(mTl.newTab().setText("评论"));
        mTl.addTab(mTl.newTab().setText("评论"));
        mTl.addTab(mTl.newTab().setText("评论"));
        mTl.addTab(mTl.newTab().setText("评论"));
        mTl.addTab(mTl.newTab().setText("评论"));
        mTl.addTab(mTl.newTab().setText("评论"));
        convenientBanner= (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        sdvUrls=new ArrayList<>();

    }

    @Override
    protected void loadData() {

        VolleyUtil.requestString(Constant.URL.INDEX,this);

    }

    @Override
    public void onResponse(String url, String response) {
        if (response!=null){
            IndexEntity.ResultEntity resultEntity = JSONUtil.getContentByJSON(response);
            List<IndexEntity.ResultEntity.SlideBannersEntity> slideBanners = resultEntity.getSlideBanners();
            bindConvenientBanner(slideBanners);
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }


    private void bindConvenientBanner(List<IndexEntity.ResultEntity.SlideBannersEntity> slideBanners) {

        for(int i=0;i<slideBanners.size();i++){
            sdvUrls.add(slideBanners.get(i).getImageSrc());
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
//        convenientBanner.setManualPageable(false);//设置不能手动影响
        convenientBanner.startTurning(2000);


    }

    public class LocalImageHolderView implements Holder<String> {

        private SimpleDraweeView simpleDraweeView;

        @Override
        public View createView(Context context) {
            simpleDraweeView = new SimpleDraweeView(getActivity());

            return simpleDraweeView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String imgUrl) {
            simpleDraweeView.setImageURI(Uri.parse(imgUrl));
        }
    }


}
