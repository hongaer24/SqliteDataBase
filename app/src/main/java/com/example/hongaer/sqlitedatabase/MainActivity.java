package com.example.hongaer.sqlitedatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
   private EditText edit_classname,edit_classstype;
   private Button  btn_insert,btn_query;
   private SQLiteDatabase db;
   private Sqliteopenhelper_ sqliteopenhelper_;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sqliteopenhelper_=new Sqliteopenhelper_(MainActivity.this,"class.db",null,1);
        db=sqliteopenhelper_ .getReadableDatabase();
    }

    private void init() {
        edit_classname= (EditText) findViewById(R.id.edit_classname);
        edit_classstype= (EditText) findViewById(R.id.edit_classstype);
        btn_insert= (Button) findViewById(R.id.btn_insert);
        btn_query= (Button) findViewById(R.id.btn_query);

    }

}
