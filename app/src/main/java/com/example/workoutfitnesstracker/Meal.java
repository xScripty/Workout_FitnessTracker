package com.example.workoutfitnesstracker;

public class Meal {
    private String name;
    private int calories;
    private String pathName;
    private String fileName;

    Meal(String name,int calories,String fileDir,String fileName){
        this.name=name;
        this.calories=calories;
        this.pathName =fileDir;
        this.fileName=fileName;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getPathName() {
        return pathName;
    }

    public String getFileName() {
        return fileName;
    }
}
