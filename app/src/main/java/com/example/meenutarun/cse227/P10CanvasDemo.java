package com.example.meenutarun.cse227;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class P10CanvasDemo extends AppCompatActivity {
    ImageView iv;
    Canvas mycanvas;
    Paint mypaint;
    Bitmap mybitmap;  // represent the pixel that shown on display

    int distance =120;
    int mydistance = distance;
    int mulyiplier =100;

    P10CustomView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //either this 1.setContentView(R.layout.activity_p10_canvas_demo);
// or   2. cv = new P10CustomView(this);

        setContentView(R.layout.activity_p10_canvas_demo);

       //cv = new P10CustomView(this);
        //setContentView(cv);

        iv =(ImageView) findViewById(R.id.iv);
        mypaint = new Paint();
    }

    public void drawthis(View view)
    {
        int width  = view.getWidth();
        int height =  view.getHeight();

        int halfwidth = width/2;
        int halfheight = height/2;

        //createBitmap() method Returns a mutable bitmap with the specified width and height.
        //Bitmap.Config.ARGB_8888: in this Each pixel is stored on 4 bytes.
        mybitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        iv.setImageBitmap(mybitmap);

        mycanvas = new Canvas(mybitmap);
        mycanvas.drawColor(Color.RED);

       Paint p = new Paint(Paint.UNDERLINE_TEXT_FLAG);
        mycanvas.drawText("Keep Clicking", 100,100,p);
        p.setTextSize(50);

     Paint p1 = new Paint(Paint.STRIKE_THRU_TEXT_FLAG);
        mycanvas.drawText("Strike Line", 100,200,p1);
        p1.setTextSize(50);
    }
}

