package com.example.aniket.demogorgon;
import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.SQLData;
import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

import static android.graphics.BitmapFactory.decodeResource;

public class MainActivity extends AppCompatActivity {

    public Activity er = this;
    public int f=1;
    public int var=0;
    public customdialog cdd;
    public ByteArrayOutputStream stream = new ByteArrayOutputStream();
    byte[] image1;
    static String s = "ani";
    private SectionsPagerAdapter mSectionsPagerAdapter;                   //section pager adapter object
    private ViewPager mViewPager;
    public static customadapter ad;
    public static ArrayList<listcontent> ar = new ArrayList<listcontent>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ar.clear();
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());        //instantiate section pager object
            mViewPager = (ViewPager) findViewById(R.id.container);                                //initialize view pager object

            mViewPager.setAdapter(mSectionsPagerAdapter);                                         //setup view pager object with secton pager adapter
            String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

            if (EasyPermissions.hasPermissions(this, galleryPermissions)) {

            } else {
                EasyPermissions.requestPermissions(this, "Access for storage",
                        101, galleryPermissions);
            }
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);                           //create tablayout object and initialize it

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));        //merge tablayout with viewpager for page change during swipe
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));      //merge viewpager with tablayout for activation of view pager when tabbuttons are clickked
        }}
////////////////////////////////////////////////////////////////

    //subclass of fragment page adapter which handles giving information of fragments

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                PlaceholderFragment1 a = new PlaceholderFragment1();           //providing with an object for list view
                return a;
            } else {
                PlaceholderFragment2 a = new PlaceholderFragment2();            //providing with an object for people view

                return a;
            }

        }

        @Override
        public int getCount() {                                       //used to tell view pager how many subdivisions are present
            return 2;
        }
    }


////////////////////////////////////////////////////

    //implementation of the fragment for list

    public static class PlaceholderFragment1 extends Fragment {
        public PlaceholderFragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_1, container, false);
            RecyclerView v = (RecyclerView) (rootView.findViewById(R.id.listf1));
            SQLiteDatabase db;
            sqlhelper a = new sqlhelper(getActivity());
            db = a.getReadableDatabase();
            for(int ii=1;;++ii)
            {
            try {
                Cursor c = db.rawQuery("select * from dream where id = "+ii+" ", null);
                c.moveToFirst();
                do {

                    ar.add(new listcontent(c.getInt(c.getColumnIndex("id")), c.getString(c.getColumnIndex("nameofsaver")), c.getInt(c.getColumnIndex("rescuestatus")), (byte[]) (c.getBlob(c.getColumnIndex("image")))));
                    Log.i("tag", "//////////////////////////////////////////////////////////" + c.getInt(c.getColumnIndex("id")));
                } while (c.moveToNext());
                c.close();
            } catch (Exception e) {
                Log.i("tag", "/////////////////11111111111///////////////111//////////////////////////" + e);
                break;
            }
        }
            db.close();
            v.setLayoutManager(new LinearLayoutManager(getActivity()));
            ad = new customadapter(ar);
            v.setAdapter(ad);
            return rootView;
        }
    }

//////////////////////////////////////////////////////

    //implementation of fragment for people

    public static class PlaceholderFragment2 extends Fragment {

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_2, container, false);
            RecyclerView v = (RecyclerView) (rootView.findViewById(R.id.listf2));
            ArrayList<listitem2> ar = new ArrayList<listitem2>();
            SQLiteDatabase db;
            sqlhelper a = new sqlhelper(getActivity());
            db = a.getReadableDatabase();
            Cursor c = db.rawQuery("select * from people", null);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();

            if (c.moveToFirst()) {
                do {

                    switch (c.getInt(0)) {
                        case 1:
                            decodeResource(getResources(), R.drawable.eleven).compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            ar.add(new listitem2(getString(R.string.n1), getString(R.string.d1), byteArray, c.getInt(1), c.getInt(2)));
                            break;
                        case 2:
                            decodeResource(getResources(), R.drawable.nancy).compress(Bitmap.CompressFormat.PNG, 100, stream1);
                            byte[] byteArray2 = stream1.toByteArray();
                            ar.add(new listitem2(getString(R.string.n2), getString(R.string.d2), byteArray2, c.getInt(1), c.getInt(2)));
                            break;
                        case 3:
                            decodeResource(getResources(), R.drawable.joyce).compress(Bitmap.CompressFormat.PNG, 100, stream2);
                            byte[] byteArray1 = stream2.toByteArray();
                            ar.add(new listitem2(getString(R.string.n3), getString(R.string.d3), byteArray1, c.getInt(1), c.getInt(2)));
                            break;
                    }

                } while (c.moveToNext());
            }
            c.close();
            customadapter2 ad = new customadapter2(ar);
            v.setLayoutManager(new LinearLayoutManager(getActivity()));
            v.setAdapter(ad);
            db.close();
            return rootView;
        }
    }


//////////////////////////////////////////////////////


    public void adddream(View view) {

        cdd=new customdialog(this,this);
       cdd.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        cdd.show();

    }
    public void res(int c)
    {
        if(cdd.img==0||cdd.character==0)
        {
            Log.i("tag","/////////////////0"+c+"00/////////////////");
        }
        else
        {
            Log.i("tag","/////////////////0"+c+"00/////////////////");
            SQLiteDatabase db;
            sqlhelper a = new sqlhelper(MainActivity.this);
            db = a.getWritableDatabase();
            ContentValues cv = new ContentValues();
            switch(c){
                case 1:
            cv.put("nameofsaver", "Eleven");
                    ar.add(new listcontent(ar.size(),"Eleven" ,0, image1));

                    break;
                case 2:
                    cv.put("nameofsaver", "Nancy");
                    ar.add(new listcontent(ar.size(),"Nancy" ,0, image1));

                    break;
                case 3:
                    cv.put("nameofsaver", "Joyce");
                    ar.add(new listcontent(ar.size(),"Joyce" ,0, image1));

            }
            cv.put("rescuestatus", 0);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            cv.put("image", image1);
            db.insert("dream", null, cv);
            db.close();
            ad.notifyDataSetChanged();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        stream = new ByteArrayOutputStream();

        if (requestCode == 1) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image1 = stream.toByteArray();
            var=1;
            res(cdd.character);
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

                if (EasyPermissions.hasPermissions(this, galleryPermissions)) {

                } else {
                    EasyPermissions.requestPermissions(this, "Access for storage",
                            101, galleryPermissions);
                }
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                (BitmapFactory.decodeFile(picturePath)).compress(Bitmap.CompressFormat.JPEG, 5, stream);
                image1 = stream.toByteArray();
                res(cdd.character);}
        }
    }
}