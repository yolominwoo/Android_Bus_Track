package com.cookandroid.everywheel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class ProfiletoTaxi extends AppCompatActivity {
    Handler handler = new Handler();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_to_taxi);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), TaxiActivity.class);
                startActivity(intent);
            }
        },1000);
    }
}
