package org.myworld.qfhc.myworld.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {  

	private static final String DB_NAME = "collects.db";
	private static final int DB_VERSION = 1;

	// 建库
	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// 建表
	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE collect (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,pic VARCHAR(50),title VARCHAR(100),likes VARCHAR(100),msgId VARCHAR(100),isCollect CHAR(2))";
		//String sql2 = "CREATE TABLE coll (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title VARCHAR(50),icon VARCHAR(200),reviewcount VARCHAR(20),url VARCHAR(20),share_msg VARCHAR(20),purchase_url VARCHAR(20),user_name VARCHAR(20))";
		//String sql3 = "CREATE TABLE collgl (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,title VARCHAR(50),tu_url VARCHAR(200),reviewcount VARCHAR(20),user_name VARCHAR(20),url VARCHAR(20),share INTEGER,likes INTEGER,comm INTEGER)";
		db.execSQL(sql);
		//db.execSQL(sql2);
		//db.execSQL(sql3);
	}

	// 升级数据库
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
