package com.example.workoutfitnesstracker;

import androidx.annotation.NonNull;
import  androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.gson.Gson;

public class StatisticsActivity extends AppCompatActivity {
    Button backButton;
    TextView calories;
    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        preferences=getSharedPreferences("Userinfo",0);
        calories=(TextView) findViewById(R.id.calories_burnt_textview);



        String jsonText= preferences.getString("Workouts","[]");
        Workout[] workouts= new Gson().fromJson(jsonText,Workout[].class);

        int totalCals=0;

        for(Workout workout: workouts){
            totalCals+=workout.getCalories();
        }

        calories.setText("Calories burnt:"+totalCals);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent main_intent= new Intent(this,MainActivity.class);
            startActivity(main_intent);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }


}