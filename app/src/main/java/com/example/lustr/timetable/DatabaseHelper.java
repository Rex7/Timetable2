package com.example.lustr.timetable;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="timetable.db";
    public static final String LOGIN ="login";
    public static final String EXTERNAL="external";
    public static final String INTERNAL="internal";
    public static final String VENUES="venues";
    public static final String STUDENT="Student";
    public static final String EXAVAIL="exavail";
    public static final String EXSUB="exsub";
    public static final String INSUB="insub";
    public static final String SUBJECT="subject";


    public static final String ID="id";
    public static final String NAME="name";
    public static final String UNAME ="username";
    public static final String PASS="password";

    public static final String EID="eid";
    public static final String EAEID="eaeid";
    public static final String ESEID="eseid";
    public static final String ENAME="ename";
    public static final String CLGNAME ="eclgname";
    public static final String ECONTACTNO="econtactno";
    public static final String EEMAILID="eemailid";
    public static final String EBNO="ebno ";


    public static final String IID="iid";
    public static final String INAME="iname";
    public static final String ICONTACTNO="icontactno";
    public static final String IEMAILID="iemailid";
    public static final String IBANKD="ibankd";

    public static final String RID="rid";
    public static final String ROOMNO="roomno";
    public static final String LABCLASS="labclass";

    public static final String ROLLNO="rollno";
    public static final String SNAME="sname";
    public static final String SEATNO ="seatno";
    public static final String DIV="div";
    public static final String BATCHNO="batchno";
    public static final String RKT="rkt";


    public static final String EAID="eaid";
    public static final String AVAILABLE ="available";

    public static final String ESID="esid";
    public static final String ESUB="sub";

    public static final String ISID="isid";
    public static final String ISUB="sub";

    public static final String SUB="sub";
    public static final String SUBID="subid";
    public static final String PO="po";
    public static final String MARKS="marks";

    public static final String CREATE_LOGIN_TABLE =" CREATE TABLE " + LOGIN +"(" + ID + " INTEGER PRIMARY KEY ,"+ NAME +" TEXT,"+ UNAME +" TEXT ,"+ PASS + " TEXT )";
    public static final String CREATE_EXTERNAL_TABLE =" CREATE TABLE " + EXTERNAL +" (" + EID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+ ENAME +" TEXT ,"+ CLGNAME +" TEXT ,"+ ECONTACTNO +" INTEGER,"+ EEMAILID +" TEXT,"+ EBNO +" TEXT)";
    public static final String CREATE_INTERNAL_TABLE =" CREATE TABLE " + INTERNAL +"(" + IID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"+INAME+" TEXT,"+ICONTACTNO+" TEXT,"+IEMAILID+" TEXT,"+IBANKD+" TEXT )";
    public static final String CREATE_VENUES_TABLE =" CREATE TABLE " + VENUES +" ("+ RID +" PRIMARY KEY AUTOINCREMENT ,"+ ROOMNO +"TEXT,"+LABCLASS+" TEXT )";
    public static final String CREATE_STUDENT_TABLE =" CREATE TABLE " + STUDENT +"("+ ROLLNO +" TEXT PRIMARY KEY ,"+SNAME+" TEXT,"+SEATNO+" TEXT,"+DIV+" TEXT,"+BATCHNO+" TEXT ,"+RKT+" TEXT)";
    public static final String CREATE_EXAVAIL_TABLE =" CREATE TABLE " + EXAVAIL +" ("+ EAID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+EAEID+" INTEGER,"+ AVAILABLE +" INTEGER , FOREIGN KEY (" + EAEID + ")  REFERENCES  "+EXTERNAL + "("+EID+")" +")";
    public static final String CREATE_EXSUB_TABLE =" CREATE TABLE " + EXSUB +" ("+ ESID +" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ ESEID +" INTEGER,"+ ESUB +" TEXT , FOREIGN KEY (" + ESEID + ")   REFERENCES  "+EXTERNAL + "("+EID+")" +")";
    public static final String CREATE_INSUB_TABLE =" CREATE TABLE " + INSUB +" ("+ ISID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+ISUB+" TEXT,"+IID+" INTEGER, FOREIGN KEY (" + IID + ")  REFERENCES  "+INTERNAL + "(iid) "+ ")";
    public static final String CREATE_SUB_TABLE =" CREATE TABLE " + SUBJECT +" ("+SUBID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+ SUB +" TEXT ,"+PO+" TEXT,"+ MARKS+" INTEGER )";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_EXTERNAL_TABLE);
        db.execSQL(CREATE_INTERNAL_TABLE);
        db.execSQL(CREATE_VENUES_TABLE);
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_EXAVAIL_TABLE);
        db.execSQL(CREATE_INSUB_TABLE);
        db.execSQL(CREATE_SUB_TABLE);
        db.execSQL(CREATE_EXSUB_TABLE);
        db.execSQL("INSERT INTO " + LOGIN +"("+NAME+","+UNAME+","+PASS+") VALUES('administrator','admin','admin123')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + LOGIN);
        db.execSQL("DROP TABLE IF EXISTS " + INTERNAL);
        db.execSQL("DROP TABLE IF EXISTS " + EXTERNAL);
        db.execSQL("DROP TABLE IF EXISTS " + EXAVAIL);
        db.execSQL("DROP TABLE IF EXISTS " + EXSUB);
        db.execSQL("DROP TABLE IF EXISTS " + VENUES);
        db.execSQL("DROP TABLE IF EXISTS " + INSUB);
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + STUDENT);
      onCreate(db);
    }

}