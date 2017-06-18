package com.example.hongaer.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hongaer on 2017/6/18.
 */

public class Sqliteopenhelper_ extends SQLiteOpenHelper {
      private String CREATE_TB ="create table class_tb(_id integer primary key autoincrement,classname,classstype)";

    public Sqliteopenhelper_(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    //创建数据库调用该方法
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB);//创建表

    }

    @Override
    //数据库版本更新时调用该方法
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
