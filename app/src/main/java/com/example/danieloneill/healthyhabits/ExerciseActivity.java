package com.example.danieloneill.healthyhabits;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private ImageButton buttonLogout;
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private static final String TAG = "ExerciseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Log.d(TAG, "onCreate: Starting.");

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

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonLogout = (ImageButton) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setUpViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab5Fragment(), "Easy");
        adapter.addFragment(new Tab6Fragment(), "Intermediate");
        adapter.addFragment(new Tab7Fragment(), "Hard");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonLogout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
