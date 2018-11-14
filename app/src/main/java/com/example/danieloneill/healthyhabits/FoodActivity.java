package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextName;
    EditText editTextCalorie;
    Button buttonAdd;
    Spinner spinnerFoods;
    DatabaseReference databaseFoods;

    ListView listViewFoods;
    List<Foods> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(FoodActivity.this, HomeActivity.class));
            }
        });

        ImageButton buttonDrink = (ImageButton) findViewById(R.id.buttonDrink);
        buttonDrink.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(FoodActivity.this, DrinkActivity.class));
            }
        });

        ImageButton buttonExercise = (ImageButton) findViewById(R.id.buttonExercise);
        buttonExercise.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(FoodActivity.this, ExerciseActivity.class));
            }
        });

        ImageButton buttonSleep = (ImageButton) findViewById(R.id.buttonSleep);
        buttonSleep.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(FoodActivity.this, SleepActivity.class));
            }
        });

        ImageButton buttonSocial = (ImageButton) findViewById(R.id.buttonSocial);
        buttonSocial.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(FoodActivity.this, SocialActivity.class));
            }
        });

        databaseFoods = FirebaseDatabase.getInstance().getReference("Foods");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCalorie = (EditText) findViewById(R.id.editTextCalorie);
        buttonAdd = (Button) findViewById(R.id.buttonAddFood);
        spinnerFoods = (Spinner) findViewById(R.id.spinnerFoods);

        listViewFoods = (ListView) findViewById(R.id.listViewFoods);
        foodsList = new ArrayList<>();

        buttonAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    databaseFoods.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            foodsList.clear();

            for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                Foods foods = foodSnapshot.getValue(Foods.class);

                foodsList.add(foods);
            }

            FoodList adapter = new FoodList(FoodActivity.this, foodsList);
            listViewFoods.setAdapter(adapter);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }

    });

}




    private void addFood(){
        String name = editTextName.getText().toString().trim();
        String calorieText = editTextCalorie.getText().toString().trim();
        int calorie = Integer.parseInt(calorieText);
        String category = spinnerFoods.getSelectedItem().toString();

        if(!TextUtils.isEmpty(name)){

            String id = databaseFoods.push().getKey();
            Foods foods = new Foods(id, name, calorie, category);
            databaseFoods.child(id).setValue(foods);
            Toast.makeText(this, "Food added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "Please enter a food and calorie amount", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}