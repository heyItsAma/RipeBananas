package com.example.tehhuzzlenut.ripebananas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TehHuzzlenut on 11/29/2017.
 */

public class CustomListView extends ArrayAdapter<Food>{

    //Class for custom view adapter of listview

    private Context mContext;
    private List<Food> foodsList = new ArrayList<>();

    public CustomListView(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Food> list) {
        super(context, 0 , list);
        mContext = context;
        foodsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_layout,parent,false);

        Food currentFood = foodsList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.foodImageView);
        image.setImageResource(currentFood.getmImageDrawable());

        TextView name = (TextView) listItem.findViewById(R.id.tvfoodName);
        name.setText(currentFood.getmName());

        TextView foodExpDate = (TextView) listItem.findViewById(R.id.tvFoodExpDate);
        foodExpDate.setText(currentFood.getmRelease());

        return listItem;
    }

    public void removeItem(int position) {

    }

    public long getItemId(int position) {
        return position;
    }

}
