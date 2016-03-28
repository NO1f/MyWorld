package org.myworld.qfhc.myworld.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：HC
 * @创建时间：${date} ${time}
 * @备注：FirstReceiver
 */
public abstract class AbsBaseAdapter<T> extends BaseAdapter {
    private Context context;
    private List<T> datas;
    private int resid;

    public AbsBaseAdapter(Context context,int resid){
        this.context = context;
        datas = new ArrayList<>();
        this.resid=resid;
    }

    public void setDatas(List<T> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }

    public void addDatas(List<T> datas){
        this.datas.addAll(datas);
        this.notifyDataSetChanged();
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
        if (convertView==null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView=inflater.inflate(resid,null);
            holder=new ViewHolder(convertView);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        onBindData(holder,datas.get(position),position);
        return convertView;
    }

    public abstract void onBindData(ViewHolder holder,T data,int position);

    protected class ViewHolder {

        View layoutView;
        Map<Integer,View> cacheMap=new HashMap<>();

        public ViewHolder(View layoutView){
            this.layoutView=layoutView;
        }

        public View getView(int id){

            if (cacheMap.containsKey(id)){
                return cacheMap.get(id);
            }else {
                View view = layoutView.findViewById(id);
                cacheMap.put(id,view);
                return view;
            }
        }

       public ViewHolder onBindTextView(int id, String text){
           TextView tv= (TextView) this.getView(id);
           tv.setText(text);
            return this;
        }

    }
}
