package com.example.lustr.timetable;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Internal extends AppCompatActivity {
    EditText etinam,etieid,etipno,etibno;
    TextView tvid,tvicno,tvibd,tvieid,tvinam;
    Button btnibk,btnisb;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        openHelper=new DatabaseHelper(this);
        etinam = (EditText) findViewById(R.id.etinam);
        etieid = (EditText) findViewById(R.id.etieid);
        etipno = (EditText) findViewById(R.id.etipno);
        etibno = (EditText) findViewById(R.id.etibno);
        tvid = (TextView) findViewById(R.id.tvid);
        tvicno = (TextView) findViewById(R.id.tvicno);
        tvibd = (TextView) findViewById(R.id.tvibd);
        tvieid= (TextView) findViewById(R.id.tvieid);
        tvinam = (TextView) findViewById(R.id.tvinam);

        btnibk = (Button) findViewById(R.id.btnibk);
        btnisb = (Button) findViewById(R.id.btnisb);



        btnisb.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (etinam.getText().toString().length() == 0) {
                                              etinam.setError("Enter the name");
                                              etinam.requestFocus();
                                              return;
                                          }

                                          if (etieid.getText().toString().length() == 0) {
                                              etieid.setError("Enter E-mail id");
                                              etieid.requestFocus();
                                              return;
                                          }
                                          if (etipno.getText().toString().length() == 0) {
                                              etipno.setError("Enter contact number");
                                              etipno.requestFocus();
                                              return;
                                          }
                                          if (etibno.getText().toString().length() == 0) {
                                              etibno.setError("Enter bank a/c number");
                                              etibno.requestFocus();
                                              return;
                                          } else {
                                              db = openHelper.getWritableDatabase();
                                              String nam = etinam.getText().toString();
                                              String eid = etieid.getText().toString();
                                              String pno = etipno.getText().toString();
                                              String bno = etibno.getText().toString();
                                              insertdata(nam, eid, pno, bno);
                                              Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();

                                          }


                                      }

                                      public void insertdata(String nam, String eid, String pno, String bno) {
                                          ContentValues contentValues = new ContentValues();
                                          contentValues.put(DatabaseHelper.INAME, nam);
                                          contentValues.put(DatabaseHelper.IEMAILID, eid);
                                          contentValues.put(DatabaseHelper.ICONTACTNO, pno);
                                          contentValues.put(DatabaseHelper.IBANKD, bno);
                                          long id = db.insert(DatabaseHelper.INTERNAL, null, contentValues);
                                          Intent intent = new Intent(Internal.this, Insub.class);
                                          String iid =Long.toString(id);
                                          intent.putExtra("key1",iid);
                                          startActivity(intent);
                                      }
                                  });

        btnibk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Internal.this, Enroll.class);
                startActivity(intent);

            }


        });
    }}



