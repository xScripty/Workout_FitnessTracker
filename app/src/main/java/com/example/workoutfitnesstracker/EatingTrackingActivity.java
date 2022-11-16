package com.example.workoutfitnesstracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EatingTrackingActivity extends AppCompatActivity {

    EditText calories,name;
    Button addButton;
    ImageButton imgBtn;
    SharedPreferences preferences;


    private static final int id=135;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating_tracking);

        preferences=getSharedPreferences("Userinfo",0);

        imgBtn=(ImageButton)findViewById(R.id.meal_picture_image_button);
        calories=(EditText) findViewById(R.id.calory_intake_edit_text);
        name=(EditText) findViewById(R.id.meal_name_edit_text);
        addButton=(Button) findViewById(R.id.add_sleep_record);

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent,id);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName=String.valueOf(System.currentTimeMillis());
                Bitmap bitmap= ((BitmapDrawable)imgBtn.getDrawable()).getBitmap();
                String pathName=saveToInternalStorage(bitmap,fileName);
                Meal meal=new Meal(name.getText().toString(), Integer.parseInt(calories.getText().toString()),pathName,fileName);

                String objects = preferences.getString("Meals","[]");
                Gson gson=new Gson();
                Meal[] meals= gson.fromJson(objects,Meal[].class);


                List<Meal> mealsList= new ArrayList<Meal>(Arrays.asList(meals));
                mealsList.add(meal);

                Gson gson1 = new Gson();
                String jsonText=gson1.toJson(mealsList);
                SharedPreferences.Editor editor= preferences.edit();

                editor.putString("Meals",jsonText);
                editor.apply();


                Intent main_intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(main_intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==id){
            imgBtn.setImageBitmap((Bitmap)   data.getExtras().get("data"));
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String fileName){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,fileName+".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }



}