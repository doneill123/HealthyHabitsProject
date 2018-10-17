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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private ImageButton buttonLogout;
    ImageButton buttonFood;

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

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText("Welcome"+user.getEmail());
        buttonLogout = (ImageButton) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
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
