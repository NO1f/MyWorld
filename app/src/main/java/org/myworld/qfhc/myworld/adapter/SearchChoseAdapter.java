package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailOneDetActivity;
import org.myworld.qfhc.myworld.base.AbsAdapter;
import org.myworld.qfhc.myworld.entity.SearchDeatilTwoChoseEntity;
import org.myworld.qfhc.myworld.util.Constant;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/6 14:20
 * @备注：
 */
public class SearchChoseAdapter extends AbsAdapter<SearchDeatilTwoChoseEntity.DataEntity.ProductEntity> {

    private Context context;

    public SearchChoseAdapter(Context context) {
        super(context, R.layout.search_chose_item);
        this.context = context;
    }

    @Override
    protected void bindDatas(MyViewHolder myViewHolder, SearchDeatilTwoChoseEntity.DataEntity.ProductEntity data, int position) {

        final String id = data.getId();

        myViewHolder.bindTextView(R.id.tv_chose_shu, (position + 1) + "")
                .bindTextView(R.id.tv_chose_title, data.getTitle())
                .bindTextView(R.id.tv_chose_desc, data.getDesc())
                .bindTextView(R.id.tv_chose_price, "参考价格：￥" + data.getPrice());

        List<SearchDeatilTwoChoseEntity.DataEntity.ProductEntity.PicEntity> pic = data.getPic();

        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;

        LinearLayout linearLayout = (LinearLayout) myViewHolder.getView(R.id.ll_chose);
        linearLayout.removeAllViews();
        for (int i = 0; i < pic.size(); i++) {
            String url = pic.get(i).getP();
            url = "http://bt.img.17gwx.com/" + url;

            SimpleDraweeView sdv = new SimpleDraweeView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthPixels, 460);
            params.topMargin = 10;
            sdv.setLayoutParams(params);
            sdv.setImageURI(Uri.parse(url));
            linearLayout.addView(sdv);
        }

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SearchDetailOneDetActivity.class);
                intent.putExtra(Constant.KEYS.SEARCH_ONE_DETAIL_ID, id);
                context.startActivity(intent);

            }
        });

    }
}
