package com.example.tehhuzzlenut.ripebananas;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Drawer Toggle
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    //Custom Adapter
    ListView lst;
    String[] fruitName = {"Bananas", "Mango", "Onions"};
    String[] fruitExpDate = {"Date1","Date2","Date3"};
    Integer[] imgID = {R.drawable.bananas, R.drawable.mango, R.drawable.onions} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        lst = (ListView) findViewById(R.id.lst);

        CustomListView customListView = new CustomListView(this, fruitName, fruitExpDate, imgID);
        lst.setAdapter(customListView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
