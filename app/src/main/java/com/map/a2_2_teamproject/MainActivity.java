package com.map.a2_2_teamproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import net.daum.mf.map.api.CameraUpdateFactory;
import net.daum.mf.map.api.MapCircle;
import net.daum.mf.map.api.MapPOIItem;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPointBounds;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;



import static net.daum.mf.map.api.MapView.setMapTilePersistentCacheEnabled;
public class MainActivity extends AppCompatActivity implements MapView.MapViewEventListener, MapView.POIItemEventListener, MapView.OpenAPIKeyAuthenticationResultListener{
    double x;    // 위도 변수
    double y;   // 경도 변수
    int i = 0;
    Button up;
    Button down;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        //tab, viewpager연동
        TabLayout tab = findViewById(R.id.tab);
        tab.setupWithViewPager(viewPager);





        final MapView mapView = new MapView(this); // MapView 객체
        setMapTilePersistentCacheEnabled(true); // 한번 로드시 캐시에 저장
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view); // id 연결

        mapViewContainer.addView(mapView);

        Intent intent = getIntent();
        double a = intent.getDoubleExtra("위도1", 1);
        double b = intent.getDoubleExtra("경도1", 2);
        double c = intent.getDoubleExtra("위도2", 3);
        double d = intent.getDoubleExtra("경도2", 4);

        //marker 지정!!
        final MapPOIItem marker1 = new MapPOIItem();
        final MapPOIItem marker2 = new MapPOIItem();
        final MapPOIItem marker3 = new MapPOIItem();
        final MapPOIItem marker4 = new MapPOIItem();
        final MapPOIItem marker5 = new MapPOIItem();

        marker3.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven); // 하늘에서 날라오는 애니메이션 마커 생성 전에 선언 해야함.




        //마크1 지정!!!!!!!!!
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(a, b);   // 임의의 MapPoint 객체를 만듬
        marker1.setItemName("나의 위치");
        marker1.setTag(0);
        marker1.setMapPoint(mapPoint);
        marker1.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker1.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker1);
        mapView.selectPOIItem(marker1,true); // 말풍선 항상 노출 허용
        //마크1지정!!!!!!!!!
        /* custom marker
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(a, b);
        MapPOIItem customMarker = new MapPOIItem();
        customMarker.setItemName("나의 위치");
        customMarker.setTag(1);
        customMarker.setMapPoint(mapPoint);
        customMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage); // 마커타입을 커스텀 마커로 지정.
        customMarker.setCustomImageResourceId(R.drawable.marker1); // 마커 이미지.
        customMarker.setCustomImageAutoscale(false); // hdpi, xhdpi 등 안드로이드 플랫폼의 스케일을 사용할 경우 지도 라이브러리의 스케일 기능을 꺼줌.
        customMarker.setCustomImageAnchor(0.5f, 1.0f); // 마커 이미지중 기준이 되는 위치(앵커포인트) 지정 - 마커 이미지 좌측 상단 기준 x(0.0f ~ 1.0f), y(0.0f ~ 1.0f) 값.
        customMarker.setCustomImageAnchorPointOffset(new MapPOIItem.ImageOffset(30,0));
        mapView.addPOIItem(customMarker);
        */
        //마크2 지정!!!!!!!!!
        MapPoint mapPoint2 = MapPoint.mapPointWithGeoCoord(c, d);   // 임의의 MapPoint 객체를 만듬
        marker2.setItemName("친구 위치");
        marker2.setTag(1);
        marker2.setMapPoint(mapPoint2);
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker2);
        mapView.selectPOIItem(marker2,true); // 말풍선 항상 노출 허용
        //마크2지정!!!!!!!!!

        final double e = (a+c) /2;
        final double f = (b+d) /2;

        //마크3 지정!!!!!!!!!
        MapPoint mapPoint3 = MapPoint.mapPointWithGeoCoord(e, f);   // 임의의 MapPoint 객체를 만듬
        marker3.setItemName("중간 지점");
        marker3.setTag(1);
        marker3.setMapPoint(mapPoint3);
        marker3.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker3.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker3);
        mapView.selectPOIItem(marker3,true); // 말풍선 항상 노출 허용
        //마크3지정!!!!!!!!!


        //polyline!!!!!!!!!!!!
        MapPolyline polyline = new MapPolyline();
        polyline.setTag(1000);
        polyline.setLineColor(Color.argb(255, 255, 169, 77)); // Polyline 컬러 지정.

        // Polyline 좌표 지정.
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(a, b));
        polyline.addPoint(MapPoint.mapPointWithGeoCoord(c,d));
        // Polyline 지도에 올리기.
        mapView.addPolyline(polyline);

        // 지도뷰의 중심좌표와 줌레벨을 Polyline이 모두 나오도록 조정.
        MapPointBounds mapPointBounds = new MapPointBounds(polyline.getMapPoints());
        int padding = 150; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
        //polyline!!!!!!!!!!!!

        final int radi[] = {1000,2000,3000,4000,5000,10000};
        final int[] i = {2};
        final MapCircle circle1 = new MapCircle(
                MapPoint.mapPointWithGeoCoord(e,f), // center
                radi[i[0]], // radius
                Color.argb(255, 255, 169, 77), // strokeColor
                Color.argb(125, 173, 181, 189) // fillColor
        );
        circle1.setTag(1234);
        mapView.addCircle(circle1);



        /*지도뷰의 중심좌표와 줌레벨을 Circle이 모두 나오도록 조정. circle
        MapPointBounds[] mapPointBoundsArray = { circle1.getBound() };
        MapPointBounds mapPointBounds = new MapPointBounds(mapPointBoundsArray);
        int padding = 200; // px
        mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
        */

        back = findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, setAdress.class);
                mapView.removePOIItem(marker1);
                mapView.removePOIItem(marker2);
                mapView.removePOIItem(marker3);
                mapView.removeCircle(circle1);
                startActivity(intent);

            }
        });


        up = findViewById(R.id.btn_up);
        down = findViewById(R.id.btn_down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // 확장 버튼 구현
                i[0] = i[0] +1;
                if(i[0]<radi.length){
                    mapView.removeCircle(circle1);
                    circle1.setRadius(radi[i[0]]);
                    final MapCircle circle1 = new MapCircle(
                            MapPoint.mapPointWithGeoCoord(e,f), // center
                            radi[i[0]], // radius
                            Color.argb(255, 255, 169, 77), // strokeColor
                            Color.argb(125, 173, 181, 189) // fillColor
                    );
                    circle1.setTag(1234);
                    mapView.addCircle(circle1);


                    // 지도뷰의 중심좌표와 줌레벨을 Circle이 모두 나오도록 조정.
                    MapPointBounds[] mapPointBoundsArray = { circle1.getBound() };
                    MapPointBounds mapPointBounds = new MapPointBounds(mapPointBoundsArray);
                    int padding = 200; // px
                    mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
                }
                else{
                    Toast.makeText(getApplicationContext(),"최대 반경 입니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // 축소 버튼 구현
                i[0] = i[0] -1;
                if(i[0]>=0){
                    mapView.removeCircle(circle1);
                    circle1.setRadius(radi[i[0]]);
                    final MapCircle circle1 = new MapCircle(
                            MapPoint.mapPointWithGeoCoord(e,f), // center
                            radi[i[0]], // radius
                            Color.argb(255, 255, 169, 77), // strokeColor
                            Color.argb(125, 173, 181, 189) // fillColor
                    );
                    circle1.setTag(1234);
                    mapView.addCircle(circle1);


                    // 지도뷰의 중심좌표와 줌레벨을 Circle이 모두 나오도록 조정.
                    MapPointBounds[] mapPointBoundsArray = { circle1.getBound() };
                    MapPointBounds mapPointBounds = new MapPointBounds(mapPointBoundsArray);
                    int padding = 200; // px
                    mapView.moveCamera(CameraUpdateFactory.newMapPointBounds(mapPointBounds, padding));
                }
                else{
                    Toast.makeText(getApplicationContext(),"최소 반경 입니다!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mapView.setMapViewEventListener(this); // this에 MapView.MapViewEventListener 구현.
        mapView.setPOIItemEventListener(this);
        onMapViewInitialized(mapView);






    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint, MapPolyline polyline) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    @Override
    public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int i, String s) {

    }
}
