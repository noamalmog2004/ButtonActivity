package com.example.buttonactivity;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TrainingManager {
    private static final String TAG = "TrainingManager";

    private DatabaseReference mDatabase;

    public TrainingManager() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void addTraining(String userId, String date, String exercise1, String exercise2, String exercise3, String exercise4) {
        // Create a new unique key for the training
        String trainingId = mDatabase.child("trainings").child(userId).push().getKey();

        // Set the values for the new training
        Map<String, Object> trainingValues = new HashMap<>();
        trainingValues.put("date", date);
        trainingValues.put("exercise1", exercise1);
        trainingValues.put("exercise2", exercise2);
        trainingValues.put("exercise3", exercise3);
        trainingValues.put("exercise4", exercise4);

        // Write the new training to the database
        mDatabase.child("trainings").child(userId).child(trainingId).setValue(trainingValues);

        // Check if any existing trainings are out of date and remove them
       // removeOutdatedTrainings(userId);
    }

    private void removeOutdatedTrainings(String userId) {
        // Get the current date
        Date currentDate = Calendar.getInstance().getTime();

        // Get a reference to the user's trainings
        DatabaseReference trainingsRef = mDatabase.child("trainings").child(userId);

        // Loop through each training and check if it's out of date
        trainingsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DataSnapshot trainingSnapshot : task.getResult().getChildren()) {
                    String trainingId = trainingSnapshot.getKey();
                    String dateString = trainingSnapshot.child("date").getValue(String.class);

                    // Parse the date string to a Date object
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date trainingDate;
                    try {
                        trainingDate = dateFormat.parse(dateString);
                    } catch (Exception e) {
                        Log.w(TAG, "Error parsing date: " + dateString, e);
                        continue;
                    }

                    // If the training date is before the current date, remove it
                    if (trainingDate.before(currentDate)) {
                        trainingsRef.child(trainingId).removeValue();
                    }
                }
            } else {
                Log.e(TAG, "Error getting trainings for user " + userId, task.getException());
            }
        });
    }
}
