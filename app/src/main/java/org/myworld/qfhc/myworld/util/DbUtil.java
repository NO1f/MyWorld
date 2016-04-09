package org.myworld.qfhc.myworld.util;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.myworld.qfhc.myworld.dao.DbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtil {

	private SQLiteDatabase db;

	public DbUtil(Context context) {
		DbHelper helper = new DbHelper(context);
		// helper.getReadableDatabase();
		db = helper.getWritableDatabase();
	}

	// 得到数据库对象
	public SQLiteDatabase getDatabase() {

		if (db != null) {
			return db;
		}

		return null;
	}

	// 执行SQL语句的方法:针对增删改及建表等操作,对查询语句无效!
	public void execSQL(String sql, String[] bindArgs) {
		if (bindArgs != null) {
			db.execSQL(sql, bindArgs);
		} else {
			db.execSQL(sql);
		}
	}

	// 查询数据库
	public Cursor rawQuery(String sql, String[] selectionArgs) {

		return db.rawQuery(sql, selectionArgs);
	}

	//直接查询得到一个集合
	public List<Map<String, Object>> queryList(String sql, String[] selectionArgs) {
		Cursor cursor = rawQuery(sql, selectionArgs);
		return cursorToList(cursor);
	}

	// 将Cursor转换成List集合
	public List<Map<String, Object>> cursorToList(Cursor cursor) {

		if (cursor != null) {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (cursor.moveToNext()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 得到Cursor中列的数量
				int columnCount = cursor.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					// 根据列号得到列名
					String columnName = cursor.getColumnName(i);
					// 根据列号得到值
					String value = cursor.getString(cursor
							.getColumnIndex(columnName));
					map.put(columnName, value);
				}
				list.add(map);
			}

			// cursor也要关闭.
			cursor.close();

			return list;
		}

		return null;

	}

	// 打印Cursor中的每一行记录
	public void printCursor(Cursor cursor) {

		if (cursor != null) {
			while (cursor.moveToNext()) {
				int columnCount = cursor.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String columnName = cursor.getColumnName(i);
					String value = cursor.getString(cursor
							.getColumnIndex(columnName));
					Log.i("TAG", "[columnName]=" + columnName + ",[value]="
							+ value);
				}
			}
		}

	}

	// 关闭数据库
	public void closeDb() {

		if (db != null) {
			db.close();
		}
	}
}
