package com.example.meenutarun.cse227;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by MeenuTarun on 6/7/2019.
 */

public class P3DataBase extends SQLiteOpenHelper {


    private static final int dbv =1;
    private static final String dbname = "mydb";

    Context ct;
    P3DataBase(Context c)
    {
        super(c,dbname,null,dbv);
        ct =c;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        Toast.makeText(ct,"IN Oncreate", Toast.LENGTH_SHORT).show();

        String s = "create table info (name text, description text )";

        sqLiteDatabase.execSQL(s);

    }

    public void insertValues(P3Person p)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("name", p.getName());
        cv.put("description",p.getDesc());
//        cv.put("image", p.getImage());

        db.insert("info",null, cv);

        Toast.makeText(ct,"Insert Completed", Toast.LENGTH_SHORT).show();
    }


    public ArrayList<P3Person> showValues()
    {
        Toast.makeText(ct,"Inside show values", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = getReadableDatabase();
        String s = "select * from info";
        Cursor cr = db.rawQuery(s, null);
        ArrayList<P3Person > al = new ArrayList<>();

        while (cr.moveToNext())
        {
            P3Person p = new P3Person();

            String s1 =cr.getString(0);
            String s2 = cr.getString(1);
            //          int a = cr.getInt(2);
            p.setName(s1);
            p.setDesc(s2);
            //        p.setImage(a);
            al.add(p);
        }
        return al;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
