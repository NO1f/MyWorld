package org.myworld.qfhc.myworld.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.DateBaseAdapter;
import org.myworld.qfhc.myworld.adapter.LikesAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.util.Constant;
import org.myworld.qfhc.myworld.util.DbUtil;

import java.util.List;
import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 21:23
 * @备注：
 */
public class MineLikesActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView ivBAck;
    private GridView mGv;
    private DbUtil mUtil;
    private SQLiteDatabase db;
    private String sql;
    private TextView tvEmpty;
    private List<Map<String, Object>> maps;
    private LikesAdapter adapter;

    @Override
    protected int getContentResid() {
        return R.layout.mine_likes;
    }

    @Override
    protected void onStart() {
        super.onStart();
        tvEmpty = (TextView) findViewById(R.id.tv_empty);
        ivBAck = (ImageView) findViewById(R.id.iv_search_detail_back);
        ivBAck.setOnClickListener(this);

        mGv= (GridView) findViewById(R.id.gv_mine);
        mGv.setEmptyView(tvEmpty);

        mUtil = new DbUtil(this);
        db = mUtil.getDatabase();

        // 查询数据库.展示收藏结果.
        sql = "select * from liked";
        maps = mUtil.queryList(sql, null);

        mGv.setOnItemClickListener(this);
        adapter = new LikesAdapter(this);
        adapter.setDatas(maps);
        mGv.setAdapter(adapter);

        // 注册上下文菜单
        registerForContextMenu(mGv);
    }


    // 创建上下文菜单.移除收藏.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.remove_like, menu);
    }

    // 实现移除收藏功能
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        // 得到对应位置的Cursor数据.
        ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        String mid  = (String) maps.get(position).get("msgId");

        // 实现移除功能.从数据库中删除指定收藏条目.
        if (item.getItemId() == R.id.like_remove) {
            int delete = db.delete("liked", "msgId=?", new String[] { mid });
            if (delete > 0) {
                // 重新查询数据库.刷新界面.
                maps = mUtil.queryList(sql, null);
                adapter.setDatas(maps);

                mGv.setAdapter(adapter);
                Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }


    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,SearchDetailOneDetActivity.class);
        intent.putExtra(Constant.KEYS.SEARCH_ONE_DETAIL_ID,(String)maps.get(position).get("msgId"));
        startActivity(intent);
    }
}
