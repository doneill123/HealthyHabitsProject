package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.anychart.AnyChartView;
import com.anychart.charts.Pie;
import com.anychart.AnyChart;
import com.anychart.enums.LegendLayout;
import com.anychart.enums.Align;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private ImageButton buttonLogout;
    ImageButton buttonFood;
    Button buttonAdd;

    DatabaseReference databaseFoods;
    EditText editTextName;
    EditText editTextCalorie;
    Spinner spinnerFoods;
    ListView listViewFoods;
    List<Foods> foodsList;

    DatabaseReference databaseDrinks;
    EditText editTextDrinkName;
    EditText editTextDrinkCalorie;
    Spinner spinnerDrinks;
    ListView listViewDrinks;
    List<Drinks> drinksList;

    private int totalFood;
    private int totalDrinks;
    Pie pie;
    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        anyChartView = (AnyChartView)findViewById(R.id.any_chart_view);
        //anyChartView.setProgressBar(findViewById(R.id.progress_bar));

        pie = AnyChart.pie();

        //used to stop the keyboard popping up when home page opens
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        //Buttons for navigation through the application
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

        databaseDrinks = FirebaseDatabase.getInstance().getReference("Drinks");
        editTextDrinkName = (EditText) findViewById(R.id.editTextDrinkName);
        editTextDrinkCalorie = (EditText) findViewById(R.id.editTextDrinkCalorie);
        buttonAdd = (Button) findViewById(R.id.buttonAddDrink);
        spinnerDrinks = (Spinner) findViewById(R.id.spinnerDrinks);
        listViewDrinks = (ListView) findViewById(R.id.listViewDrinks);
        drinksList = new ArrayList<>();

        buttonAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });

        buttonAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addDrink();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //Connecting the list of foods from Firebase onto the listview in activity_home.xml
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

                for ( Foods tempFood : foodsList)
                {
                    totalFood += tempFood.getFoodCalorie();
                }

                for ( Drinks tempDrink : drinksList)
                {
                    totalDrinks += tempDrink.getDrinkCalorie();
                }

                List<DataEntry> data = new ArrayList<>();
                data.add(new ValueDataEntry("Food", totalFood));
                data.add(new ValueDataEntry("Drink", totalDrinks));

                pie.data((data));
                pie.title("Calories consumed per day");

                pie.legend()
                        .position("center-bottom")
                        .itemsLayout(LegendLayout.HORIZONTAL)
                        .align(Align.CENTER);

                anyChartView.setChart(pie);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        //Connecting the list of drinks from Firebase onto the listview in activity_home.xml
        databaseDrinks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                drinksList.clear();
                for (DataSnapshot drinkSnapshot : dataSnapshot.getChildren()) {
                    Drinks drinks = drinkSnapshot.getValue(Drinks.class);
                    drinksList.add(drinks);
                }
                DrinkList adapter = new DrinkList(HomeActivity.this, drinksList);
                listViewDrinks.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    //Allowing user to add a food
    private void addFood() {
        String name = editTextName.getText().toString().trim();
        String calorieText = editTextCalorie.getText().toString().trim();
        int calorie = Integer.parseInt(calorieText);
        String category = spinnerFoods.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(calorieText)) {
            Toast.makeText(this, "Both food fields left empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Food name field is empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(calorieText)) {
            Toast.makeText(this, "Food calorie field is empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        else if(!TextUtils.isEmpty(name)) {
            String id = databaseFoods.push().getKey();
            Foods foods = new Foods(id, name, calorie, category);
            databaseFoods.child(id).setValue(foods);
            Toast.makeText(this, "Food added", Toast.LENGTH_SHORT).show();
        }
    }

    //Allowing user to add a drink
    private void addDrink() {
        String name = editTextDrinkName.getText().toString().trim();
        String calorieDrinkText = editTextDrinkCalorie.getText().toString().trim();
        int calorie = Integer.parseInt(calorieDrinkText);
        String category = spinnerDrinks.getSelectedItem().toString();

        if (TextUtils.isEmpty(name) && TextUtils.isEmpty(calorieDrinkText)) {
            Toast.makeText(this, "Both drink fields left empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Drink name field is empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(calorieDrinkText)) {
            Toast.makeText(this, "Drink calorie field is empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        else if (!TextUtils.isEmpty(name)) {
            String id = databaseDrinks.push().getKey();
            Drinks drinks = new Drinks(id, name, calorie, category);
            databaseDrinks.child(id).setValue(drinks);
            Toast.makeText(this, "Drink added", Toast.LENGTH_SHORT).show();
        }
    }

    //When a user clicks the logout button
    @Override
    public void onClick(View view) {

        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent (this, LoginActivity.class));
        }
    }
}
