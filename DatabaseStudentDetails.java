package com.dinesh.sqldatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dinesh on 10/2/2016.
 */
public class DatabaseStudentDetails extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="Student.db";
    public static final String TABLE_NAME ="Student_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="FIRSTNAME";
    public static final String COL_3 ="LASTNAME";
    public static final String COL_4 ="ROLLNUMBER";
    public static final String COL_5 ="EMAILID";
    public static final String COL_6 ="GPA";
    public DatabaseStudentDetails(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT,FIRSTNAME TEXT,LASTNAME TEXT,ROLLNUMBER INTEGER,EMAILID TEXT,GPA INTEGER)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public boolean insertData(String firstname, String lastname, String rollnumber, String emailid, String gpa){
        SQLiteDatabase db = DatabaseStudentDetails.this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,firstname);
        contentValues.put(COL_3,lastname);
        contentValues.put(COL_4,rollnumber);
        contentValues.put(COL_5,emailid);
        contentValues.put(COL_6,gpa);
        long result= db.insert(TABLE_NAME,null, contentValues);
        if(result == -1){
            return false;
            }else
                return true;

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res= db.rawQuery(" Select * from " + TABLE_NAME, null);
        return res;
    }
    public  boolean updateData(String id, String firstname, String lastname, String rollnumber, String emailid, String gpa ){
        SQLiteDatabase db = DatabaseStudentDetails.this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1,id);
        values.put(COL_2,firstname);
        values.put(COL_3,lastname);
        values.put(COL_4,rollnumber);
        values.put(COL_5,emailid);
        values.put(COL_6,gpa);
        db.update(TABLE_NAME, values, "ID = ?", new String[] {id});
        return true;

    }
    public Integer deleteData(String id){
        SQLiteDatabase db = DatabaseStudentDetails.this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String [] {id});
    }


}
