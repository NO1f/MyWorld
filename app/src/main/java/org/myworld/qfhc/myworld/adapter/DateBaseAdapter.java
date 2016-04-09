package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.SearchDetailTwoDetActivity;
import org.myworld.qfhc.myworld.base.AbsBaseAdapter;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.L;

import java.util.List;
import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/9 11:31
 * @备注：
 */
public class DateBaseAdapter extends AbsBaseAdapter <Map<String, Object>> {

    private Context context;

    public DateBaseAdapter(Context context) {
        super(context, R.layout.search_detail_two_bottom_item);
        this.context=context;
    }

    @Override
    public void onBindData(ViewHolder holder, Map<String, Object> data, int position) {

        if (data != null) {
            final String title = (String) data.get("title");
            final String likes = (String) data.get("likes");
            final String pic = (String) data.get("pic");
            L.e(pic+"_+++++++++++++++++++++++++++++++++++++++++++");
            final String mId = (String) data.get("msgId");

            SimpleDraweeView sdv = (SimpleDraweeView) holder.getView(R.id.sdv_search_detail_two);
            sdv.setImageURI(Uri.parse(pic));

            holder.onBindTextView(R.id.tv_search_detail_two_name, title)
                    .onBindTextView(R.id.tv_search_detail_two_likes, likes);

            LinearLayout ll = (LinearLayout) holder.getView(R.id.ll_collect);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SearchDetailTwoDetActivity.class);
                    intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_ID, mId);
                    intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_PIC, pic);
                    intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_TITLE, title);
                    intent.putExtra(Constant.KEYS.SEARCH_TWO_DET_LIKES, likes);
                    context.startActivity(intent);
                }
            });
        }

    }
}
