package com.example.workoutfitnesstracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExersiseTrackingActivity extends AppCompatActivity {
    Button addButton;
    EditText calories,time,name;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exersise_tracking);


        preferences=getSharedPreferences("Userinfo",0);
        calories=(EditText) findViewById(R.id.CaloriesEditText);
        time=(EditText) findViewById(R.id.ExersiseTimeEditText);
        addButton=(Button)findViewById(R.id.AddButton);
        name=(EditText)findViewById(R.id.nameEditText);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(view);
            }
        });

    }

    private void add(View view){

        int caloriesNum= Integer.parseInt(calories.getText().toString());
        String timeText= time.getText().toString();
        String nameText=name.getText().toString();

        Workout workout=new Workout(caloriesNum,timeText,nameText);
        //making a workout code



        String objects = preferences.getString("Workouts","[]");
        Gson gson=new Gson();
        Workout[] workouts= gson.fromJson(objects,Workout[].class);


        List<Workout> workoutsList= new ArrayList<Workout>(Arrays.asList(workouts));
        workoutsList.add(workout);

        Gson gson1 = new Gson();
        String jsonText=gson1.toJson(workoutsList);
        SharedPreferences.Editor editor= preferences.edit();

        editor.putString("Workouts",jsonText);
        editor.apply();

        Intent main_intent=new Intent(this,MainActivity.class);
        startActivity(main_intent);
    }


}

