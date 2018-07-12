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

public class External extends AppCompatActivity {
    EditText etna,etcnam,eteid,etpno,etebno;
    TextView tved,tvcno,tvbno,tveid,tvexn,tvcollgn;
    Button btnebk,btnsmb;
    SQLiteDatabase db ;
    SQLiteOpenHelper openHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        openHelper=new DatabaseHelper(this);
        etna = (EditText) findViewById(R.id.etcnam);
        eteid = (EditText) findViewById(R.id.eteid);
        etpno = (EditText) findViewById(R.id.etpno);
        etebno = (EditText) findViewById(R.id.etebno);
        etcnam = (EditText) findViewById(R.id.etna);


        tved = (TextView) findViewById(R.id.tved);
        tvcno = (TextView) findViewById(R.id.tvcno);
        tvbno = (TextView) findViewById(R.id.tvbno);
        tveid= (TextView) findViewById(R.id.tvieid);
        tvcollgn = (TextView) findViewById(R.id.tvcollgn);


        btnebk = (Button) findViewById(R.id.btnebk);
        btnsmb = (Button) findViewById(R.id.btnsmb);



        btnsmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etna.getText().toString().length() == 0) {
                    etna.setError("Enter the name");
                    etna.requestFocus();
                    return;
                }
                if (etcnam.getText().toString().length() == 0) {
                    etcnam.setError("Enter college name");
                    etcnam.requestFocus();
                    return;
                }
                if (eteid.getText().toString().length() == 0) {
                    eteid.setError("Enter E-mail id");
                    eteid.requestFocus();
                    return;
                }
                if (etpno.getText().toString().length() == 0) {
                    etpno.setError("Enter contact number");
                    etpno.requestFocus();
                    return;
                }
                if (etebno.getText().toString().length() == 0) {
                    etebno.setError("Enter bank a/c number");
                    etebno.requestFocus();
                    return;

                } else {
                    db = openHelper.getWritableDatabase();
                    String enam = etna.getText().toString();
                    String ecnam = etcnam.getText().toString();
                    String eeid = eteid.getText().toString();
                    String epno = etpno.getText().toString();
                    String ebno = etebno.getText().toString();
                    insertedata(enam, ecnam, eeid, epno, ebno);
                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
                }

            }
                public void insertedata (String nam, String cnam, String eid, String pno, String bno)
                {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseHelper.ENAME, nam);
                    contentValues.put(DatabaseHelper.CLGNAME, cnam);
                    contentValues.put(DatabaseHelper.EEMAILID, eid);
                    contentValues.put(DatabaseHelper.ECONTACTNO, pno);
                    contentValues.put(DatabaseHelper.EBNO, bno);
                    long id = db.insert(DatabaseHelper.EXTERNAL, null, contentValues);
                    String eeid =Long.toString(id);
                    Intent intent = new Intent(External.this, Exsubdates.class);
                    intent.putExtra("key",eeid);
                    startActivity(intent);
                }


             });
        btnebk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(External.this, Enroll.class);
                startActivity(intent);

            }


            });




    }}
