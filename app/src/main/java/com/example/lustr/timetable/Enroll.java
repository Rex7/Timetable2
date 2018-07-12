package com.example.lustr.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enroll extends AppCompatActivity  {
    Button btnbatch, btnEx, btnint, btnsubject,btnven;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        btnbatch = (Button) findViewById(R.id.btnbatch);
        btnEx = (Button) findViewById(R.id.btnEx);
        btnint = (Button) findViewById(R.id.btnint);
        btnven = (Button) findViewById(R.id.btnven);
        btnsubject =(Button)findViewById(R.id.btnsubject);


        btnbatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnbatch) {
                    Intent intent = new Intent(Enroll.this, Student.class);
                    startActivity(intent);


                }
            } });
        btnEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnEx) {
                    Intent intent = new Intent(Enroll.this, External.class);
                    startActivity(intent);


                }
            } });
        btnint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnint) {
                    Intent intent = new Intent(Enroll.this, Internal.class);
                    startActivity(intent);


                }
            } });
        btnven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnven) {
                    Intent intent = new Intent(Enroll.this, Venues.class);
                    startActivity(intent);


                }
            } });
        btnsubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btnsubject) {
                    Intent intent = new Intent(Enroll.this, Subject.class);
                    startActivity(intent);


                }
            } });





    }
    }
