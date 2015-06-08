package com.alejandrogonzalezv.ilforno;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {
    private static DataBaseManager Manager;
    private ViewPager viewPager;
    LinearLayout page1;
    LinearLayout page2;
    LinearLayout page3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Manager = new DataBaseManager(this);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new MyPageAdapter());
    }

    class MyPageAdapter extends PagerAdapter {
        public int getCount(){
            return 3;
        }
        public Object instantiateItem(ViewGroup container, int position){
            View page = null;
            switch (position){
                case 0: if (page1==null)
                    page1 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_uno,null);
                    page = page1;
                    break;

                case 1: if (page2==null)
                    page2 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_dos,null);
                    page = page2;
                    break;

                default: if (page3==null)
                    page3 = (LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.fragment_tres,null);
                    page = page3;
                    break;
            }
            ((ViewPager) container).addView(page,0);
            return page;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view==o;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }

    public static DataBaseManager getManager() {
        return Manager;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.actmapa) {
            Intent m = new Intent(this,mapa.class);
            startActivity(m);
            return true;
        }
        if (id == R.id.actform) {
            Intent f = new Intent(this,formulario.class);
            startActivity(f);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
