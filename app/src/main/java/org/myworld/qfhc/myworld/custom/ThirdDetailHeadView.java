package org.myworld.qfhc.myworld.custom;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.entity.ThirdBottomDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.L;
import org.myworld.qfhc.myworld.util.VolleyUtil;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/4 16:57
 * @备注：
 */
public class ThirdDetailHeadView extends RelativeLayout implements VolleyUtil.OnRequestListener {

    private TextView tvName,tvDesc,tvViews,tvPosts;
    private SimpleDraweeView simpleDraweeView;

    public ThirdDetailHeadView(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.third_bottom_detail_head,this,true);
        tvName= (TextView) findViewById(R.id.tv_third_bottom_detail_name);
        tvPosts= (TextView) findViewById(R.id.tv_third_bottom_detail_posts);
        tvViews= (TextView) findViewById(R.id.tv_third_bottom_detail_views);
        tvDesc= (TextView) findViewById(R.id.ctv_third_bottom_detail_desc);
        simpleDraweeView= (SimpleDraweeView) findViewById(R.id.sdv_third_bottom_detail_head);
    }

    public void setUrl(String url){
        VolleyUtil.requestString(url,this);
    }

    @Override
    public void onResponse(String url, String response) {
        if(response!=null){

            ThirdBottomDetailEntity.DataEntity thirdBottomDetailByJson = JSONUtil.getThirdBottomDetailByJson(response);
            String name = thirdBottomDetailByJson.getInfo().getName();
            String desc = thirdBottomDetailByJson.getInfo().getDesc();
            String pic2 = thirdBottomDetailByJson.getInfo().getPic2();
            int views = thirdBottomDetailByJson.getInfo().getDynamic().getViews();
            int posts = thirdBottomDetailByJson.getInfo().getDynamic().getPosts();

            tvName.setText(name);
            tvDesc.setText(desc);
            tvPosts.setText(posts+"帖子");
            tvViews.setText(views+"浏览");
            simpleDraweeView.setImageURI(Uri.parse(pic2));

        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {
        Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }
}
