package com.example.workoutfitnesstracker;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.workoutfitnesstracker.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    SharedPreferences preferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        preferences=getSharedPreferences("Userinfo",0);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
                Fragment current =(navHostFragment == null ? null : navHostFragment.getChildFragmentManager().getFragments().get(0));
                if(current instanceof SecondFragment){
                    Intent track_intent= new Intent(getApplicationContext(),EatingTrackingActivity.class);
                    startActivity(track_intent);
                   // getSupportFragmentManager().getFragments().get(0).onCreate(savedInstanceState);
                }
                else {
                    Intent track_intent = new Intent(getApplicationContext(), ExersiseTrackingActivity.class);
                    startActivity(track_intent);
                }
            }
        });

 //       Intent svc=new Intent(this, BackgroundSoundService.class);
  //      startService(svc);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.ResetButton){
            SharedPreferences.Editor editor=preferences.edit();
            //delete workout and meal data
            editor.putString("Workouts","[]");
            editor.putString("Meals","[]");

            //delete images
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            Toast.makeText(this,directory.getAbsolutePath(),Toast.LENGTH_SHORT).show();

            for(File subfile: directory.listFiles()){
                subfile.delete();
            }

            //apply changes
            editor.apply();

            //Toast.makeText(this,"Data Reset!",Toast.LENGTH_SHORT).show();
            recreate();
        }
        if(id==R.id.statistic_button){
            Intent stats_intent= new Intent(this,StatisticsActivity.class);
            startActivity(stats_intent);
        }

        if(id==R.id.action_settings){
            Intent intent_settings = new Intent(this,SettingsActivity.class);
            startActivity(intent_settings);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("My Dialog Title");
        dialog.setMessage("Are you Sure you want to exit");
// dialog.setIcon(R.drawable.ic_baseline_audiotrack_24);
        dialog.setNegativeButton("No", null);
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                exit();
            }
        });


        AlertDialog alert=dialog.create();
        alert.show();

    }

    public void exit(){
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}

