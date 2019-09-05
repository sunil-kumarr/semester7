package com.example.meenutarun.cse227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class P3RecyclerView extends AppCompatActivity {
    RecyclerView r1;
    ArrayList<P3Person> al;
    P3MyAdapter md;

    EditText et1,et2;
    Button b;

    P3DataBase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3_recycler_view);
        r1 =(RecyclerView) findViewById(R.id.rc);
        et1 = (EditText) findViewById(R.id.etname);
        et2 = (EditText) findViewById(R.id.etdesc);


        myDatabase = new P3DataBase(this);

        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);

        r1.setLayoutManager(rlm);

        al = new ArrayList<>();

        r1.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

    }

    public void dothis(View v)
    {
        P3Person p = new P3Person();
        p.setName(et1.getText().toString());
        p.setDesc(et2.getText().toString());
        //   p.setImage(R.drawable.lpu);

        al.add(p);
        myDatabase.insertValues(p);

        md = new P3MyAdapter(this,myDatabase.showValues());

        r1.setAdapter(md);
    }
}
