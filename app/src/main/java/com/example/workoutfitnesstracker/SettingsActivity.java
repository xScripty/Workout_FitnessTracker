package com.example.workoutfitnesstracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    ToggleButton darkmodeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        preferences=getSharedPreferences("Userinfo",0);


        darkmodeButton=(ToggleButton) findViewById(R.id.darkmode_toggle_button);

        if(preferences.getBoolean("DarkMode",false)) darkmodeButton.setChecked(true);

        darkmodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor= preferences.edit();
                if(darkmodeButton.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("DarkMode",true);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("DarkMode",false);
                }

                editor.apply();
                recreate();
            }
        });
    }

    // this event will enable the back
    // function to the button on press
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