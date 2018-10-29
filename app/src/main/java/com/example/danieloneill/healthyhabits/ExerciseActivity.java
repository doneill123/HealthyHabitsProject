package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, HomeActivity.class));
            }
        });

        ImageButton buttonFood = (ImageButton) findViewById(R.id.buttonFood);
        buttonFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, FoodActivity.class));
            }
        });

        ImageButton buttonDrink = (ImageButton) findViewById(R.id.buttonDrink);
        buttonDrink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, DrinkActivity.class));
            }
        });

        ImageButton buttonSleep = (ImageButton) findViewById(R.id.buttonSleep);
        buttonSleep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, SleepActivity.class));
            }
        });

        ImageButton buttonSocial = (ImageButton) findViewById(R.id.buttonSocial);
        buttonSocial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ExerciseActivity.this, SocialActivity.class));
            }
        });
    }
}
