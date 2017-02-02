package com.example.huimin_zhou.Huimin_Zhou_FitRunner.Database;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lucidity on 17/1/29.
 */

public class ExerciseEntry {
    private long Id = 0;
    private String inputType = "Manual";
    private String activityType = "Running";
    private String date;
    private String time;
    private int duration = 0;
    private float distance = 0;
    private int calories = 0;
    private int heart = 0;

    public ExerciseEntry() {
        SimpleDateFormat sf = new SimpleDateFormat("MMM dd yyyy");
        this.date = sf.format(new Date());
        SimpleDateFormat sf2 = new SimpleDateFormat("hh:mm:ss");
        this.time = sf2.format(new Date());
    }

    public ExerciseEntry(String inputType, String activityType, String date, String time, int duration,
                   int distance, int calories, int heart) {
        this.inputType = inputType;
        this.activityType = activityType;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.distance = distance;
        this.calories = calories;
        this.heart = heart;
    }

    public long getId() {return Id;}

    public String getInputType() {return inputType;}

    public String getActivityType() {return activityType;}

    public String getDate() {return date;}

    public String getTime() {return time;}

    public int getDuration() {return duration;}

    public float getDistance() {return distance;}

    public int getCalories() {return calories;}

    public int getHeart() {return heart;}

    public void setId(long Id) {this.Id = Id;}

    public void setInputType(String inputType) {this.inputType = inputType;}

    public void setActivityType(String activityType) {this.activityType = activityType;}

    public void setDate(String date) {this.date = date;}

    public void setTime(String time) {this.time = time;}

    public void setDuration(int duration) {this.duration = duration;}

    public void setDistance(float distance) {this.distance = distance;}

    public void setCalories(int calories) {this.calories = calories;}

    public void setHeart(int heart) {this.heart = heart;}
}
