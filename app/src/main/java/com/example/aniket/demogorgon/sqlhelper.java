package com.example.aniket.demogorgon;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import static android.content.ContentValues.TAG;


public class sqlhelper extends SQLiteOpenHelper {
    sqlhelper(Context a)
    {
        super(a,"demogorgon",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            Cursor c = db.rawQuery("select * from people", null);
        }
        catch(Exception e){
            db.execSQL("create table if not exists people (id integer primary key,numberofrescue integer,totalrescuerequests integer)");
            db.execSQL("Insert into people values (1,0,0)");
            db.execSQL("Insert into people values (2,0,0)");
            db.execSQL("Insert into people values (3,0,0)");
        }
        db.execSQL("create table if not exists dream (id integer  PRIMARY KEY autoincrement, nameofsaver text,rescuestatus integer,image blob)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
