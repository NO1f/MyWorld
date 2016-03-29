package org.myworld.qfhc.myworld.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.myworld.qfhc.myworld.fragment.IndexContentFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/14 20:27
 * @备注：
 */
public abstract class AbsAdapter<T> extends RecyclerView.Adapter<AbsAdapter.MyViewHolder> {

    private Context context;
    private List<T> datas;
    private int resid;

    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }


    public AbsAdapter(Context context,int resid){
        this.context=context;
        this.resid=resid;
        datas=new ArrayList<>();
    }

    public void setDatas(List<T> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }

    public void addData(T data,int position){
        datas.add(position,data);
        notifyItemInserted(position);
    }

    public void deleteData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public AbsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(resid, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AbsAdapter.MyViewHolder holder, int position) {
        bindDatas(holder,datas.get(position),position);
    }

    protected abstract void bindDatas(MyViewHolder myViewHolder, T data, int position);

    @Override
    public int getItemCount() {
        return datas.size();
    }

    protected class MyViewHolder extends RecyclerView.ViewHolder{

        private Map<Integer, View> cacheMap = new HashMap<>();
        private View itemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onClickListener != null){
                        onClickListener.onClick(v, getAdapterPosition());
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(onLongClickListener != null){
                        onLongClickListener.onLongClick(v, getAdapterPosition());
                    }
                    return true;
                }
            });
        }

        public View getView(int id){
            if (cacheMap.containsKey(id)){
                return cacheMap.get(id);
            }else {
                View view=itemView.findViewById(id);
                cacheMap.put(id,view);
                return view;
            }
        }

        public MyViewHolder bindTextView(int id, String data){
            TextView tv = (TextView) getView(id);
            tv.setText(data);
            return this;
        }

    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

    public interface OnLongClickListener{
        void onLongClick(View v, int position);
    }
}
