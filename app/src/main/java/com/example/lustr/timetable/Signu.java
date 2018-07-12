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

public class Signu extends AppCompatActivity {
TextView tvrusern,tvrsign,tvrpass,tvrname,tvpass;
EditText etusername,etspass,etuname;
Button btnreg,btnbcksi;
SQLiteOpenHelper openHelper;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        openHelper=new DatabaseHelper(this);
        tvrusern=(TextView) findViewById(R.id.tvrusern);
        tvrsign=(TextView) findViewById(R.id.tvrsign);
        tvrpass=(TextView) findViewById(R.id.tvmarks);
        tvrname=(TextView) findViewById(R.id.tvsubject);
        etusername=(EditText)findViewById(R.id.etpo);
        etuname=(EditText)findViewById(R.id.etsname);
        etspass=(EditText)findViewById(R.id.etspass);
        btnreg=(Button)findViewById(R.id.btnReg);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etusername.getText().toString().length()==0) {
                    etusername.setError("Enter Username");
                    etusername.requestFocus();
                    return;
                }
                if (etuname.getText().toString().length()==0) {
                    etuname.setError("Enter name");
                    etuname.requestFocus();
                    return;
                }
                if (etspass.getText().toString().length()==0) {
                    etspass.setError("Enter password");
                    etspass.requestFocus();
                    return;
                }
                db=openHelper.getWritableDatabase();
                String username = etusername.getText().toString();
                String uname= etuname.getText().toString();
                String spass= etspass.getText().toString();
                insertdata(uname, username,spass);
                Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Signu.this, Signu.class);
                startActivity(intent);

            }


        });
        btnbcksi = (Button) findViewById(R.id.btnbcksi);
        btnbcksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signu.this, Enroll.class);
                startActivity(intent);

            }


        });
        }
        public void insertdata(String etuname, String etusername, String etspass){
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.NAME, etuname);
                contentValues.put(DatabaseHelper.UNAME, etusername);
                contentValues.put(DatabaseHelper.PASS, etspass);
                long id = db.insert(DatabaseHelper.LOGIN, null, contentValues);
            }

        }




