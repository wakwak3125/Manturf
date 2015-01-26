package com.manturf.manturf;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.astuetz.PagerSlidingTabStrip;
import com.manturf.manturf.Fragments.Favorites;
import com.manturf.manturf.Fragments.Messeage;
import com.manturf.manturf.Fragments.Profile;
import com.manturf.manturf.Fragments.Timeline;
import com.manturf.manturf.Fragments.TopTimeLine;
import com.manturf.manturf.adapter.TabsPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements Messeage.OnFragmentInteractionListener,
        Timeline.OnFragmentInteractionListener,Favorites.OnFragmentInteractionListener,Profile.OnFragmentInteractionListener,TopTimeLine.OnFragmentInteractionListener{

    private DrawerLayout vDrawerLayout;
    private ActionBarDrawerToggle vDrawerToggle;

    ListView vListView;
    static List<String>items = new ArrayList<>();
    static ArrayAdapter<String>adapter;

    private void releaseView() {
        if (vListView != null) {
            vListView.setAdapter(null);
            vListView = null;
        }

        if (adapter != null) {
            adapter.clear();
            adapter = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vListView = (ListView)findViewById(R.id.listview);

        items.add("ホーム");
        items.add("なんか");
        items.add("なんか");
        items.add("なんか");

        adapter = new ArrayAdapter<>(this,R.layout.list_layout,R.id.item_title,items);
        vListView.setAdapter(adapter);


        // Initilization
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager()));

        // Adding Tabs
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        vDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initDrawer();

    }

    private void initDrawer(){
        vDrawerToggle = new ActionBarDrawerToggle(this, vDrawerLayout, R.string.app_name, R.string.app_name);
        vDrawerToggle.setDrawerIndicatorEnabled(true);
        vDrawerLayout.setDrawerListener(vDrawerToggle);

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
        if (id == R.id.action_settings) {
            return true;
        }
        return vDrawerToggle.onOptionsItemSelected(item)|| super.onOptionsItemSelected(item);
        }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        vDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        vDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
