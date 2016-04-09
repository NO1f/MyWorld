package org.myworld.qfhc.myworld.activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.myworld.qfhc.myworld.R;
import org.myworld.qfhc.myworld.adapter.DateBaseAdapter;
import org.myworld.qfhc.myworld.base.BaseActivity;
import org.myworld.qfhc.myworld.util.DbUtil;

import java.util.List;
import java.util.Map;

/**
 * @类描述: ${TODO}
 * @创建时间：2016/4/8 21:27
 * @备注：
 */
public class MineCollectActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivBAck;
    private ListView mLv;
    private SQLiteDatabase db;
    private String sql;
    private DateBaseAdapter adapter;
    private DbUtil mUtil;
    private List<Map<String, Object>> maps;

    @Override
    protected int getContentResid() {
        return R.layout.mine_collects;
    }

    @Override
    protected void onStart() {
        super.onStart();

        ivBAck = (ImageView) findViewById(R.id.iv_search_detail_back);
        ivBAck.setOnClickListener(this);

        mLv= (ListView) findViewById(R.id.lv_mine);
        mUtil = new DbUtil(this);
        db = mUtil.getDatabase();

        // 查询数据库.展示收藏结果.
        sql = "select * from collect";
        maps = mUtil.queryList(sql, null);
        adapter = new DateBaseAdapter(this);
        adapter.setDatas(maps);
        mLv.setAdapter(adapter);

        // 注册上下文菜单
        registerForContextMenu(mLv);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    // 创建上下文菜单.移除收藏.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.remove_collect, menu);
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
        if (item.getItemId() == R.id.action_remove) {
            int delete = db.delete("collect", "msgId=?", new String[] { mid });
            if (delete > 0) {
                // 重新查询数据库.刷新界面.
                maps = mUtil.queryList(sql, null);
                adapter.setDatas(maps);
                mLv.removeAllViews();
                // 交换Cursor结果的方法.
                mLv.setAdapter(adapter);
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
}
