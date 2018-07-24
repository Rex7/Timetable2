package com.example.lustr.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vip extends AppCompatActivity {
    Button btnadd,btnip,buttoback2,report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        btnadd=(Button) findViewById(R.id.btnadd);
        btnip=(Button) findViewById(R.id.btnip);
        report=(Button) findViewById(R.id.report);
        buttoback2=(Button) findViewById(R.id.buttoback2);

        btnip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vip.this,Enroll.class);
                startActivity(i);
            }
        });
        buttoback2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.buttoback2) {
                    Intent intent = new Intent(Vip.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vip.this,Signu.class);
                startActivity(i);
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vip.this,Schedule.class);
                startActivity(i);
            }
        });



    }
}
