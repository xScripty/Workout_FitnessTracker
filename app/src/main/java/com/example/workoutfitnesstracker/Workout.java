package com.example.workoutfitnesstracker;

import java.sql.Time;

public class Workout {
    private int calories;
    private String time;
    private String name;

    Workout(int calories, String time,String name){
        this.calories=calories;
        this.time=time;
        this.name=name;
    }

    public int getCalories() {
        return calories;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
