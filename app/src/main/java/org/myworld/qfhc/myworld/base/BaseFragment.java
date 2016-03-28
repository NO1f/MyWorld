package org.myworld.qfhc.myworld.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @version V1.0
 * @类名称: ${type_name}
 * @类描述: ${todo}
 * @创建人：HC
 * @创建时间：${date} ${time}
 * @备注：FirstReceiver
 */
public abstract class BaseFragment extends Fragment {

    protected abstract int getContentResid();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentResid(),null);
    }

    /**
     * 该方法会紧跟着onCreateView（）被调用
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getData(getArguments());
        loadData();
    }

    protected void loadData(){

    };

    protected void init(View view){

    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    protected void getData(Bundle bundle){

    };

}
