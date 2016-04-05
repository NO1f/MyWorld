package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchSKUEntity;
import org.myworld.qfhc.myworld.util.L;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 12:05
 * @备注：
 */
public class SearchSKUGVAdapter extends AbsBaseAdapter<SearchSKUEntity.DataEntity.SubclassEntity> {

    public SearchSKUGVAdapter(Context context) {
        super(context, R.layout.search_gv_item);
    }


    @Override
    public void onBindData(ViewHolder holder, SearchSKUEntity.DataEntity.SubclassEntity data, int position) {
        SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_one_icon);
        sdv.setImageURI(Uri.parse(data.getIcon()));

        holder.onBindTextView(R.id.tv_search_gv_one_name,data.getName());
    }
}
