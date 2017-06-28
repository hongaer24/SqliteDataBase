package com.example.hongaer.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hongaer on 2017/6/18.
 */

public class SqliedatabaseUtil {
     /*数据库添加语句*/
    public  static void insertInto(SQLiteDatabase db,String classname,String classtype){
        db.execSQL("insert into  class_tc(classname,classtype) values (?,?)",new String[]{classname,classtype});
    }
      /*数据库查询语句*/
     public static List<Map<String,Object>> query(SQLiteDatabase db){
         List<Map<String,Object>> oList=new ArrayList<Map<String,Object>>();
         Cursor cursor= db.rawQuery("select * from  class_tc",null);
          while (cursor.moveToNext()){
              Map<String, Object> map=new HashMap<>();
              map.put("id_",cursor.getInt(cursor.getColumnIndex(cursor.getColumnName(0))));
              map.put("classname_",cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1))));
              map.put("classtype_",cursor.getString(cursor.getColumnIndex(cursor.getColumnName(2))));
              oList.add(map);
          }
            return oList;
     }
    /* 数据库修改语句*/
  public static void updata(SQLiteDatabase db,String classname,String classtype,String id) {
      db.execSQL("update class_tc set classname=? where _id=?", new String[]{classname, id});
      db.execSQL("update class_tc set classtype=? where _id=?", new String[]{classtype, id});

    }
   public static void delete(SQLiteDatabase db,String id){
      db.execSQL("delete from class_tc where _id= ?",new String[]{id});
   }
}
