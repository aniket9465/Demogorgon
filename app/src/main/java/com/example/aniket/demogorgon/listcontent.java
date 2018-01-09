package com.example.aniket.demogorgon;

import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;

public class listcontent {
    public int element1;
    public String element2;
    public int element3;
    public byte[] d;
    public listcontent(int a, String ele1, int b,byte[] c)
    {
        element1=a;
        element2=ele1;
        element3=b;
        d=c;
    }
}
