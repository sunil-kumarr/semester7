package com.example.meenutarun.cse227;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by MeenuTarun on 8/28/2019.
 */

public class P10CustomView extends View {

    Paint p;
    int x=100;
    public P10CustomView(Context context) {
        super(context);

        init();
    }

    public void init()
    {
        p = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Log.i("keyyy","onDraw calledddddd");
        canvas.drawColor(Color.RED);
        p.setColor(Color.YELLOW);
        //left,top,right,bottom
        canvas.drawRect(100,100,500,500,p);

        p.setColor(Color.GREEN);
        canvas.drawCircle(x,600,50,p);
        canvas.drawCircle(x,700,50,p);
        canvas.drawCircle(x,800,50,p);
       // canvas.drawArc(500,500,800,800,x,30,true,p);
        //canvas.drawArc(500,500,800,800,x+120,30,true,p);
        //canvas.drawArc(500,500,800,800, x+240,30,true,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startFan();
                break;
            case MotionEvent.ACTION_UP:
                stopFan();
                break;
        }
        return true;
    }

    void startFan()
    {
        x = x+5;
        invalidate();
    }
    void stopFan()
    {

    }
}
