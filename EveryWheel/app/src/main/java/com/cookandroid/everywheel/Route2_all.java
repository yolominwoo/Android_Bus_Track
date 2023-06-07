package com.cookandroid.everywheel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Route2_all extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    TextView bus01;

    SwipeRefreshLayout swipeRefreshLayout;
    String key ="QWI%2BIX9Zr4CUcS85K2bULpsff1B4B9t15pBOV6Z%2FRSurR1nqp4wAL%2F6pVUN3STofXK1qx2Vrie7BMSwm%2Fmx0fw%3D%3D";
    String data01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_transit2);

        bus01 = (TextView)findViewById(R.id.show01Info);

        // 뒤로가기 버튼 클릭
        Button bb2= (Button) findViewById(R.id.bb2);
        bb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TransitActivity.class);
                startActivity(intent);
            }
        });

        // 안내시작 버튼 클릭
        Button start_guide2= (Button) findViewById(R.id.start_guide2);
        start_guide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
                startActivity(intent);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                data01 = get01Data();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bus01.setText(data01);
                    }
                });
            }
        }).start();


        swipeRefreshLayout = findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
    }
    String get01Data() {
        StringBuffer buffer = new StringBuffer();

        String queryUrl="http://ws.bus.go.kr/api/rest/arrive/getArrInfoByRoute?"
                +"&stId=101000117&busRouteId=100100001&ord=6&serviceKey=" + key;

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
                            buffer.append("첫번째 시간 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                        }
                        else if(tag.equals("arrmsg2")){
                            buffer.append("    두번째 버스 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
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

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                data01 = get01Data();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bus01.setText(data01);
                    }
                });
            }
        }).start();

        swipeRefreshLayout.setRefreshing(false);
        ClickHandler();
    }
    public void ClickHandler()
    {
        Toast myToast = Toast.makeText(this.getApplicationContext(), R.string.TOAST_MESSAGE, Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TransitActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

}
