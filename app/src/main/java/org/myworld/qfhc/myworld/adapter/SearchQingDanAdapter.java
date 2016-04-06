package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchQingDanEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 00:56
 * @备注：
 */
public class SearchQingDanAdapter extends AbsBaseAdapter<SearchQingDanEntity.DataEntity> {

    public SearchQingDanAdapter(Context context) {
        super(context, R.layout.search_detail_two_bottom_item);
    }

    @Override
    public void onBindData(ViewHolder holder, SearchQingDanEntity.DataEntity data, int position) {
        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_detail_two);
        sdv.setImageURI(Uri.parse(data.getPic()));

        holder.onBindTextView(R.id.tv_search_detail_two_name,data.getTitle())
                .onBindTextView(R.id.tv_search_detail_two_likes,data.getLikes());
    }
}
