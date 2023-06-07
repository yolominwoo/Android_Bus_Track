package com.cookandroid.everywheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_transit);

        // 뒤로가기 버튼 클릭
        Button back_main1 = (Button) findViewById(R.id.back_main1);
        back_main1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        // 출발지, 도착지 표시
        TextView route_start = (TextView) findViewById(R.id.route_start);
        TextView route_end = (TextView) findViewById(R.id.route_end);

        LinearLayout route_all = (LinearLayout) findViewById(R.id.route_all);
        LinearLayout route_bus = (LinearLayout) findViewById(R.id.route_bus);
        LinearLayout route_subway = (LinearLayout) findViewById(R.id.route_subway);
        //route_all.setSelected(true);




        // 전체 버튼 클릭
        RadioButton transit_all = (RadioButton) findViewById(R.id.transit_all);
        transit_all.setChecked(true);
        transit_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route_all.setVisibility(View.VISIBLE);
                route_bus.setVisibility(View.GONE);
                route_subway.setVisibility(View.GONE);
            }
        });

        // 버스 버튼 클릭
        RadioButton transit_bus = (RadioButton) findViewById(R.id.transit_bus);
        transit_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route_all.setVisibility(View.GONE);
                route_bus.setVisibility(View.VISIBLE);
                route_subway.setVisibility(View.GONE);
            }
        });

        // 지하철 버튼 클릭
        RadioButton transit_subway = (RadioButton) findViewById(R.id.transit_subway);
        transit_subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                route_all.setVisibility(View.GONE);
                route_bus.setVisibility(View.GONE);
                route_subway.setVisibility(View.VISIBLE);
            }
        });


        RadioButton route1_all = (RadioButton) findViewById(R.id.route1_all);
        RadioButton route2_all = (RadioButton) findViewById(R.id.route2_all);
        RadioButton route1_bus = (RadioButton) findViewById(R.id.route1_bus);
        RadioButton route2_bus = (RadioButton) findViewById(R.id.route2_bus);
        RadioButton route1_subway = (RadioButton) findViewById(R.id.route1_subway);
        RadioButton route2_subway = (RadioButton) findViewById(R.id.route2_subway);

        route1_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route2_all.setChecked(false);
                route1_bus.setChecked(false);
                route2_bus.setChecked(false);
                route1_subway.setChecked(false);
                route2_subway.setChecked(false);
            }
        });
        route2_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route1_all.setChecked(false);
                route1_bus.setChecked(false);
                route2_bus.setChecked(false);
                route1_subway.setChecked(false);
                route2_subway.setChecked(false);
            }
        });
        route1_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route1_all.setChecked(false);
                route2_all.setChecked(false);
                route2_bus.setChecked(false);
                route1_subway.setChecked(false);
                route2_subway.setChecked(false);
            }
        });
        route2_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route1_all.setChecked(false);
                route2_all.setChecked(false);
                route1_bus.setChecked(false);
                route1_subway.setChecked(false);
                route2_subway.setChecked(false);
            }
        });
        route1_subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route1_all.setChecked(false);
                route2_all.setChecked(false);
                route1_bus.setChecked(false);
                route2_bus.setChecked(false);
                route2_subway.setChecked(false);
            }
        });
        route2_subway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route1_all.setChecked(false);
                route2_all.setChecked(false);
                route1_bus.setChecked(false);
                route2_bus.setChecked(false);
                route1_subway.setChecked(false);
            }
        });


        // 경로 선택 버튼 클릭
        Button select_route = (Button) findViewById(R.id.select_route);
        select_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(route1_all.isChecked()){

                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RouteActivity.class);
                    startActivity(intent);
                }
                else if (route2_all.isChecked()) {
                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Route2_all.class);
                    startActivity(intent);
                }
                else if (route1_bus.isChecked()) {
                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Route1_bus.class);
                    startActivity(intent);
                }
                else if (route2_bus.isChecked()) {
                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Route2_bus.class);
                    startActivity(intent);
                }
                else if (route1_subway.isChecked()) {
                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Route1_subway.class);
                    startActivity(intent);
                }
                else if (route2_subway.isChecked()) {
                    Toast.makeText(getApplicationContext(), "경로를 선택했습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Route2_subway.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "경로를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
