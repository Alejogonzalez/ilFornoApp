package com.alejandrogonzalezv.ilforno;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class mapa extends ActionBarActivity {
    private GoogleMap map;
    private CameraUpdate cameraUpdate;

    private final LatLng LOCATION_HOME = new LatLng(6.260768,-75.601676);
    private final LatLng LOCATION_CITY = new LatLng(6.247899,-75.576239);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        map.addMarker(new MarkerOptions()
                .position(LOCATION_HOME)
                .title("My Home")
                .snippet("Where the maginc Happens")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(LOCATION_CITY, 8);
        map.animateCamera(cameraUpdate);
    }
    public void onClick(View view){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(LOCATION_HOME, 16);
        map.animateCamera(cameraUpdate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
