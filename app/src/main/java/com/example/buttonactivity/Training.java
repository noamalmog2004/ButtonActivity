package com.example.buttonactivity;

public class Training {
    private String date;
    private String exercise1;
    private String exercise2;
    private String exercise3;
    private String exercise4;
    private String belongsTo;


    public Training() {
        // Default constructor required for calls to DataSnapshot.getValue(Training.class)
    }

    public Training(String date, String exercise1, String exercise2, String exercise3, String exercise4, String belongsTo) {
        this.date = date;
        this.exercise1 = exercise1;
        this.exercise2 = exercise2;
        this.exercise3 = exercise3;
        this.exercise4 = exercise4;
        this.belongsTo = belongsTo;
    }

    public String getDate() {
        return date;
    }

    public String getExercise1() {
        return exercise1;
    }

    public String getExercise2() {
        return exercise2;
    }

    public String getExercise3() {
        return exercise3;
    }

    public String getExercise4() {
        return exercise4;
    }

    public String getBelongsTo() {return belongsTo;}


}
