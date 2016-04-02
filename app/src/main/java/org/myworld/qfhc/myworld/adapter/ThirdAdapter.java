package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 23:51
 * @备注：
 */
public class ThirdAdapter extends AbsBaseAdapter<ThirdHeadEntity.DataEntity.RecGroupsEntity> {
    public ThirdAdapter(Context context) {
        super(context, R.layout.third_content_item);
    }

    @Override
    public void onBindData(ViewHolder holder, ThirdHeadEntity.DataEntity.RecGroupsEntity data, int position) {
        holder.onBindTextView(R.id.tv_third_name, data.getName())
                .onBindTextView(R.id.tv_third_views, data.getDynamic().getViews() + "浏览")
                .onBindTextView(R.id.tv_third_posts, data.getDynamic().getPosts() + "帖子");

        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_third_bottomimg);
        sdv.setImageURI(Uri.parse(data.getPic2()));
    }
}
