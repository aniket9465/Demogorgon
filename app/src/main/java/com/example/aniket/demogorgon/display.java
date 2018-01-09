package com.example.aniket.demogorgon;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dell on 03/01/2018.
 */

public class display extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("display", "//////////////////////////////////////////////////");
        setContentView(R.layout.character);
        Log.d("display", "//////////////////////////////////////////////////");
        int c=getIntent().getExtras().getInt("position");
        Log.d("display", c+"//////////////////////////////////////////////////");
        SQLiteDatabase db;
        sqlhelper a = new sqlhelper(this);
        db = a.getReadableDatabase();
        Cursor cu;
        cu= db.rawQuery("select * from people where id = "+c+" ", null);

        Log.d("display", c+"//////////////////////////////////////////////////"+cu.moveToFirst());

        String s="Number of rescue : "+cu.getInt(cu.getColumnIndex("numberofrescue"));
        ((TextView)findViewById(R.id.nres)).setText(s);
        switch (c)
        {
            case 1:
                ((ImageView)findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.eleven));
                ((TextView)findViewById(R.id.txt)).setText(R.string.d1);
                ((TextView)findViewById(R.id.name)).setText(R.string.n1);
                break;
            case 2:
                ((ImageView)findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.nancy));
                ((TextView)findViewById(R.id.txt)).setText(R.string.d2);
                ((TextView)findViewById(R.id.name)).setText(R.string.n2);
                break;
            case 3:
                ((ImageView)findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.joyce));
                ((TextView)findViewById(R.id.txt)).setText(R.string.d3);
                ((TextView)findViewById(R.id.name)).setText(R.string.n3);
                break;
        }
    }
}
