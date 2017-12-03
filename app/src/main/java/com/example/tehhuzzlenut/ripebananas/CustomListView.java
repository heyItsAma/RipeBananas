package com.example.tehhuzzlenut.ripebananas;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by TehHuzzlenut on 11/29/2017.
 */

public class CustomListView extends ArrayAdapter<String>{

    //Class for custom view adapter of listview

    private String[] foodName;
    private String[] foodExpDate;
    private Integer[] imgs;
    private Activity context;

    public CustomListView(Activity context, String[] foodName, String[] foodExpDate, Integer[] imgs) {
        super(context, R.layout.listview_layout, foodName);

        this.context = context;
        this.foodName = foodName;
        this.foodExpDate = foodExpDate;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public View getView(int postion, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;

        if(r == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.foodImg.setImageResource(imgs[postion]);
        viewHolder.foodNameTV.setText(foodName[postion]);
        viewHolder.foodExpDateTV.setText(foodExpDate[postion]);

        return r;

    }

    class ViewHolder{
        TextView foodNameTV;
        TextView foodExpDateTV;
        ImageView foodImg;

        ViewHolder(View v){
            foodNameTV = v.findViewById(R.id.tvfoodName);
            foodExpDateTV = v.findViewById(R.id.tvFoodExpDate);
            foodImg = v.findViewById(R.id.foodImageView);
        }
    }
}
