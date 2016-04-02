package org.myworld.qfhc.myworld.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.activity.ThirdHeadDetailActivity;
import org.myworld.qfhc.myworld.adapter.GridViewAadpter;
import org.myworld.qfhc.myworld.base.BaseFragment;
import org.myworld.qfhc.myworld.entity.ThirdHeadEntity;
import org.myworld.qfhc.myworld.util.Constant;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/1 19:44
 * @备注：
 */
public class ThirdHeadFragment extends BaseFragment {

    private GridView mGv;
    private List<ThirdHeadEntity.DataEntity.ModuleElementsEntity.ElementsEntity> elements;
    private ThirdHeadEntity.DataEntity.ModuleElementsEntity datas;

    public static ThirdHeadFragment newInstance(ThirdHeadEntity.DataEntity.ModuleElementsEntity datas) {

        Bundle args = new Bundle();
        args.putSerializable(Constant.KEYS.THIRD_HEAD, datas);
        ThirdHeadFragment fragment = new ThirdHeadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentResid() {
        return R.layout.third_head_fragment;
    }

    @Override
    protected void init(View view) {

        Bundle bundle = getArguments();
        datas = (ThirdHeadEntity.DataEntity.ModuleElementsEntity) bundle.getSerializable(Constant.KEYS.THIRD_HEAD);
        if (datas !=null){
            elements = datas.getElements();
        }

        mGv = (GridView) view.findViewById(R.id.gv_third);
        GridViewAadpter adpter = new GridViewAadpter(getActivity());
        adpter.setDatas(elements);
        mGv.setAdapter(adpter);

    }

    @Override
    protected void loadData() {

        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThirdHeadEntity.DataEntity.ModuleElementsEntity.ElementsEntity elementsEntity = elements.get(position);
                String type = elementsEntity.getType();
                String mid = elementsEntity.getId();
                String url=null;

                if (type.equals("post_list_element")){
                    url = String.format(Constant.URL.THIRD_ONE_ELE, mid);
                }else if (type.equals("post_list_tag")){
                    url = String.format(Constant.URL.THIRD_ONE_TAG, mid);
                }
                if (url!=null){
                    Intent intent=new Intent(getActivity(),ThirdHeadDetailActivity.class);
                    intent.putExtra(Constant.KEYS.THIRD_DETAIL_URL,url);
                }else {
                    Toast.makeText(getActivity(), "数据加载失败，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
