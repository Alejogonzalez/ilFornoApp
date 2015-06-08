package com.alejandrogonzalezv.ilforno;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    private Cursor cursor;

    private final LatLng LOCATION_CITY = new LatLng(6.247899,-75.576239);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(LOCATION_CITY, 11);
        map.animateCamera(cameraUpdate);
        cargarRest();
    }

    public void onClick(View view){
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(LOCATION_CITY, 11);
        map.animateCamera(cameraUpdate);
    }

    public void onClick1(View view){

    }

    public void cargarRest(){
        DataBaseManager Manager = MainActivity.getManager();
        cursor = Manager.cargarCursorContactos();
        if (cursor.moveToFirst()){
            do{
                String dbnombre = cursor.getString(cursor.getColumnIndex(Manager.CN_NAME)).toString();
                String dblatitud = cursor.getString(cursor.getColumnIndex(Manager.CN_LAT)).toString();
                String dblongitud = cursor.getString(cursor.getColumnIndex(Manager.CN_LONG)).toString();
                float lat = Float.parseFloat(dblatitud);
                float longitud = Float.parseFloat(dblongitud);
                final LatLng LOCATION_VAR = new LatLng(lat,longitud);
                Toast.makeText(getApplicationContext(), dbnombre, Toast.LENGTH_SHORT).show();
                map.addMarker(new MarkerOptions()
                        .position(LOCATION_VAR)
                        .title(dbnombre)
                        .snippet(dblatitud+", "+dblongitud)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }while (cursor.moveToNext());

        }
        else{
            Toast.makeText(getApplicationContext(), "no hay Restaurantes ", Toast.LENGTH_SHORT).show();
        }
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
        if (id == R.id.actform) {
            Intent f = new Intent(this,formulario.class);
            startActivity(f);
            return true;
        }
        if (id == R.id.actmain) {
            Intent m = new Intent(this,MainActivity.class);
            startActivity(m);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
