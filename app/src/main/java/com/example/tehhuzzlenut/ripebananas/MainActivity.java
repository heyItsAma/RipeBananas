package com.example.tehhuzzlenut.ripebananas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //Custom Adapter
    ListView lst;
    String[] fruitName = {"Bananas", "Mango", "Onions"};
    String[] fruitExpDate = {"Date1","Date2","Date3"};
    Integer[] imgID = {R.drawable.bananas, R.drawable.mango, R.drawable.onions} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lst = (ListView) findViewById(R.id.lst);

        CustomListView customListView = new CustomListView(this, fruitName, fruitExpDate, imgID);
        lst.setAdapter(customListView);
    }
}
