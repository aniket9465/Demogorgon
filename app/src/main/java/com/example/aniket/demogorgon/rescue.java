package com.example.aniket.demogorgon;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class rescue extends AppCompatActivity implements View.OnClickListener {
    public  Cursor cu;
    public SQLiteDatabase db;
    public EditText et;
    public TextView t;
    public Button b;
    public int c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rescue);
        c=getIntent().getExtras().getInt("id");
        sqlhelper a = new sqlhelper(this);
        b=(Button)findViewById(R.id.but);
        et=(EditText)findViewById(R.id.et);
        b.setOnClickListener(this);
        t=(TextView)findViewById(R.id.t);
        db = a.getReadableDatabase();
       cu= db.rawQuery("select * from dream where id = "+c+" ", null);
        if(cu.moveToFirst()) {
            byte[] bytearr = (byte[]) (cu.getBlob(cu.getColumnIndex("image")));
            ImageView I = (ImageView) findViewById(R.id.imageup);
            I.setImageBitmap(BitmapFactory.decodeByteArray(bytearr, 0, bytearr.length));
        }
        if(cu.getInt(cu.getColumnIndex("rescuestatus"))==1)
        {
            et.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            t.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View view) {
        save();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cu.close();
    }
    public void save()
    {
        Log.i("hf",et.getText().toString()+"    "+cu.getString(cu.getColumnIndex("nameofsaver")));
        if((et.getText().toString()).equalsIgnoreCase(cu.getString(cu.getColumnIndex("nameofsaver"))))
        {
            MainActivity.ar.get(c-1).element3=1;
            Log.i("hf",et.getText().toString()+"    "+cu.getString(cu.getColumnIndex("nameofsaver")));
            ContentValues cv = new ContentValues();
            cv.put("rescuestatus","1");
            db.update("dream", cv, "id="+c, null);
            Log.i("hf",et.getText().toString()+"    "+cu.getString(cu.getColumnIndex("nameofsaver")));
            ContentValues cv1 = new ContentValues();
            if(cu.getString(cu.getColumnIndex("nameofsaver")).equals("Eleven"))
                c=1;
            if(cu.getString(cu.getColumnIndex("nameofsaver")).equals("Nancy"))
                    c=2;
            if(cu.getString(cu.getColumnIndex("nameofsaver")).equals("Joyce"))
                        c=3;
            Cursor cu2;
            cu2= db.rawQuery("select * from people where id = "+c+" ", null);
            cu2.moveToFirst();
            cv1.put("numberofrescue",cu2.getInt(cu2.getColumnIndex("numberofrescue"))+1);
            db.update("people", cv1, "id="+c, null);
            MainActivity.ad.notifyDataSetChanged();
            et.setVisibility(View.INVISIBLE);
            b.setVisibility(View.INVISIBLE);
            t.setVisibility(View.VISIBLE);

        }
        else
        {
            et.setText("");
            Toast.makeText(this,R.string.toast,Toast.LENGTH_LONG).show();
        }
    }
}