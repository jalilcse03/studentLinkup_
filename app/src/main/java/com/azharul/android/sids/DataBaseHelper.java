package com.azharul.android.sids;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Azharul on 3/15/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

////////////////////////////////DataBase-Name And Version Constant ///////////////////////////////////////////////////
    static final String DATABASE_NAME="studentInformationDatabase";
    static final int DATABASE_VERSION=1;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static final String COL_ID="id"; //primary key
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static final String COL_STUDENT_ID ="studentID";
    static final String COL_NAME="name";
    static final String COL_DEPT="dept";
    static final String COL_CREDITS="credits";
    static final String COL_WEAVER="weaver";
    static final String COL_BALANCE="balance";
    static final String COL_CELLPHONE="cellphone";
    static final String COL_EMAIL="email";
    static final String COL_DOB="dob";
    static final String COL_BLOODGROUP="bloodGroup";
    static final String COL_GENDER="gender";
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    static final String TABLE_STUDENT_INFO="studentInfo";//DataBase table Name for Student Information
////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////// DataBase Initialize  Here /////////////////////////////////////////////////////////////


    String CREATE_TABLE_STUDENT= " CREATE TABLE " + TABLE_STUDENT_INFO + " ( "+ COL_ID + " INTEGER PRIMARY KEY, "+ COL_STUDENT_ID + " TEXT, " + COL_NAME + " TEXT, "+ COL_DEPT + " TEXT, "+ COL_CREDITS + " TEXT, "+ COL_WEAVER + " TEXT, "+ COL_BALANCE + " TEXT, "+ COL_CELLPHONE + " TEXT, "+COL_DOB + " TEXT, "+COL_BLOODGROUP + " TEXT, "+COL_GENDER + " TEXT, "+ COL_EMAIL + " TEXT )";

    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
////////////////////////////// DataBase Created Here //////////////////////////////////////////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);

    }
///////////////////////// DataBase update will control here ///////////////////////////////////////////////////////
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXIST " + TABLE_STUDENT_INFO);
        onCreate(db);
    }
}
