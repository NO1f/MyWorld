package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.entity.SearchTieZiEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/7 01:29
 * @备注：
 */
public class SearchTieZiAdapter extends AbsAdapter<SearchTieZiEntity.DataEntity> {

    public SearchTieZiAdapter(Context context) {
        super(context, R.layout.search_tiezi_item);
    }

    @Override
    protected void bindDatas(MyViewHolder myViewHolder, SearchTieZiEntity.DataEntity data, int position) {

        SimpleDraweeView sdv = (SimpleDraweeView) myViewHolder.getView(R.id.sdv_tiezi);
        sdv.setImageURI(Uri.parse(data.getPic()));

        myViewHolder.bindTextView(R.id.tv_tiezi,data.getContent());

    }
}
