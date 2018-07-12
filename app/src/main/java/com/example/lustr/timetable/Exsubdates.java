package com.example.lustr.timetable;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

    public class Exsubdates extends AppCompatActivity {
        TextView tvsd,tvsub,tvdates;
        EditText etsub,etdates;
        Button btnsub,btndates,btnbckex;
        SQLiteOpenHelper openHelper;
        SQLiteDatabase db;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_exsubdates);
            openHelper=new DatabaseHelper(this);
            db=openHelper.getWritableDatabase();
            tvsd = (TextView) findViewById(R.id.tvsd);
            tvsub = (TextView) findViewById(R.id.tvsub);
            tvdates = (TextView) findViewById(R.id.tvdates);
            etsub = (EditText) findViewById(R.id.etsub);
            etdates = (EditText) findViewById(R.id.etdates);
            btnsub=(Button)findViewById(R.id.btnsub);
            btndates=(Button)findViewById(R.id.btndates);

            btnsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etsub.getText().toString().length()==0)
                    {
                        etsub.setError("Enter the subject");
                        etsub.requestFocus();
                        return;
                    }

                    else {
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            String eid = extras.getString("key");
                            String esub = etsub.getText().toString();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(DatabaseHelper.ESUB, esub);
                            contentValues.put(DatabaseHelper.ESEID, eid);
                            long id=db.insert(DatabaseHelper.EXSUB, null, contentValues);
                            Intent intent = new Intent(Exsubdates.this, Exsubdates.class);
                            Log.i("parent",eid);
                            intent.putExtra("key",eid);
                            startActivity(intent);

                            Toast.makeText(Exsubdates.this, "Subject added successfully", Toast.LENGTH_SHORT).show();
                        }

                    }}
            });
            btndates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(etdates.getText().toString().length()==0)
                    {
                        etdates.setError("Enter the date");
                        etdates.requestFocus();
                        return;
                    }
                    else
                    {
                        Bundle extras = getIntent().getExtras();
                        if (extras != null) {
                            String eid = extras.getString("key");
                        String edate = etdates.getText().toString();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(DatabaseHelper.AVAILABLE, edate);
                        contentValues.put(DatabaseHelper.EAEID, eid);
                        long id=db.insert(DatabaseHelper.EXAVAIL, null, contentValues);
                        Intent intent = new Intent(Exsubdates.this, Exsubdates.class);
                            Log.i("parent",eid);
                            intent.putExtra("key",eid);
                        startActivity(intent);
                        Toast.makeText(Exsubdates.this, "date added successfully", Toast.LENGTH_SHORT).show();
                    }

                }}
            });
            btnbckex = (Button) findViewById(R.id.btnbckex);
            btnbckex.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Exsubdates.this, Enroll.class);
                    startActivity(intent);

                }


            });


        }
    }

