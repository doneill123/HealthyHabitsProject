package com.example.danieloneill.healthyhabits;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DrinkList extends ArrayAdapter<Drinks> {

    private Activity context;
    private List<Drinks> drinksList;

    public DrinkList(Activity context, List<Drinks> drinksList){
        super(context, R.layout.list_layoutdrink, drinksList);
        this.context = context;
        this.drinksList = drinksList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layoutdrink,null, true);

        TextView textViewDrinkName = (TextView) listViewItem.findViewById(R.id.textViewDrinkName);
        TextView textViewDrinkCalorie = (TextView) listViewItem.findViewById(R.id.textViewDrinkCalorie);

        Drinks drinks = drinksList.get(position);

        textViewDrinkName.setText(drinks.getDrinkName());
        String cal = String.valueOf(drinks.getDrinkCalorie());
        textViewDrinkCalorie.setText(cal);

        return listViewItem;
    }
}