package com.example.lustr.timetable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etusername,etpass;
    Button btnlog;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etusername=(EditText)findViewById(R.id.etpo);
        etpass=(EditText)findViewById(R.id.etpass);
        btnlog=(Button)findViewById(R.id.btnlog);
        openHelper=new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        db=openHelper.getWritableDatabase();
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override

                public void onClick(View v) {
                    String username = etusername.getText().toString();
                    String pass = etpass.getText().toString();

                    cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.LOGIN  + " WHERE "+ DatabaseHelper.UNAME + "=? AND " + DatabaseHelper.PASS + "=?", new String[]{username,
                            pass});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,Vip.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(MainActivity.this,Vip.class);
                            startActivity(i);
                        }
                        }


            }

        });

            }}






