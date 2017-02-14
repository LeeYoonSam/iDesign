package com.bup.idesign.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.bup.idesign.R;
import com.bup.idesign.products.ProductAct;

public class ActIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_intro);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               moveMain();
            }
        }, 2000);
    }

    public void moveMain() {
//        Intent i = new Intent(ActIntro.this, ActMain.class);
        Intent i = new Intent(ActIntro.this, ProductAct.class);
        startActivity(i);

        finish();
    }
}
