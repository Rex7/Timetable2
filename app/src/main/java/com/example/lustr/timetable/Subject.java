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
import android.widget.TextView;
import android.widget.Toast;

public class Subject extends AppCompatActivity {
    TextView tvrsubl,tvsubject,tvpo,tvmarks;
    EditText etsname,etpo,etmarks;
    Button btnsuba,btnbcksu;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        openHelper=new DatabaseHelper(this);
        tvrsubl=(TextView) findViewById(R.id.tvrsubl);
        tvpo=(TextView) findViewById(R.id.tvpo);
        tvmarks=(TextView) findViewById(R.id.tvmarks);
        tvsubject=(TextView) findViewById(R.id.tvsubject);
        etpo=(EditText)findViewById(R.id.etpo);
        etsname=(EditText)findViewById(R.id.etsname);
        etmarks=(EditText)findViewById(R.id.etmarks);
        btnsuba=(Button)findViewById(R.id.btnReg);

        btnsuba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etsname.getText().toString().length()==0) {
                    etsname.setError("Enter Subject");
                    etsname.requestFocus();
                    return;
                }
                if (etpo.getText().toString().length()==0)
                {
                    etpo.setError("Enter practicals or oral");
                    etpo.requestFocus();
                    return;
                }
                if (etmarks.getText().toString().length()==0) {
                    etmarks.setError("Enter Marks");
                    etmarks.requestFocus();
                    return;
                }
                else {
                    db = openHelper.getWritableDatabase();
                    String sname = etsname.getText().toString();
                    String marks = etmarks.getText().toString();
                    String po = etpo.getText().toString();
                    insertdata(sname, marks, po);
                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Subject.this, Subject.class);
                    startActivity(intent);
                }

            }


        });
        btnbcksu= (Button) findViewById(R.id.btnbcksu);
        btnbcksu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Subject.this, Enroll.class);
                startActivity(intent);

            }


        });
    }
    public void insertdata(String sname, String marks, String po){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUB, sname);
        contentValues.put(DatabaseHelper.MARKS, marks);
        contentValues.put(DatabaseHelper.PO, po);
        long id = db.insert(DatabaseHelper.SUBJECT, null, contentValues);
    }

}




