package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.IndexHeadActivity;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.IndexDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 10:03
 * @备注：
 */
public class IndexDetailAdapter extends AbsBaseAdapter<IndexDetailEntity.DataEntity.AlbumEntity.ContentListEntity> {
    private Context context;

    public IndexDetailAdapter(Context context) {
        super(context, R.layout.index_detail_item);
        this.context=context;
    }

    @Override
    public void onBindData(ViewHolder holder, IndexDetailEntity.DataEntity.AlbumEntity.ContentListEntity data, int position) {
        String title = data.getTitle();
        String reason = data.getReason();
        String shareImage = data.getContainer().getCommodity().getShareImage();
        int collectCount = data.getContainer().getCollectCount();
        String price = data.getContainer().getCommodity().getPrice();
        Object oldPrice = data.getContainer().getCommodity().getOldPrice();
        final String commodityUrl = data.getContainer().getCommodity().getCommodityUrl();


        holder.onBindTextView(R.id.tv_first_nav,position+"")
                .onBindTextView(R.id.tv_first_detail_title,title)
                .onBindTextView(R.id.tv_first_detail_reason,reason)
                .onBindTextView(R.id.tv_first_detail_collect,collectCount+"")
                .onBindTextView(R.id.tv_first_detail_price,"￥"+price);

        if (oldPrice!=null){
            holder.onBindTextView(R.id.tv_first_detail_oldprice,"￥"+oldPrice);
        }else {
            holder.getView(R.id.tv_first_detail_oldprice).setVisibility(View.INVISIBLE);
            holder.getView(R.id.tv_first_detail_clip).setVisibility(View.INVISIBLE);
        }

        SimpleDraweeView sdv_img = (SimpleDraweeView) holder.getView(R.id.sdv_first_detail_img);
        sdv_img.setImageURI(Uri.parse(shareImage));
        Button btnTaobao = (Button) holder.getView(R.id.btn_first_detail_taobao);
        btnTaobao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, IndexHeadActivity.class);
                intent.putExtra(Constant.KEYS.INDEX_DETAIL_URL,commodityUrl);
                context.startActivity(intent);
            }
        });
    }
}
