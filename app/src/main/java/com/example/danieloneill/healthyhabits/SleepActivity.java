package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SleepActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private ImageButton buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        //Buttons for navigation through the application
        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepActivity.this, HomeActivity.class));
            }
        });

        ImageButton buttonFood = (ImageButton) findViewById(R.id.buttonFood);
        buttonFood.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepActivity.this, FoodActivity.class));
            }
        });

        ImageButton buttonDrink = (ImageButton) findViewById(R.id.buttonDrink);
        buttonDrink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepActivity.this, DrinkActivity.class));
            }
        });

        ImageButton buttonExercise = (ImageButton) findViewById(R.id.buttonExercise);
        buttonExercise.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepActivity.this, ExerciseActivity.class));
            }
        });

        ImageButton buttonSocial = (ImageButton) findViewById(R.id.buttonSocial);
        buttonSocial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(SleepActivity.this, SocialActivity.class));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (ImageButton) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        Button reset = (Button)findViewById(R.id.buttonReset);
    }

    public void avgFunction(View v)
    {
        EditText a = (EditText)findViewById(R.id.editTextAverage1);
        EditText b = (EditText)findViewById(R.id.editTextAverage2);
        EditText c = (EditText)findViewById(R.id.editTextAverage3);
        EditText d = (EditText)findViewById(R.id.editTextAverage4);
        EditText e = (EditText)findViewById(R.id.editTextAverage5);
        EditText f = (EditText)findViewById(R.id.editTextAverage6);
        EditText g = (EditText)findViewById(R.id.editTextAverage7);
        TextView t = (TextView)findViewById(R.id.textViewAvg);
        Button btn = (Button)findViewById(R.id.buttonAvg);

        int num1 = Integer.parseInt(a.getText().toString());
        int num2 = Integer.parseInt(b.getText().toString());
        int num3 = Integer.parseInt(c.getText().toString());
        int num4 = Integer.parseInt(d.getText().toString());
        int num5 = Integer.parseInt(e.getText().toString());
        int num6 = Integer.parseInt(f.getText().toString());
        int num7 = Integer.parseInt(g.getText().toString());
        double avg = (num1 + num2 + num3 + num4 + num5 + num6 + num7)/7;

        t.setText(Double.toString(avg));

        if (avg < 6.1) {
            Toast.makeText(getApplicationContext(), "You fall under the recommended hours of sleep",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        else if (avg > 9.9){
            Toast.makeText(getApplicationContext(), "You are over the recommended hours of sleep",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            Toast.makeText(getApplicationContext(), "You are within the recommended hours of sleep",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //When a user clicks the logout button
    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
