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

public class Student extends AppCompatActivity {
    EditText etStudentName,etRollNo,etSeatNo,etdiv,etbtch;
    TextView tvStudentName,tvSeatNo,tvRollNo;
    Button btnAdd,btnBAck,btnexcel;
    RadioGroup radiogroup;
    RadioButton radbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        tvStudentName=(TextView)findViewById(R.id.tvStudentName);
        tvSeatNo=(TextView)findViewById(R.id.tvSeatNo);
        tvRollNo=(TextView)findViewById(R.id.tvRollNo);
        etStudentName=(EditText)findViewById(R.id.etStudentName);
        etRollNo=(EditText) findViewById(R.id.etRollNo);
        etdiv=(EditText) findViewById(R.id.etdiv);
        etSeatNo=(EditText) findViewById(R.id.etSeatNo);
        etbtch = (EditText) findViewById(R.id.etbtch);
        btnexcel = (Button) findViewById(R.id.btnexcel);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnBAck = (Button) findViewById(R.id.btnBAck);
        radiogroup =(RadioGroup)findViewById(R.id.rgBack);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etStudentName.getText().toString().length()==0)
                {
                    etStudentName.setError("Enter Student name");
                    etStudentName.requestFocus();
                    return;
                }
                if (etSeatNo.getText().toString().length()==0) {
                    etSeatNo.setError("Enter Seatno");
                    etSeatNo.requestFocus();
                    return;
                }
                if (etRollNo.getText().toString().length()==0)
                {
                    etRollNo.setError("wrong password");
                    etRollNo.requestFocus();
                    return;
                }
                if (etdiv.getText().toString().length()==0) {
                    etdiv.setError("Enter division");
                    etdiv.requestFocus();
                    return;
                }
                if (etbtch.getText().toString().length()==0) {
                    etbtch.setError("Enter batch");
                    etbtch.requestFocus();
                    return;
                }

                else {
                    int radid = radiogroup.getCheckedRadioButtonId();
                    radbtn =(RadioButton)findViewById(radid);
                    String ena = etStudentName.getText().toString();
                String esno = etSeatNo.getText().toString();
                String ero = etRollNo.getText().toString();
                String edi = etdiv.getText().toString();
                String ebt = etbtch.getText().toString();
                    String rdb = radbtn.getText().toString();
                    addstudent(ena,esno,ero,edi,rdb,ebt);
                    Intent intent = new Intent(Student.this,Student.class);
                    startActivity(intent);


                }
            } });
        btnBAck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnBAck) {
                    Intent intent = new Intent(Student.this, Vip.class);
                    startActivity(intent);


                }
            } });
        btnexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnexcel) {
                    Intent intent = new Intent(Student.this, Converter.class);
                    startActivity(intent);


                }
            } });
    }
    public void addstudent(String ena,String esno,String ero,String edi,String rdb ,String ebt)
    {
        DatabaseHelper dbh =new DatabaseHelper(this);
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SNAME,ena);
        contentValues.put(DatabaseHelper.SEATNO, esno);
        contentValues.put(DatabaseHelper.ROLLNO, ero);
        contentValues.put(DatabaseHelper.DIV, edi);
        contentValues.put(DatabaseHelper.RKT, rdb);
        contentValues.put(DatabaseHelper.BATCHNO, ebt);
        long id = db.insert(DatabaseHelper.STUDENT, null, contentValues);
    }
}
