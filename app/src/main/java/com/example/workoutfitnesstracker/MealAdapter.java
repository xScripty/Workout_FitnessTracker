package com.example.workoutfitnesstracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MealAdapter extends ArrayAdapter<Meal> {
    // invoke the suitable constructor of the ArrayAdapter class
    public MealAdapter(@NonNull Context context, ArrayList<Meal> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_meal_list_item, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Meal currentMeal = getItem(position);

        //get the image


        // then according to the position of the view assign the desired image for the same
        ImageView mealImage = currentItemView.findViewById(R.id.meal_image_view);
        assert currentMeal != null;
        loadImageFromStorage(currentMeal.getPathName(),currentMeal.getFileName(),mealImage);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView name = currentItemView.findViewById(R.id.meal_name_text_view);
        name.setText(currentMeal.getName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView calories= currentItemView.findViewById(R.id.meal_calories_text_view);
        calories.setText(String.valueOf(currentMeal.getCalories()));

        // then return the recyclable view
        return currentItemView;
    }



    private void loadImageFromStorage(String path,String fileName,ImageView img)
    {

        try {
            File f=new File(path, fileName+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

}
