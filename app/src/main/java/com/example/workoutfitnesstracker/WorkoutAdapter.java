package com.example.workoutfitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class WorkoutAdapter extends ArrayAdapter<Workout> {
    public WorkoutAdapter(@NonNull Context context, ArrayList<Workout> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentView = convertView;

        if(currentView==null){
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.custom_workout_list_view,
                    parent,false);

        }

        Workout workout=getItem(position);

        TextView name=(TextView)currentView.findViewById(R.id.item_name_text_view);
        name.setText(workout.getName());

        TextView calories =(TextView)currentView.findViewById(R.id.item_calories_text_view);
        calories.setText(String.valueOf(workout.getCalories()));

        TextView time=(TextView)currentView.findViewById(R.id.item_time_text_view);
        time.setText(workout.getTime());

        return currentView;
    }
}
