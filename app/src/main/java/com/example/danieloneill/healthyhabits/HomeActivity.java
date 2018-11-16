package com.example.danieloneill.healthyhabits;

import android.content.Intent;
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
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private ImageButton buttonLogout;
    DatabaseReference databaseFoods;
    ImageButton buttonFood;
    EditText editTextName;
    EditText editTextCalorie;
    Button buttonAdd;
    Spinner spinnerFoods;
    ListView listViewFoods;
    List<Foods> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageButton buttonFood = (ImageButton) findViewById(R.id.buttonFood);
        buttonFood.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, FoodActivity.class));
            }
        });

        ImageButton buttonDrink = (ImageButton) findViewById(R.id.buttonDrink);
        buttonDrink.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, DrinkActivity.class));
            }
        });

        ImageButton buttonExercise = (ImageButton) findViewById(R.id.buttonExercise);
        buttonExercise.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, ExerciseActivity.class));
            }
        });

        ImageButton buttonSleep = (ImageButton) findViewById(R.id.buttonSleep);
        buttonSleep.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, SleepActivity.class));
            }
        });

        ImageButton buttonSocial = (ImageButton) findViewById(R.id.buttonSocial);
        buttonSocial.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, SocialActivity.class));
            }
        });

        this.getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (ImageButton) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
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
                FoodList adapter = new FoodList(HomeActivity.this, foodsList);
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
    public void onClick(View view) {

        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent (this, LoginActivity.class));
        }
    }
}
