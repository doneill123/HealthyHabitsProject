package com.example.danieloneill.healthyhabits;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class FoodList extends ArrayAdapter<Foods> {

    private Activity context;
    private List<Foods> foodsList;

    public FoodList(Activity context, List<Foods> foodsList){
        super(context, R.layout.list_layout, foodsList);
        this.context = context;
        this.foodsList = foodsList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewCalorie = (TextView) listViewItem.findViewById(R.id.textViewCalorie);

        Foods foods = foodsList.get(position);
        //ProgressBar pb = (ProgressBar) view.findViewById(R.id.pgbCalorieProgress);

        textViewName.setText(foods.getFoodName());
        String cal = String.valueOf(foods.getFoodCalorie());
        textViewCalorie.setText(cal);

        return listViewItem;
    }
}
