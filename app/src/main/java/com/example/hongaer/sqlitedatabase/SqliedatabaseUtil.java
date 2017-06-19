package com.example.hongaer.sqlitedatabase;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hongaer on 2017/6/18.
 */

public class SqliedatabaseUtil {

    public  static void insertInto(SQLiteDatabase db,String classname,String classtype){
        db.execSQL("insert into  class_tc(classname,classtype) values (?,?)",new String[]{classname,classtype});
    }

}
