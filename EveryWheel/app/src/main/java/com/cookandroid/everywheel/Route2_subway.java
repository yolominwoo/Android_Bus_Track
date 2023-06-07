package com.cookandroid.everywheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Route2_subway extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_subway2);

        // 뒤로가기 버튼 클릭
        Button bb6= (Button) findViewById(R.id.bb6);
        bb6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TransitActivity.class);
                startActivity(intent);
            }
        });

        // 안내시작 버튼 클릭
        Button start_guide6= (Button) findViewById(R.id.start_guide6);
        start_guide6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TransitActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
