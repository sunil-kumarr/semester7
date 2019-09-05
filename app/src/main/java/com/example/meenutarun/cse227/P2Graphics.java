package com.example.meenutarun.cse227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class P2Graphics extends AppCompatActivity {
    P2MySurfaceView msv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2_graphics);
        msv =(P2MySurfaceView) findViewById(R.id.sv);

   }

    @Override
    protected void onStop() {
        super.onStop();
        msv.stop1();
    }

}

