package com.example.hongaer.sqlitedatabase;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
    private String index;
    private SimpleAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sqliteopenhelper_ = new Sqliteopenhelper_(MainActivity.this, "class1.db", null, 1);
        db = sqliteopenhelper_.getReadableDatabase();
        btn_insert.setOnClickListener(onClicklistener);
        btn_query.setOnClickListener(onClicklistener);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, final long id) {
                Map<String,Object> map= (Map<String, Object>) listView.getItemAtPosition(position);
                index=map.get("id_").toString();
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请选择");
                builder.setItems(R.array.dialog_arry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                LayoutInflater inflater=getLayoutInflater().from(MainActivity.this);
                                View view=inflater.inflate(R.layout.dialog_item,null);
                                final EditText dialog_classname= (EditText) view.findViewById(R.id.dialog_classname);
                                final EditText dialog_classtype= (EditText) view.findViewById(R.id.dialog_classtype);
                                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("请更改");
                                builder.setView(view);
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SqliedatabaseUtil.updata(db,dialog_classname.getText().toString(),dialog_classtype.getText().toString(),index);
                                        oList.get(position).put("classname_",dialog_classname.getText().toString());
                                        oList.get(position).put("classtype_",dialog_classname.getText().toString());
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                builder.show();
                             Toast.makeText(MainActivity.this,"点击了更改",Toast.LENGTH_LONG).show();

                            break;

                            case 1:
                            SqliedatabaseUtil.delete(db,index);
                            oList.remove(position);
                            adapter.notifyDataSetChanged();
                            break;

                        }
                    }
                });
                 builder.show();
                return false;
            }
        });
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
                    /* 添加后自动查询 */
                    oList= SqliedatabaseUtil.query(db);
                    adapter=new SimpleAdapter(MainActivity.this,oList,R.layout.listview_item,new
                            String[]{"id_","classname_","classtype_"},new int[]{R.id.item_id,R.id.item_classname,R.id.item_classtype});
                    listView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this,"恭喜你添加成功！",Toast.LENGTH_LONG).show();
                    break;

                case R.id.btn_query:
                   oList= SqliedatabaseUtil.query(db);
                  adapter=new SimpleAdapter(MainActivity.this,oList,R.layout.listview_item,new
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


