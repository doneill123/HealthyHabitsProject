package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttonHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(FoodActivity.this, HomeActivity.class));
            }
        });
    }


    @Override
    public void onClick(View v) {

    }
}
