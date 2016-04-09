package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.MineLikesActivity;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/9 15:29
 * @备注：
 */
public class LikesAdapter extends AbsBaseAdapter<Map<String, Object>>{

    public LikesAdapter(Context context) {
        super(context, R.layout.mine_likes_item);
    }

    @Override
    public void onBindData(ViewHolder holder, Map<String, Object> data, int position) {

        ImageView iv= (ImageView) holder.getView(R.id.sdv_likes);
        VolleyUtil.requestImage((String) data.get("picture"),iv,R.drawable.shadow_bottom,R.drawable.shadow_bottom);


        holder.onBindTextView(R.id.tv_likes_title, (String) data.get("title"))
                .onBindTextView(R.id.tv_likes_price,"￥"+data.get("price"));

    }
}
