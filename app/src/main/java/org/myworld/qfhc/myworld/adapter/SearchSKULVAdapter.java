package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.SearchSKUEntity;
import org.myworld.qfhc.myworld.util.L;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/5 11:59
 * @备注：
 */
public class SearchSKULVAdapter extends AbsBaseAdapter<SearchSKUEntity.DataEntity> {

    public SearchSKULVAdapter(Context context) {
        super(context, R.layout.search_lv_item);
    }

    @Override
    public void onBindData(ViewHolder holder,SearchSKUEntity.DataEntity data, int position) {

       holder.onBindTextView(R.id.tv_search_lv_one_name,data.getName());


    }
}
