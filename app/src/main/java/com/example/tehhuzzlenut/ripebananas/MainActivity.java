package com.example.tehhuzzlenut.ripebananas;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //Drawer Toggle
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    //Custom Adapter
    ListView lst;
    private CustomListView mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set up navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Set up ListView
        lst = (ListView) findViewById(R.id.lst);
        LayoutInflater layoutInflater = getLayoutInflater();
        final ArrayList<Food> foodsList = new ArrayList<>();
        foodsList.add(new Food(R.drawable.bananas, "Bananas" , "date"));
        mAdapter = new CustomListView(this,foodsList);
        lst.setAdapter(mAdapter);


        //Long click listener for listview - Delete an item
        lst.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int position = i;
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Consume Food")
                        .setMessage("Have you already consumed this food? If so, hit yes to delete.")
                        .setPositiveButton("Yes, it's all gone.", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                foodsList.remove(position);
                                mAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Removed from list.", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No, it's still in my pantry.", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return false;
            }
        });

        //Button to add item to list
        
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
