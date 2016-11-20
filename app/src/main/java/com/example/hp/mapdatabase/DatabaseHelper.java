package com.example.hp.mapdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper
{

    public static final String Database_Name = "WSA.db";
    public static final String Table_Name = "user";

    public static final String Col_1 = "Name";
    public static final String Col_2= "Email";
    public static final String Col_3 = "_Phone";
    public static final String Col_4 = "Sex";
    public static final String Col_5 = "Address";
    public static final String Col_6 = "Dob";
    public static final String Col_7= "Reg_Id";

    final String createTable = " create table " +Table_Name+ "( Name varchar(30), Email varchar(20), _Phone integer primary key, Sex varchar(10), Address varchar(20), Dob varchar(20), Reg_Id varchar(40) )";
    static SQLiteDatabase db;
    public DatabaseHelper(Context context)
    {
        super(context, Database_Name, null, 1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(createTable);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST " +Table_Name);
        onCreate(db);
    }

    public boolean insertData(String name, String email, String phone ,String sex, String address , String dob)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, name);
        contentValues.put(Col_2, email);
        contentValues.put(Col_3, phone);
        contentValues.put(Col_4, sex);
        contentValues.put(Col_5, address);
        contentValues.put(Col_6, dob);
        long result = db.insert( Table_Name , null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertRegId(String Reg_Id){

        Random r = new Random();


        String name="WSA_U";
        int id=1;

        Reg_Id= String.valueOf(r.nextInt(20-2)+"_"+name+id+"_"+2);
        //Reg_Id= name+id;
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col_7,Reg_Id);
        id++;
        long result = db.insert( Table_Name , null, contentValues);
        if(result == -1)
            return false;
        else {
            return true;
        }
    }

    public Cursor getAllData()
    {
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +Table_Name, null);
        return res;

    }
}
