package org.myworld.qfhc.myworld.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 20:49
 * @备注：
 */
public class GridViewAadpter extends BaseAdapter {

    private Context context;
    private List<ThirdHeadEntity.DataEntity.ModuleElementsEntity.ElementsEntity> datas;

    public GridViewAadpter(Context context){
        this.context=context;
    }

    public void setDatas(List<ThirdHeadEntity.DataEntity.ModuleElementsEntity.ElementsEntity> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.third_gridview_item, null);
            holder.sdvLogo = (SimpleDraweeView) convertView.findViewById(R.id.sdv_third_headimg);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_third_title);
            holder.tvSubTitle = (TextView) convertView.findViewById(R.id.tv_third_sub_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        String title = datas.get(position).getTitle();
        String sub_title = datas.get(position).getSub_title();
        String type = datas.get(position).getType();
        String id = datas.get(position).getId();
        String photo =datas.get(position).getPhoto();

        holder.sdvLogo.setImageURI(Uri.parse(photo));
        holder.tvTitle.setText(title);
        holder.tvSubTitle.setText(sub_title);

        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView sdvLogo;
        TextView tvTitle,tvSubTitle;
    }
}
