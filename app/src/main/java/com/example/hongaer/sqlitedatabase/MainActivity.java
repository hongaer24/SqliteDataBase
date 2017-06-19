package com.example.hongaer.sqlitedatabase;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText edit_classname, edit_classtype;
    private Button btn_insert, btn_query;
    private SQLiteDatabase db;
    private Sqliteopenhelper_ sqliteopenhelper_;
    private List<Map<String, Object>> oList;
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sqliteopenhelper_ = new Sqliteopenhelper_(MainActivity.this, "class1.db", null, 1);
        db = sqliteopenhelper_.getReadableDatabase();
        btn_insert.setOnClickListener(onClicklistener);
        btn_query.setOnClickListener(onClicklistener);
    }

    private View.OnClickListener onClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btn_insert:
                   SqliedatabaseUtil.insertInto(db, edit_classname.getText().toString(),
                            edit_classtype.getText().toString());
                    edit_classname.setText("");
                    edit_classtype.setText("");
                    Toast.makeText(MainActivity.this,"恭喜你添加成功！",Toast.LENGTH_LONG).show();
                    break;

                case R.id.btn_query:
                   oList= SqliedatabaseUtil.query(db);
                    SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,oList,R.layout.listview_item,new
                            String[]{"id_","classname_","classtype_"},new int[]{R.id.item_id,R.id.item_classname,R.id.item_classtype});
                       listView.setAdapter(adapter);
                    break;
            }
        }
    };

        private void init() {
            edit_classname = (EditText) findViewById(R.id.edit_classname);
            edit_classtype = (EditText) findViewById(R.id.edit_classtype);
            btn_insert = (Button) findViewById(R.id.btn_insert);
            btn_query = (Button) findViewById(R.id.btn_query);
            listView= (ListView) findViewById(R.id.listview);


        }

    }


