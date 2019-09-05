package com.example.meenutarun.cse227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
// JSON stands for JavaScript Object Notation.
// It is an independent data exchange format and is the best alternative for XML(Extensible Markup Language is a markup language that defines a set of rules for encoding documents in a format that is both human-readable and machine-readable.)

//JSON,is a minimal, readable format for structuring data. It is used primarily to transmit data between a server and web application.

//Advantage of JSON over XML
  //      1) JSON is faster and easier than xml for AJAX applications.
//        2) Unlike XML, it is shorter and quicker to read and write.
//        3) It uses array.

//myjson.com server

//json object
 // A JSON object contains key/value pairs like map.
// The keys are strings and the values are the JSON types.
// Keys and values are separated by :(coln) . The { (curly brace) represents the json object.
//The [ (square bracket) represents the json array.

public class P5JSonDemo extends AppCompatActivity {

    public static final String JSON_STRING=
            "{\"employee\":{\"name\":\"Sachin\",\"salary\":56000}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_json_demo);
        TextView textView1=(TextView)findViewById(R.id.textView1);

        try{
            // Create the root JSONObject from the JSON string.
            //JSONObject  jsonRootObject = new JSONObject(JSON_STRING)

            //Get the instance of JSONArray that contains JSONObjects
            //JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");

            JSONObject emp=(new JSONObject(JSON_STRING)).getJSONObject("employee");
            String empname=emp.getString("name");
            int empsalary=emp.getInt("salary");

            String str="Employee Name:"+empname+"\n"+"Employee Salary:"+empsalary;
            textView1.setText(str);

        }catch (Exception e) {e.printStackTrace();}

    }

}

