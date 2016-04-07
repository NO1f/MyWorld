package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchDanPinEntity;
import org.myworld.qfhc.myworld.entity.SearchDetailOneEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 13:25
 * @备注：
 */
public class SearchDanPinAdapter extends AbsBaseAdapter<SearchDanPinEntity.DataEntity> {

    public SearchDanPinAdapter(Context context) {
        super(context, R.layout.search_detail_one_item);
    }

    @Override
    public void onBindData(AbsBaseAdapter.ViewHolder holder, SearchDanPinEntity.DataEntity data, int position) {

        holder.onBindTextView(R.id.tv_search_detail_one_title,data.getTitle())
                .onBindTextView(R.id.tv_search_detail_one_price,"￥"+data.getPrice())
                .onBindTextView(R.id.tv_search_detail_one_likes,data.getLikes()+"人推荐")
                .onBindTextView(R.id.tv_search_detail_one_desc,data.getDesc());

        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_detail_one);
        sdv.setImageURI(Uri.parse(data.getPic()));

    }
}
