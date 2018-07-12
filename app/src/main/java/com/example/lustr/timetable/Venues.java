package com.example.lustr.timetable;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Venues extends AppCompatActivity {
    TextView tvlc,tvRoomNo,tvLc;
    EditText etlab;
    Button btnvsb,btnbckv;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    RadioGroup rglc;
    RadioButton rdbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venues);
        openHelper=new DatabaseHelper(this);
        db=openHelper.getWritableDatabase();
        tvlc = (TextView) findViewById(R.id.tvlc);
        tvRoomNo = (TextView) findViewById(R.id.tvRoomno);
        tvLc = (TextView) findViewById(R.id.tvlc);
        etlab = (EditText) findViewById(R.id.etlab);
        rglc =(RadioGroup)findViewById(R.id.rglc);

        btnvsb=(Button)findViewById(R.id.btnvsb);
        btnvsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etlab.getText().toString().length()==0)
                {
                    etlab.setError("Enter the lab or class");
                    etlab.requestFocus();
                    return;
                }
                else
                {
                    int radid = rglc.getCheckedRadioButtonId();
                    rdbtn =(RadioButton)findViewById(radid);
                    String elc = rdbtn.getText().toString();
                    String eno = etlab.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseHelper.ROOMNO, eno);
                    contentValues.put(DatabaseHelper.LABCLASS, elc);
                    db.insert(DatabaseHelper.VENUES, null, contentValues);
                    Intent i = new Intent(Venues.this,Venues.class);
                    startActivity(i);
                    Toast.makeText(Venues.this, "Lab and class no added successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnbckv = (Button) findViewById(R.id.btnbckv);
        btnbckv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Venues.this, Enroll.class);
                startActivity(intent);

            }


        });


    }
}
