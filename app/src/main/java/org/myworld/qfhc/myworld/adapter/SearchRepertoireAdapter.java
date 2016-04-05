package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchRepertoireEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 12:05
 * @备注：
 */
public class SearchRepertoireAdapter extends AbsBaseAdapter<SearchRepertoireEntity.DataEntity> {

    public SearchRepertoireAdapter(Context context) {
        super(context, R.layout.search_two_gv_item);
    }


    @Override
    public void onBindData(ViewHolder holder, SearchRepertoireEntity.DataEntity data, int position) {
        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_two_icon);
        sdv.setImageURI(Uri.parse(data.getIcon()));

        holder.onBindTextView(R.id.tv_search_gv_two_name,data.getName())
        .onBindTextView(R.id.tv_search_gv_two_en_name,data.getEn_name());
    }
}
