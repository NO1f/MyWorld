package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 15:00
 * @备注：
 */
public class IndexContentAdapter extends AbsBaseAdapter<IndextContentEntity.DataEntity.PostListEntity.ListEntity> {
    public IndexContentAdapter(Context context) {
        super(context, R.layout.indexcontent_item);
    }


    @Override
    public void onBindData(ViewHolder holder, IndextContentEntity.DataEntity.PostListEntity.ListEntity data, int position) {
        String imgBig_url = data.getAlbum().getCover().getUrl();
        String title = data.getAlbum().getTitle();
        String introduction = data.getAlbum().getIntroduction();
        int itemCount = data.getAlbum().getItemCount();
        String smallAvatar_url = data.getAuthor().getSmallAvatar();
        String name = data.getAuthor().getName();
        int collectCount = data.getCollectCount();

        holder.onBindTextView(R.id.tv_first_title,title)
        .onBindTextView(R.id.tv_first_introduction,introduction)
        .onBindTextView(R.id.tv_first_name,name)
        .onBindTextView(R.id.tv_first_itemCount,itemCount+"件好物")
        .onBindTextView(R.id.tv_first_collectCount,collectCount+"");

        SimpleDraweeView big = (SimpleDraweeView) holder.getView(R.id.sdv_first_big);
        big.setImageURI(Uri.parse(imgBig_url));

        SimpleDraweeView small= (SimpleDraweeView) holder.getView(R.id.sdv_first_smallAvatar);
        small.setImageURI(Uri.parse(smallAvatar_url));
    }
}
