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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton buttonHome;
    EditText editTextName;
    Button buttonAdd;
    Spinner spinnerFoods;

    DatabaseReference databaseFoods;

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

        databaseFoods = FirebaseDatabase.getInstance().getReference("Foods");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAdd = (Button) findViewById(R.id.buttonAddFood);
        spinnerFoods = (Spinner) findViewById(R.id.spinnerFoods);

        buttonAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addFood();
            }
        });
    }

        private void addFood(){
        String name = editTextName.getText().toString().trim();
        String category = spinnerFoods.getSelectedItem().toString();
        if(!TextUtils.isEmpty(name)){

            String id = databaseFoods.push().getKey();
            Foods foods = new Foods(id, name, category);
            databaseFoods.child(id).setValue(foods);
            Toast.makeText(this, "Food added", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "You should enter a food", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
