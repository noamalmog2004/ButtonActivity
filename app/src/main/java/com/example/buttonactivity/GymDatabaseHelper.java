package com.example.buttonactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GymDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "gym_app.db";

    // Define the table schema
    private static final String TABLE_NAME = "workouts";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_EXERCISE_1 = "exercise_1";
    private static final String COLUMN_EXERCISE_2 = "exercise_2";
    private static final String COLUMN_EXERCISE_3 = "exercise_3";
    private static final String COLUMN_EXERCISE_4 = "exercise_4";
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_DATE + " TEXT, " +
            COLUMN_EXERCISE_1 + " INTEGER, " +
            COLUMN_EXERCISE_2 + " INTEGER, " +
            COLUMN_EXERCISE_3 + " INTEGER, " +
            COLUMN_EXERCISE_4 + " INTEGER" +
            ")";

    public GymDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle schema upgrades here if necessary
    }

    public void insertWorkout(String date, String exercise1, String exercise2, String exercise3, String exercise4) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_EXERCISE_1, exercise1);
        values.put(COLUMN_EXERCISE_2, exercise2);
        values.put(COLUMN_EXERCISE_3, exercise3);
        values.put(COLUMN_EXERCISE_4, exercise4);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Cursor getWorkouts() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }
}
