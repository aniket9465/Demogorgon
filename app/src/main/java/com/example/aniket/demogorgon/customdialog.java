package com.example.aniket.demogorgon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class customdialog extends Dialog implements View.OnClickListener,AdapterView.OnItemSelectedListener
{
    public Spinner s;
    public Activity c;
    public MainActivity d;
    public int character;
    public int img;
    public byte[] image2,image1;
    public Button camera,gallery;
    public customdialog(Activity a,MainActivity s) {
        super(a);
        c=a;
        character=0;
        img=0;
        d=s;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.dialog);
        s=(Spinner)findViewById(R.id.spin);
        s.setOnItemSelectedListener(this);
        camera=(Button)findViewById(R.id.cam);
        gallery=(Button)findViewById(R.id.gal);
        camera.setOnClickListener(this);
        gallery.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cam:
                img=1;
                Intent i= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                d.startActivityForResult(i,1);
                break;
            case R.id.gal:
                img=1;
                Intent i2 = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                d.startActivityForResult(i2, 2);
    break;
        }
       dismiss();
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        character=pos+1;
    }
    public void onNothingSelected(AdapterView<?> parent) {
        character=1;
    }
}
