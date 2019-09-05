package com.example.meenutarun.cse227;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by MeenuTarun on 6/7/2019.
 */

public class P2MySurfaceView extends SurfaceView implements Runnable {

    boolean isrunning;
    Thread mthread;

    Context ct;
    SurfaceHolder sh;
    Paint mpaint;


    Bitmap bitmap;
    int mx = 0, my;

    Canvas canvas;

    public P2MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public P2MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {

        Log.d("Tag", "inside run");
        while (isrunning) {
            try {
                Thread.sleep(200);
                canvas = sh.lockCanvas();

                onDraw(canvas);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    sh.unlockCanvasAndPost(canvas);
                    //Each class which is derived from the View class has
                    // the invalidate and the postInvalidate method. If invalidate
                    // gets called it tells the system that the current view has
                    // changed and it should be redrawn as soon as possible.
                    // As this method can only be called from your UIThread
                    // another method is needed for when you are not in the UIThread
                    // and still want to notify the system that your View has been changed.
                    postInvalidate();

                }
            }

        }
    }

    void init(Context ct) {

        this.ct = ct;
        mpaint = new Paint();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Toast.makeText(ct, "Inside init", Toast.LENGTH_SHORT).show();

        sh = getHolder();

        sh.addCallback(new SurfaceHolder.Callback() {
            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

                //canvas = sh.lockCanvas();
                //onDraw(canvas);
                //sh.unlockCanvasAndPost(canvas);

               my = getHeight();
                //    resume();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                stop1();
            }
        });

    }

    void resume1() {
        isrunning = true;
        mthread = new Thread(this);
        mthread.start();
    }

    void stop1() {
        isrunning = false;
        try {
            //Joining Threads in Java. java.lang.Thread class provides the
            // join() method which allows one thread to wait until another
            // thread completes its execution. If t is a Thread object whose
            // thread is currently executing, then t.join() will make sure
            // that t is terminated before the next instruction is executed
            // by the program.
            mthread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, mx, my, mpaint);
       /* mx = mx + 10;
        if (mx >= getWidth()) {
            mx = 0;
        }*/

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //ACTION_DOWN means when we click and our mouse or pad is in down position.
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            mx  = (int)event.getX();
            my = (int) event.getY();
            resume1();
        }

        return true;
    }
}
