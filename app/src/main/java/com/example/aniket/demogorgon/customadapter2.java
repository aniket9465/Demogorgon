package com.example.aniket.demogorgon;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aniket.demogorgon.listcontent;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class customadapter2 extends RecyclerView.Adapter<com.example.aniket.demogorgon.customadapter2.viewh> {


    private ArrayList<listitem2> str;
    customadapter2(ArrayList<listitem2> s)
    {
        str=s;
    }
    public class viewh extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView t1;
        ImageView t2;
        viewh(View v)
        {
            super(v);
            t1=v.findViewById(R.id.t1);
            t2=v.findViewById(R.id.t2);
            t1.setOnClickListener(this);
            t2.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int a=getAdapterPosition();
            ++a;
            Intent i=new Intent(view.getContext(),display.class);
            i.putExtra("position",a);
            view.getContext().startActivity(i);
        }
    }

    @Override
    public com.example.aniket.demogorgon.customadapter2.viewh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem,parent, false);
        com.example.aniket.demogorgon.customadapter2.viewh l=new com.example.aniket.demogorgon.customadapter2.viewh(v);
        return l;
    }

    @Override
    public void onBindViewHolder(com.example.aniket.demogorgon.customadapter2.viewh v, final int position) {
        v.t1.setText(str.get(position).element1);
        v.t2.setImageBitmap(BitmapFactory.decodeByteArray(str.get(position).d,0,str.get(position).d.length));
    }
    @Override
    public int getItemCount() {
        return str.size();
    }
}