package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.IndexHeadActivity;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.ThirdBottomDetailEntity;
import org.myworld.qfhc.myworld.entity.ThirdDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.UseUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/4 17:55
 * @备注：
 */
public class ThirdBottomDetailAdapter extends AbsBaseAdapter<ThirdBottomDetailEntity.DataEntity.PostListEntity> {

    private Context context;

    public ThirdBottomDetailAdapter(Context context) {
        super(context, R.layout.third_listview_detail_item);
        this.context = context;
    }

    @Override
    public void onBindData(ViewHolder holder, final ThirdBottomDetailEntity.DataEntity.PostListEntity data, int position) {
        List<ThirdBottomDetailEntity.DataEntity.PostListEntity.ProductEntity> product = data.getProduct();
        List<ThirdBottomDetailEntity.DataEntity.PostListEntity.TagsEntity> tags = data.getTags();
        LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.ll_third_fenlei);
        if (product.size() != 0) {

            addView(holder, tags, linearLayout);

            holder.onBindTextView(R.id.tv_third_top_detail_datestr, data.getDatestr())
                    .onBindTextView(R.id.tv_third_top_detail_content, data.getContent())
                    .onBindTextView(R.id.tv_third_detail_collect, data.getDynamic().getLikes())
                    .onBindTextView(R.id.tv_third_detail_title, data.getProduct().get(0).getTitle())
                    .onBindTextView(R.id.tv_third_detail_price, "￥"+data.getProduct().get(0).getPrice());

            SimpleDraweeView sdvTop = (SimpleDraweeView) holder.getView(R.id.sdv_third_top_detail);
            sdvTop.setImageURI(Uri.parse(data.getPics().get(0).getUrl()));

            SimpleDraweeView sdvPic = (SimpleDraweeView) holder.getView(R.id.sdv_third_detail_pic);
            sdvPic.setImageURI(Uri.parse(data.getProduct().get(0).getPic()));

            RelativeLayout rl = (RelativeLayout) holder.getView(R.id.rl_third);
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, IndexHeadActivity.class);
                    intent.putExtra(Constant.KEYS.INDEX_HEAD_CONTENT, data.getProduct().get(0).getUrl());
                    context.startActivity(intent);
                }
            });

            ImageView shareImg= (ImageView) holder.getView(R.id.iv_third_detail_share);
            shareImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data!=null) {
                        UseUtil.simpleShowShare(context, data.getProduct().get(0).getUrl(), data.getProduct().get(0).getTitle(), data.getProduct().get(0).getUrl());
                    }
                }
            });

        } else {
            RelativeLayout rl = (RelativeLayout) holder.getView(R.id.rl_third);
            rl.setVisibility(View.GONE);
            TextView tv = (TextView) holder.getView(R.id.tv_third);
            tv.setVisibility(View.GONE);

            addView(holder, tags, linearLayout);

            holder.onBindTextView(R.id.tv_third_top_detail_datestr, data.getDatestr())
                    .onBindTextView(R.id.tv_third_top_detail_content, data.getContent())
                    .onBindTextView(R.id.tv_third_detail_collect, data.getDynamic().getLikes());

            SimpleDraweeView sdvTop = (SimpleDraweeView) holder.getView(R.id.sdv_third_top_detail);
            sdvTop.setImageURI(Uri.parse(data.getPics().get(0).getUrl()));

        }
    }

    private void addView(AbsBaseAdapter.ViewHolder holder, List<ThirdBottomDetailEntity.DataEntity.PostListEntity.TagsEntity> tags, LinearLayout linearLayout) {
        LinearLayout ll = (LinearLayout) holder.getView(R.id.ll_third);
        ll.removeAllViews();
        for (int i = 0; i < tags.size(); i++) {
            String name = tags.get(i).getName();
            String id = tags.get(i).getId();

            TextView tv = new TextView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 10;
            tv.setLayoutParams(params);
            tv.setTextColor(Color.parseColor("#FD6363"));
            tv.setTextSize(12);
            tv.setText(name);
            ll.addView(tv);
        }

        if (tags.size() == 0) {
            linearLayout.setVisibility(View.GONE);
        }
    }
}
