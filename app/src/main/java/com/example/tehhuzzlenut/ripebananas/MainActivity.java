package com.example.tehhuzzlenut.ripebananas;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

    //Buttons
    private Button addButton;

    //ArrayList of Foods
    final ArrayList<Food> foodsList = new ArrayList<>();

    //Database Helper
    DatabaseHelper db;

    private static final String TAG = "MainActivity";

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

        //Database Helper setup
        db = new DatabaseHelper(MainActivity.this);


        //Set up ListView
//        lst = (ListView) findViewById(R.id.lst);
//        //LayoutInflater layoutInflater = getLayoutInflater();
//        foodsList.add(new Food(R.drawable.bananas, "Bananas" , "date"));
//        mAdapter = new CustomListView(this,foodsList);
//        lst.setAdapter(mAdapter);
        populateListView();


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
                                String name = foodsList.get(position).getmName();
                                foodsList.remove(position);
                                mAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, name + " removed from list.", Toast.LENGTH_SHORT).show();
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

        //Button to add item to list - send to new activity to add to the list, update list with info
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddFood.class);
                int RESULT_CODE = 10;
                startActivityForResult(i, RESULT_CODE);
            }
        });

        //Receive intent upon returning to main activity from Add food
        //Intent receivedIntent = getIntent();

        //checkIntent(receivedIntent);

    }

    //Populate listview
    private void populateListView() {
//        Log.d(TAG, "populateListView: Displaying data in the ListView.");
//        Log.d(TAG, "populateListView: got database.");
//        Cursor data = db.getData();
//        if(data == null){
//            Log.d(TAG, "populateListView: NOTHING HERE.");
//            Toast.makeText(MainActivity.this, "NOTHING HERE", Toast.LENGTH_SHORT).show();
//        }
//        while(data.moveToNext()){
//            //Get the value form the database and add to Arraylist
//            foodsList.add(new Food(R.drawable.foodplaceholder, data.getString(1), data.getString(2)));
//        }

        //Create listview
        //lst = (ListView) findViewById(R.id.lst);
        //LayoutInflater layoutInflater = getLayoutInflater();

        lst = (ListView) findViewById(R.id.lst);
        foodsList.add(new Food(R.drawable.bananas, "Bananas" , "date"));
        foodsList.add(new Food(R.drawable.mango, "Mangos" , "date"));
        foodsList.add(new Food(R.drawable.onions, "Onions" , "date"));
        mAdapter = new CustomListView(this,foodsList);
        lst.setAdapter(mAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
