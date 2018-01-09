package com.example.aniket.demogorgon;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class customadapter extends RecyclerView.Adapter<customadapter.viewh>{


    private ArrayList<listcontent> str;
    customadapter(ArrayList<listcontent> s)
    {
        str=s;
    }
    public class viewh extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView t1;
        ImageView t2;
        TextView t3;
        viewh(View v)
        {
            super(v);
            t1=v.findViewById(R.id.t1);
            t2=v.findViewById(R.id.t2);
            t3=v.findViewById(R.id.t3);
            t1.setOnClickListener(this);
            t2.setOnClickListener(this);
            t3.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int a=getAdapterPosition();
            ++a;
            Intent i=new Intent(view.getContext(),rescue.class);
            i.putExtra("id",a);
            view.getContext().startActivity(i);
        }
    }
    @Override
    public viewh onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitemvission,parent, false);
        viewh l=new viewh(v);
        return l;
    }

    @Override
    public void onBindViewHolder(viewh v, final int position) {
        try {
            v.t1.setText(str.get(position).element2);
            v.t2.setImageBitmap(BitmapFactory.decodeByteArray(str.get(position).d, 0, str.get(position).d.length));
            if(str.get(position).element3==0)
            {
                v.t3.setText("Save me");
            }
            else
            {
                v.t3.setText("Rescued me");
            }
        }
        catch (Exception e){}
    }
    @Override
    public int getItemCount() {
        try{
        return str.size();}
        catch (Exception e){return 0;}
    }
}
