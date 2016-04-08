package org.myworld.qfhc.myworld.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.IndexHeadEntity;
import org.myworld.qfhc.myworld.entity.SearchDetailOneDetEntity;
import org.myworld.qfhc.myworld.fragment.IndexFragment;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 17:20
 * @备注：
 */
public class SearchDetailOneDetActivity extends BaseActivity implements VolleyUtil.OnRequestListener {

    private ConvenientBanner convenientBanner;
    private TextView tvTitle, tvPrice, tvDesc;
    private int id;
    private List<String> sdvUrls;
    private SearchDetailOneDetEntity.DataEntity.ProductEntity searchDetailOneDetByJson;
    private ScrollView scrollView;

    private ImageView ivRefresh;
    private LinearLayout llWangluo;
    private String formatUrl;

    @Override
    protected int getContentResid() {
        return R.layout.search_detail_one_det;
    }

    @Override
    protected void init() {

        ivRefresh= (ImageView)findViewById(R.id.iv_third_bottom_refresh);
        AnimationDrawable background = (AnimationDrawable) ivRefresh.getBackground();
        background.start();
        llWangluo = (LinearLayout) findViewById(R.id.ll_wangluo);
        llWangluo.setVisibility(View.INVISIBLE);

        scrollView= (ScrollView) findViewById(R.id.sv);
        scrollView.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.SEARCH_ONE_DETAIL_ID);
        id = Integer.valueOf(mid);

        tvTitle = (TextView) findViewById(R.id.tv_search_detail_one_det_title);
        tvPrice = (TextView) findViewById(R.id.tv_search_detail_one_det_price);
        tvDesc = (TextView) findViewById(R.id.tv_search_detail_one_det_desc);

        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner_search);
        sdvUrls = new ArrayList<>();
    }

    @Override
    protected void initData() {

        formatUrl = String.format(Constant.URL.SEARCH_DETAIL_ONE_DET, id);
        VolleyUtil.requestString(formatUrl, this);
    }

    @Override
    public void onResponse(String url, String response) {
        ivRefresh.setVisibility(View.INVISIBLE);
        scrollView.setVisibility(View.VISIBLE);

        if (response != null) {
            searchDetailOneDetByJson = JSONUtil.getSearchDetailOneDetByJson(response);
            String title = searchDetailOneDetByJson.getTitle();
            String price = searchDetailOneDetByJson.getPrice();
            String desc = searchDetailOneDetByJson.getDesc();

            tvTitle.setText(title);
            tvPrice.setText("￥" + price);
            tvDesc.setText(desc);

            List<SearchDetailOneDetEntity.DataEntity.ProductEntity.PicEntity> pic = searchDetailOneDetByJson.getPic();
            bindConvenientBanner(pic);
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

        scrollView.setVisibility(View.INVISIBLE);
        ivRefresh.setVisibility(View.INVISIBLE);
        llWangluo.setVisibility(View.VISIBLE);
        llWangluo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llWangluo.setVisibility(View.INVISIBLE);
                ivRefresh.setVisibility(View.VISIBLE);
                VolleyUtil.requestString(formatUrl, SearchDetailOneDetActivity.this);
            }
        });

    }

    private void bindConvenientBanner(List<SearchDetailOneDetEntity.DataEntity.ProductEntity.PicEntity> pic) {
        for (int i = 0; i < pic.size(); i++) {
            sdvUrls.add(pic.get(i).getPic());
        }
        if (sdvUrls.size() > 1) {
            convenientBanner.setPages(
                    new CBViewHolderCreator<SearchDetailOneDetActivity.LocalImageHolderView>() {
                        @Override
                        public SearchDetailOneDetActivity.LocalImageHolderView createHolder() {
                            return new SearchDetailOneDetActivity.LocalImageHolderView();
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
        } else {
            convenientBanner.setPages(
                    new CBViewHolderCreator<SearchDetailOneDetActivity.LocalImageHolderView>() {
                        @Override
                        public SearchDetailOneDetActivity.LocalImageHolderView createHolder() {
                            return new SearchDetailOneDetActivity.LocalImageHolderView();
                        }
                    }, sdvUrls);
        }

    }

    public class LocalImageHolderView implements Holder<String> {

        private SimpleDraweeView simpleDraweeView;

        @Override
        public View createView(Context context) {
            simpleDraweeView = new SimpleDraweeView(SearchDetailOneDetActivity.this);
            GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.shadow_bottom);// 修改占位图
            return simpleDraweeView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String imgUrl) {
            simpleDraweeView.setImageURI(Uri.parse(imgUrl));
        }
    }


    public void wupinxiangqing(View v) {
        String urlTaobao = searchDetailOneDetByJson.getUrl();
        String share_url = searchDetailOneDetByJson.getShare_url();
        L.e(urlTaobao+"___________________________________________");
        switch (v.getId()) {
            case R.id.tv_search_detail_one_back:
                finish();
                break;
            case R.id.tv_search_detail_one_det_share:
                Toast.makeText(SearchDetailOneDetActivity.this, "此处要分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_search_detail_one_det_coll:
                Toast.makeText(SearchDetailOneDetActivity.this, "此处要收藏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_search_detail_one_det_buy:
                Intent intent = new Intent(SearchDetailOneDetActivity.this, IndexHeadActivity.class);
                intent.putExtra(Constant.KEYS.INDEX_HEAD_CONTENT, urlTaobao);
                startActivity(intent);
                break;
        }
    }
}
