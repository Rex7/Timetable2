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

public class Insub extends AppCompatActivity {
    TextView tvinsd, tvinsub;
    EditText etinsub;
    Button btninsub,btnbcki;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isub);
        openHelper=new DatabaseHelper(this);
        db=openHelper.getWritableDatabase();
        tvinsd = (TextView) findViewById(R.id.tvinsd);
        tvinsub = (TextView) findViewById(R.id.tvinsub);
        etinsub = (EditText) findViewById(R.id.etinsub);
        btninsub = (Button) findViewById(R.id.btninsub);
        btnbcki = (Button) findViewById(R.id.btnbcki);
        btninsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etinsub.getText().toString().length() == 0) {
                    etinsub.setError("Enter the subject");
                    etinsub.requestFocus();
                    return;
                } else {
                    Bundle extras = getIntent().getExtras();
                    if (extras != null) {
                        String iid = extras.getString("key1");
                    String isub = etinsub.getText().toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DatabaseHelper.ISUB, isub);
                    contentValues.put(DatabaseHelper.IID, iid);
                    db.insert(DatabaseHelper.INSUB, null, contentValues);
                    Intent intent = new Intent(Insub.this, Insub.class);
                        intent.putExtra("key1",iid);
                    startActivity(intent);
                    Toast.makeText(Insub.this, "Subject added successfully", Toast.LENGTH_SHORT).show();
                }

            }}
        });
        btnbcki = (Button) findViewById(R.id.btnbcki);
        btnbcki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Insub.this, Enroll.class);
                startActivity(intent);

            }


        });
    }

}
