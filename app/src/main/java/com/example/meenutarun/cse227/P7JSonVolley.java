package com.example.meenutarun.cse227;
// add permission in manifest
//<uses-permission android:name="android.permission.INTERNET"/>
// to create createJson Object ion URL
//go to http://myjson.com/api then there createJson Object

//Volley is a networking library for Android that manages network requests.
// The RequestQueue manages worker threads for running the network operations,
// reading from and writing to the cache, and parsing responses

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

//import com.google.android.gms.common.api.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;*/
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.xml.transform.ErrorListener;

public class P7JSonVolley extends AppCompatActivity {
    ListView lv;
    Button b;
   // RequestQueue rq;

    ArrayAdapter<String> adapter;
    ArrayList<String> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p7_json_volley);
     /*   lv= (ListView) findViewById(R.id.lv);
        b = (Button) findViewById(R.id.but1);
        rq = Volley.newRequestQueue(this);

        al = new ArrayList<>();
    }


    public void dothis(View V)
    {
        String url = "https://api.myjson.com/bins/16u4it";

        //JsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener)
        //Creates a new request.

        JsonObjectRequest jreq = new JsonObjectRequest(Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jarr = response.getJSONArray("student");
                            for(int i=0; i<jarr.length();i++)
                            {
                                JSONObject stu = jarr.getJSONObject(i);
                                String name = stu.getString("name");
                                long mob = stu.getLong("mobno");
                                String s = name + " "+mob;

                                al.add(s);
                                adapter = new ArrayAdapter<String>(P7JSonVolley.this,android.R.layout.simple_list_item_1,al);
                                lv.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }

        );

        rq.add(jreq);*/
    }
}

