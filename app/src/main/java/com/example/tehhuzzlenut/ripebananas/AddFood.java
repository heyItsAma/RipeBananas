package com.example.tehhuzzlenut.ripebananas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddFood extends AppCompatActivity {

    private static final String TAG = "AddFood";

    //Buttons
    private Button addFoodButton;
    private Button cancelButton;

    //Database
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        //Receive intent
        Intent receivedIntent = getIntent();

        //Cancel Button Setup
        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddFood.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Add button setup
        addFoodButton = (Button) findViewById(R.id.addFoodButton);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFood();
            }
        });
    }

    private void addNewFood() {
        EditText foodName = (EditText) findViewById(R.id.ETfoodName);
        EditText foodExpDate = (EditText) findViewById(R.id.ETfoodExpDate);

        if(foodName.toString().isEmpty() == true || foodExpDate.toString().isEmpty() == true){
            Toast.makeText(AddFood.this, "Please fill out the other parameters!", Toast.LENGTH_SHORT).show();
        } else {
            String nameOfFood = foodName.getText().toString();
            Log.d(TAG, "addNewFood: name of food = " + nameOfFood);
            String expOfFood = foodExpDate.getText().toString();
            Log.d(TAG, "addNewFood: exp date = " + expOfFood);

            //Put data in database
            db = new DatabaseHelper(AddFood.this);
            boolean insertData = db.addData(nameOfFood, expOfFood);

            if (insertData){
                Toast.makeText(AddFood.this, "Food successfully stored!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddFood.this, "Something went wrong!", Toast.LENGTH_LONG).show();
            }

            Intent i = new Intent(AddFood.this, MainActivity.class);
            //i.putExtra("FoodNameKey", nameOfFood);
            //i.putExtra("FoodExpKey", expOfFood);
            startActivity(i);
        }

    }

}
