package com.example.tehhuzzlenut.ripebananas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TehHuzzlenut on 12/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "food_table";

    //Table Details
    private static final String ColFoodID = "ID";
    private static final String ColFoodName = "foodName";
    private static final String ColFoodExpDate = "foodExpDate";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ColFoodName + " TEXT, " + ColFoodExpDate + " TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
    }

    public boolean addData(String item1, String item2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ColFoodName, item1);
        contentValues.put(ColFoodExpDate, item2);

        Log.d(TAG, "addData: Adding " + item1 + " and " + item2 + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //If data is inserted incorrectly, it will return
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Cursor to return database data
    public Cursor getData(){
        Log.d(TAG, "getData: got into function.");
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d(TAG, "getData: got database.");
        String query = "SELECT * FROM " + TABLE_NAME;
        Log.d(TAG, "getData: made query.");
        Cursor data = db.rawQuery(query, null);
        Log.d(TAG, "getData: made cursor and reutrn.");
        return data;
    }

    //Cursor to get item id
    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + ColFoodID + " FROM " + TABLE_NAME +
                " WHERE " + ColFoodName + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //Update name of item
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE '" + TABLE_NAME + "' WHERE " + ColFoodID +
                " = '" + id + "' " + " AND " + ColFoodName + " = '" +
                oldName + "'";
        db.execSQL(query);
    }

    //Delete food
    public void deleteFood(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + ColFoodID + " = '" + id + "' AND " + ColFoodName +
                " = '" + name + "'";
        db.execSQL(query);
    }
}
