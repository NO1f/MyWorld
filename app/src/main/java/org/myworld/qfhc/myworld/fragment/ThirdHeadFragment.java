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
import org.myworld.qfhc.myworld.util.L;

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
        GridViewAadpter adapter = new GridViewAadpter(getActivity());
        adapter.setDatas(elements);
        mGv.setAdapter(adapter);

    }

    @Override
    protected void loadData() {

        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThirdHeadEntity.DataEntity.ModuleElementsEntity.ElementsEntity elementsEntity = elements.get(position);
                String type = elementsEntity.getType();
                String extend = elementsEntity.getExtend();
                String title = elementsEntity.getTitle();
                String url=null;

                if (type.equals("post_list_element")){
                    url = Constant.URL.THIRD_ONE_ELE;
                }else if (type.equals("post_list_tag")){
                    url = Constant.URL.THIRD_ONE_TAG;
                }
                if (url!=null){
                   // L.e(url+"=======================================");
                    Intent intent=new Intent(getActivity(),ThirdHeadDetailActivity.class);
                    intent.putExtra(Constant.KEYS.THIRD_DETAIL_ID,extend);
                    intent.putExtra(Constant.KEYS.THIRD_DETAIL_URL,url);
                    intent.putExtra(Constant.KEYS.THIRD_DETAIL_TITLE,title);
                    getActivity().startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
