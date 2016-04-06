package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.SearchTieZiDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:41
 * @备注：
 */
public class SearchTieZiDetailActivity extends BaseActivity implements VolleyUtil.OnRequestListener {

    private int id;
    private LinearLayout ll;

    @Override
    protected int getContentResid() {
        return R.layout.search_tiezi_detail;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        String mid = intent.getStringExtra(Constant.KEYS.SEARCH_ONE_ID);
        id = Integer.valueOf(mid);

        ll = (LinearLayout) findViewById(R.id.ll_third);

    }

    @Override
    protected void initData() {

        String url = String.format(Constant.URL.SEARCH_TIEZI_DETAIL, id);
        VolleyUtil.requestString(url, this);

    }

    @Override
    public void onResponse(String url, String response) {
        if (response != null) {
            SearchTieZiDetailEntity.DataEntity.PostEntity searchTieziDetailByJson = JSONUtil.getSearchTieziDetailByJson(response);
            String datestr = searchTieziDetailByJson.getDatestr();
            String img_url = searchTieziDetailByJson.getPics().get(0).getUrl();
            String likes = searchTieziDetailByJson.getDynamic().getLikes();
            String content = searchTieziDetailByJson.getContent();
            String price = searchTieziDetailByJson.getProduct().get(0).getPrice();
            String title = searchTieziDetailByJson.getProduct().get(0).getTitle();
            String pic = searchTieziDetailByJson.getProduct().get(0).getPic();
            String url_taobao = searchTieziDetailByJson.getProduct().get(0).getUrl();
            List<SearchTieZiDetailEntity.DataEntity.PostEntity.TagsEntity> tags = searchTieziDetailByJson.getTags();

            ll.removeAllViews();
            for (int i = 0; i < tags.size(); i++) {
                String name = tags.get(i).getName();
                String id = tags.get(i).getId();

                TextView tv = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 10;
                tv.setLayoutParams(params);
                tv.setTextColor(Color.parseColor("#FD6363"));
                tv.setTextSize(12);
                tv.setText(name);
                ll.addView(tv);
            }
        }

    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }
}
