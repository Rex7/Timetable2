package com.example.lustr.timetable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Sche extends AppCompatActivity {
    ArrayAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sche);
        listView=(ListView) findViewById(R.id.listview);
        Bundle b=this.getIntent().getExtras();
        String[] arrayR;
        arrayR=b.getStringArray("sc1");
        adapter=new ArrayAdapter<String>(this,R.layout.activity_list_items,R.id.textView3,arrayR);
        listView.setAdapter(adapter);

    }
}
