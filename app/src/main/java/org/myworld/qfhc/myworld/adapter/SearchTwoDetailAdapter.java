package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchTwoEntity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 10:16
 * @备注：
 */
public class SearchTwoDetailAdapter extends AbsBaseAdapter<SearchTwoEntity.DataEntity.TopicEntity>{

    public SearchTwoDetailAdapter(Context context) {
        super(context, R.layout.search_detail_two_bottom_item);
    }

    @Override
    public void onBindData(ViewHolder holder, SearchTwoEntity.DataEntity.TopicEntity data, int position) {
        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_detail_two);
        sdv.setImageURI(Uri.parse(data.getPic()));

        holder.onBindTextView(R.id.tv_search_detail_two_name,data.getTitle())
                .onBindTextView(R.id.tv_search_detail_two_likes,data.getLikes());
    }
}
