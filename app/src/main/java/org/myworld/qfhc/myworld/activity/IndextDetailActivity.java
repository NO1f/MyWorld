package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.android.volley.VolleyError;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.IndexDetailAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.entity.IndexDetailEntity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.JSONUtil;
import org.myworld.qfhc.myworld.util.VolleyUtil;

import java.util.List;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/3/30 01:35
 * @备注：
 */
public class IndextDetailActivity extends BaseActivity implements VolleyUtil.OnRequestListener {

    private ListView mLv;
    private String detail_url;
    private IndexDetailAdapter adapter;

    @Override
    protected int getContentResid() {
        return R.layout.indext_detail_layout;
    }

    @Override
    protected void init() {

        Intent intent = getIntent();
        detail_url = intent.getStringExtra(Constant.KEYS.INDEX_DETAIL_URL);

        mLv= (ListView) findViewById(R.id.lv_first_detail);
        adapter = new IndexDetailAdapter(this);
        mLv.setAdapter(adapter);

    }

    @Override
    protected void initData() {

        VolleyUtil.requestString(detail_url,this);

    }

    @Override
    public void onResponse(String url, String response) {
        if (response!=null){
            IndexDetailEntity.DataEntity detailByJson = JSONUtil.getDetailByJson(response);
            IndexDetailEntity.DataEntity.AlbumEntity album = detailByJson.getAlbum();
            List<IndexDetailEntity.DataEntity.AlbumEntity.ContentListEntity> contentList = album.getContentList();
            adapter.setDatas(contentList);
        }
    }

    @Override
    public void onErrorResponse(String url, VolleyError error) {

    }

    public void click(View view){
        switch (view.getId()){
            case R.id.iv_head_back:
                    finish();
                break;
            case R.id.iv_head_share:
                break;
            case R.id.iv_head_menu:
                break;
        }
    }
}
