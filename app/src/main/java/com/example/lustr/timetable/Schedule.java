package com.example.lustr.timetable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;


public class Schedule extends AppCompatActivity {
    Button btnsched;
    SQLiteDatabase db;
    Cursor cs;
    String[][] sc = new String[11][31];
    String[][] ex = new String[2][30];
    String[][] es = new String[2][30];
    String[][] ve = new String[3][20];
    String[][] is = new String[3][20];
    int xj[] = new int[30];
    int xv[] = new int[30];
    String csub;
    int jb, a, s, i, j, k, l;
    String csb, cmarks, cpo, cb, ceid, cdate;
    final String subjects = " SELECT * FROM " + DatabaseHelper.SUBJECT;
    final String exavail = " SELECT * FROM " + DatabaseHelper.EXAVAIL;
    final String exsub = " SELECT * FROM " + DatabaseHelper.EXSUB;
    final String venue = "SELECT * FROM " + DatabaseHelper.VENUES;
    final String insub = "SELECT * FROM " + DatabaseHelper.INSUB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        db = openOrCreateDatabase(DatabaseHelper.DATABASE_NAME, MODE_PRIVATE, null);
        btnsched = (Button) findViewById(R.id.btnsce);

        btnsched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cs = db.rawQuery(subjects, null);
                i = -1;
                while (i < 29) {
                    sc[1][i + 1] = "B1";
                    sc[1][i + 2] = "B2";
                    sc[1][i + 3] = "B3";
                    sc[1][i + 4] = "B4";
                    sc[1][i + 5] = "B5";
                    sc[1][i + 6] = "B6";
                    i = i + 6;
                }
                i = 0;
                while (i < 30) {
                    cs.moveToFirst();

                    if (cs.moveToFirst()) {
                        do {
                            int r = 0;
                            csub = cs.getString(1);
                            cmarks = cs.getString(2);
                            cpo = cs.getString(3);
                            while (r < 6) {
                                sc[2][i] = csub;
                                sc[4][i] = cmarks;
                                sc[5][i] = cpo;
                                i = i + 1;
                                r = r + 1;
                            }
                        } while (cs.moveToNext());
                    }
                }
                i = 0;
                cs.close();
                cs = db.rawQuery(exavail, null);
                if (cs.moveToFirst()) {
                    do {
                        ex[0][i] = cs.getString(1);
                        ex[1][i] = cs.getString(2);
                        i = i + 1;
                    } while (cs.moveToNext());

                }
                cs.close();
                s = 0;
                cs = db.rawQuery(exsub, null);
                if (cs.moveToFirst()) {
                    do {
                        es[0][s] = cs.getString(1);
                        es[1][s] = cs.getString(2);
                        s = s + 1;
                    } while (cs.moveToNext());

                }
                cs.close();
                int lp = 0;
                int count;
                while (lp < 1) {
                    a = 0;
                    while (a < 30) {
                        csb = sc[2][a];
                        cb = sc[1][a];
                        i = 0;
                        loopi:
                        while (i < s) {
                            if (csb.equals(es[1][i])) {
                                ceid = es[0][i];//matching eid by subject
                                j = 0;
                                loopj:
                                while (j < 30) {
                                    if (ceid.equals(ex[0][j])) {
                                        cdate = ex[1][j];//geting date
                                        jb = j;
                                        k = 0;
                                        loopk:
                                        do {
                                            if (k < a) {
                                                if (cb.equals(sc[1][k])) {//same batch
                                                    if (cdate.equals(sc[0][k])) {//same batch date
                                                        break loopk;
                                                    }
                                                }
                                                k = k + 1;
                                            } else {
                                                if (xj[jb] != 1) {
                                                    xj[jb] = 1;
                                                    sc[3][a] = ceid;
                                                    sc[0][a] = cdate;
                                                    break loopi;
                                                }
                                                break loopk;
                                            }

                                        } while (k <= a);
                                    }


                                    j = j + 1;
                                }
                            }
                            i = i + 1;
                        }

                        a = a + 1;
                    }//a loop

                    l = 0;
                    count = 0;
                    while (l < 30) {
                        if (xj[l] == 1) {

                            count = count + 1;
                        }

                        l = l + 1;
                    }//l loop
                    if (count > 28) {
                        lp = lp + 1;
                    } else {
                        shuffle();
                    }
                }
                for (i = 0; i < 30; i++) {
                    Log.i("jamesm", "fixed batch +" + sc[1][i]);
                    Log.i("jamesm", "fixed sub +" + sc[2][i]);
                    Log.i("jamesm", "fixed id +" + sc[3][i]);
                    Log.i("jamesm", "fixed date +" + sc[0][i]);

                }
                make();
            }
        });
    }

    protected void shuffle() {
        Random rnd = new Random();
        for (i = 29; i > 0; i--) {
            int index = rnd.nextInt(i);
            String a = ex[0][index];
            ex[0][index] = ex[0][i];
            ex[0][i] = a;
            a = ex[1][index];
            ex[1][index] = ex[1][i];
            ex[1][i] = a;
            xj[i] = 0;
        }

    }
    protected void ishuffle() {
        Random rnd = new Random();
        for (i = 29; i > 0; i--) {
            int index = rnd.nextInt(i);
            String a = is[0][index];
            is[0][index] = is[0][i];
            is[0][i] = a;
            a = is[1][index];
            is[1][index] = is[1][i];
            is[1][i] = a;
            xv[i] = 0;
        }

    }


    protected void make() {
        i = 0;
        cs = db.rawQuery(venue, null);
        if (cs.moveToFirst()) {
            do {
                ve[0][i] = cs.getString(1);
                ve[1][i] = cs.getString(2);
                i = i + 1;
            } while (cs.moveToNext());

        }
        cs.close();
        int vl = i - 1;
        i = 0;
        j = 0;
        k = 0;
        int xvv;
        int kk;
        int kj;
        String cv, cve;
        while (i < 30) {//i fr all blocks
            cve = sc[0][i];//current date
            if (sc[4][i].equals("oral")) {//oral then class
                loopjj:
                while (j <= vl) {
                    if (ve[1][j].equals("Class")) {
                        cv = ve[0][j];//current roomno
                        kj = 0;
                        loopkj:
                        do {
                            if (kj < i) {
                                if (cv.equals(sc[6][kj])) {
                                    if (cve.equals(sc[0][kj])) {//sam
                                        break loopkj;
                                    }
                                }
                                kj = kj + 1;
                            } else {
                                    sc[6][i] = cv;
                                    break loopjj;

                            }
                        } while (kj <= i);
                    }
                    j = j + 1;
                }
            } else {
                loopjj1:
                while (k <= vl) {
                    if (ve[1][k].equals("Lab")) {
                        cv = ve[0][k];//current roomno
                        kk = 0;
                        loopkk1:
                        do {
                            if (kk < i) {
                                if (cv.equals(sc[6][kk])) {
                                    if (cve.equals(sc[0][kk])) {//sam
                                        break loopkk1;
                                    }
                                }
                                kk = kk + 1;
                            } else {
                                    sc[6][i] = cv;
                                    break loopjj1;
                            }
                        } while (kk <= i);
                    }
                    k = k + 1;
                }
            }
           i = i + 1;
        }


        i = 0;
        cs = db.rawQuery(insub, null);
        if (cs.moveToFirst()) {
            do {
                is[0][i] = cs.getString(1);
                Log.i("iidd", is[0][i]);
                is[1][i] = cs.getString(2);
                Log.i("iidd", is[1][i]);
                i = i + 1;
            } while (cs.moveToNext());

        }
        cs.close();
        int jk = i;
        int count;
        int lp=0;
        while(lp<1)
        {
            i = 0;
            while (i < 30) {
            csub = sc[2][i];
            cb = sc[0][i];
            j = 0;
            loopj:
            while (j < jk) {
                if (csub.equals(is[0][j])) {
                    ceid = is[1][j];//matching iid by subject
                    k = 0;
                    xvv=j;
                    loopk:
                    do {
                        if (k < i) {
                            if (ceid.equals(sc[7][k])) {
                                Log.i("jamerr", sc[7][k] + k);
                                if (cb.equals(sc[0][k])) {//sam
                                    break loopk;
                                }
                            }
                            k = k + 1;
                        } else {
                                xv[xvv] = 1;
                                sc[7][i] = ceid;
                                break loopj;
                        }
                    } while (k <= i);

                }
                j = j + 1;
            }
            i = i + 1;
        }
            l = 0;
            count = 0;
            while (l < 30) {
                if (xj[l] == 1) {
                    count = count + 1;
                }
                l = l + 1;
            }
        if (count > 28) {
            lp = lp + 1;
        } else {
            ishuffle();
        }
    }
        for(i=0;i<30;i++){
            Log.i("jam123", "subject +" + sc[2][i]);
            Log.i("jam12", "marks +" + sc[4][i]);
            Log.i("jam12", "po +" + sc[5][i]);
            Log.i("jam1236", "venue +" + sc[0][i]+sc[7][i]);
            Log.i("jam1235", "iid +" + sc[7][i]);
            Log.i("jamsch", sc[0][i]+"++" +sc[1][i]+ "++" +
            sc[2][i]+"++" + sc[3][i]+"++" + sc[4][i]+"++" +sc[5][i]+"++" +sc[6][i]+"++" +sc[7][i]);
        }






    }}