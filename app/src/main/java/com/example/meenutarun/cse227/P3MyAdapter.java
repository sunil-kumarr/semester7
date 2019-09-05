package com.example.meenutarun.cse227;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MeenuTarun on 6/7/2019.
 */

public class P3MyAdapter extends RecyclerView.Adapter<P3MyAdapter.MyHolder> {

    Context ct;
    ArrayList<P3Person> al;

    P3DataBase myDatabase;


    P3MyAdapter(Context ct1, ArrayList al1)
    {
        ct = ct1;
        al = al1;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater li = LayoutInflater.from(ct);
        View v = li.inflate(R.layout.p3mylayout,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        final P3Person p1 = al.get(position);

        holder.tv1.setText(p1.getName());
        holder.tv2.setText(p1.getDesc());

        // holder.iv.setImageResource(p1.getImage());
        holder.ll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ct,"Clicked "+ position,Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public int getItemCount() {return al.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv1,tv2;
        ImageView iv;
        LinearLayout ll;


        public MyHolder(View itemView) {
            super(itemView);

            tv1 = (TextView)itemView.findViewById(R.id.tv1);
            tv2 = (TextView)itemView.findViewById(R.id.tv2);

            iv = (ImageView)itemView.findViewById(R.id.iv);

            ll = (LinearLayout)itemView.findViewById(R.id.ll);


        }

    }

}

