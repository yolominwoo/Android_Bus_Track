package com.cookandroid.everywheel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    String key ="QWI%2BIX9Zr4CUcS85K2bULpsff1B4B9t15pBOV6Z%2FRSurR1nqp4wAL%2F6pVUN3STofXK1qx2Vrie7BMSwm%2Fmx0fw%3D%3D";
    String data6702, data11, data600, data7019;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        // 검색하기 버튼 클릭
        Button search_route = (Button) findViewById(R.id.search_route);
        search_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton ride_transit = (RadioButton) findViewById(R.id.ride_transit);
                RadioButton ride_taxi = (RadioButton) findViewById(R.id.ride_taxi);

                if (ride_transit.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), TransitActivity.class);
                    startActivity(intent);
                }
                else if (ride_taxi.isChecked()) {
                    Intent intent = new Intent(getApplicationContext(), TaxiActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "선택하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?serviceKey=QWI%2BIX9Zr4CUcS85K2bULpsff1B4B9t15pBOV6Z%2FRSurR1nqp4wAL%2F6pVUN3STofXK1qx2Vrie7BMSwm%2Fmx0fw%3D%3D&stId=101900012&busRouteId=100100573&ord=19





        // 버스 즐겨찾기
        ListView bus_bookmark1 = (ListView) findViewById(R.id.bus_bookmark1);
        List<String> data1 = new ArrayList<>();
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data1);
        bus_bookmark1.setAdapter(adapter1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                data6702 = get6702Data();
                data11 = get11Data();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data1.add(data6702);
                        data1.add(data11);
                        adapter1.notifyDataSetChanged();
                    }
                });
            }
        }).start();


        ListView bus_bookmark2 = (ListView) findViewById(R.id.bus_bookmark2);
        List<String> data2 = new ArrayList<>();
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2);
        bus_bookmark2.setAdapter(adapter2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                data600 = get600Data();
                data7019 = get7019Data();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data2.add(data600);
                        data2.add(data7019);
                        adapter2.notifyDataSetChanged();
                    }
                });
            }
        }).start();
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }




    }

    //http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?serviceKey=QWI%2BIX9Zr4CUcS85K2bULpsff1B4B9t15pBOV6Z%2FRSurR1nqp4wAL%2F6pVUN3STofXK1qx2Vrie7BMSwm%2Fmx0fw%3D%3D&stId=101900012&busRouteId=100100573&ord=19
    String get6702Data() {
        StringBuffer buffer = new StringBuffer();

        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?"
                +"&stId=101900012&busRouteId=100100573&ord=19&serviceKey=" + key;

//        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getLowArrInfoByRoute?"
//                +"&stId=100000007&busRouteId=100100185&ord=13&serviceKey=" + key;
        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("itemList")) ;// 첫번째 검색결과
                        else if(tag.equals("arrmsg1")){
                            buffer.append("6702 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                        }


                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("itemList"));// 첫번째 검색결과종료..줄바꿈

                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }

    String get11Data() {
        StringBuffer buffer = new StringBuffer();

        //http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?serviceKey=QWI%2BIX9Zr4CUcS85K2bULpsff1B4B9t15pBOV6Z%2FRSurR1nqp4wAL%2F6pVUN3STofXK1qx2Vrie7BMSwm%2Fmx0fw%3D%3D&stId=101900012&busRouteId=100900007&ord=11

        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?"
                +"&stId=101900012&busRouteId=100900007&ord=11&serviceKey=" + key;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("itemList")) ;// 첫번째 검색결과
                        else if(tag.equals("arrmsg1")){
                            buffer.append("종로11 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                        }



                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("itemList"));// 첫번째 검색결과종료..줄바꿈

                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
    String get7019Data() {
        StringBuffer buffer = new StringBuffer();

        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?"
                +"&stId=100000025&busRouteId=100100085&ord=36&serviceKey=" + key;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("itemList")) ;// 첫번째 검색결과
                        else if(tag.equals("arrmsg1")){
                            buffer.append("600 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                        }



                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("itemList"));// 첫번째 검색결과종료..줄바꿈

                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }
    String get600Data() {
        StringBuffer buffer = new StringBuffer();

        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?"
                +"&stId=100000025&busRouteId=100100339&ord=44&serviceKey=" + key;

        try {
            URL url= new URL(queryUrl);//문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType= xpp.getEventType();

            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT:
                        buffer.append("파싱 시작...\n\n");
                        break;

                    case XmlPullParser.START_TAG:
                        tag= xpp.getName();//태그 이름 얻어오기

                        if(tag.equals("itemList")) ;// 첫번째 검색결과
                        else if(tag.equals("arrmsg1")){
                            buffer.append("7019 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                        }



                    case XmlPullParser.TEXT:
                        break;
                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("itemList"));// 첫번째 검색결과종료..줄바꿈

                        break;
                }
                eventType= xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        return buffer.toString();//StringBuffer 문자열 객체 반환
    }

}