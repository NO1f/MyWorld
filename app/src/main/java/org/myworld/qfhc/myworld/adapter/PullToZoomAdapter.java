package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.MineCollectActivity;
import org.myworld.qfhc.myworld.activity.MineLikesActivity;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 14:13
 * @备注：
 */
public class PullToZoomAdapter extends AbsBaseAdapter<String>{

    private ImageView iv;
    private Context context;

    public PullToZoomAdapter(Context context) {
        super(context, R.layout.mine_item);
    }

    @Override
    public void onBindData(ViewHolder holder, String data, int position) {
        holder.onBindTextView(R.id.tv_title,data);
        switch (position){
            case 0:

                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.ic_feed_favourite_normal);
                Intent intent1=new Intent(context,MineLikesActivity.class);
                context.startActivity(intent1);
                break;

            case 1:
                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.ic_setting_score);
                Intent intent=new Intent(context,MineCollectActivity.class);
                context.startActivity(intent);
                break;


            case 2:
                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.ic_trash);
                break;


            case 3:
                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.zone_my_order);
                break;

            case 4:
                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.ic_refresh);
                break;
            case 5:
                iv = (ImageView) holder.getView(R.id.mine_icon);
                iv.setImageResource(R.drawable.ic_about);
                break;
        }
    }
}
