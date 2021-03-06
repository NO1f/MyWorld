package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.entity.IndextContentEntity;
import org.myworld.qfhc.myworld.util.L;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/29 15:00
 * @备注：
 */
public class IndexContentAdapter extends AbsAdapter<IndextContentEntity.DataEntity.PostListEntity.ListEntity> {

    private String imgBig_url;
    private String title;
    private String introduction;
    private int itemCount;

    public IndexContentAdapter(Context context) {
        super(context, R.layout.indexcontent_item);
    }

    @Override
    protected void bindDatas(AbsAdapter<IndextContentEntity.DataEntity.PostListEntity.ListEntity>.MyViewHolder myViewHolder, IndextContentEntity.DataEntity.PostListEntity.ListEntity data, int position) {

        String smallAvatar_url = data.getAuthor().getSmallAvatar();
        String name = data.getAuthor().getName();
        int collectCount = data.getCollectCount();

        if (data.getAlbum()!=null){
            imgBig_url = data.getAlbum().getCover().getUrl();
            title = data.getAlbum().getTitle();
            introduction = data.getAlbum().getIntroduction();
            itemCount = data.getAlbum().getItemCount();

            myViewHolder.bindTextView(R.id.tv_first_title,title)
                    .bindTextView(R.id.tv_first_introduction,introduction)
                    .bindTextView(R.id.tv_first_name,name)
                    .bindTextView(R.id.tv_first_itemCount,itemCount+"件好物")
                    .bindTextView(R.id.tv_first_collectCount,collectCount+"");

            SimpleDraweeView big = (SimpleDraweeView) myViewHolder.getView(R.id.sdv_first_big);
            big.setImageURI(Uri.parse(imgBig_url));

            SimpleDraweeView small= (SimpleDraweeView) myViewHolder.getView(R.id.sdv_first_smallAvatar);
            small.setImageURI(Uri.parse(smallAvatar_url));

        }else {
            myViewHolder.bindTextView(R.id.tv_first_title,"品质生活 | 提高宿舍幸福感的小功率电器")
                    .bindTextView(R.id.tv_first_introduction,"宿舍党最头疼的问题就是限电了， 分享一下宿舍可用的小功率电器。")
                    .bindTextView(R.id.tv_first_name,name)
                    .bindTextView(R.id.tv_first_itemCount,"13件好物")
                    .bindTextView(R.id.tv_first_collectCount,collectCount+"");

            SimpleDraweeView big = (SimpleDraweeView) myViewHolder.getView(R.id.sdv_first_big);
            big.setImageURI(Uri.parse("http://img1.lukou.com/static/p/album/medium/0004/92/26/31/4922631.jpg"));

            SimpleDraweeView small= (SimpleDraweeView) myViewHolder.getView(R.id.sdv_first_smallAvatar);
            small.setImageURI(Uri.parse(smallAvatar_url));
        }




    }
}
