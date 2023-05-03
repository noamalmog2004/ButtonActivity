package com.example.buttonactivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//for checkDates
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.HashMap;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CalendarFragment extends Fragment implements View.OnClickListener {

    Button btnAdd, btnView;

    HashMap<String, String> exerciseMap = new HashMap<String, String>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO:exercies
        exerciseMap.put("bi1", "Seated Curls");
        exerciseMap.put("bi2", "Hammer Curls");
        exerciseMap.put("bi3", "Barbel Curls");
        exerciseMap.put("bi4", "Chin Ups");
        exerciseMap.put("bi5", "Reverese Grip Curls");
        exerciseMap.put("ch1", "Bench Press");
        exerciseMap.put("ch2", "Pec Flies");
        exerciseMap.put("ch3", "Cable Flies");
        exerciseMap.put("ch4", "Push Ups");
        exerciseMap.put("ch5", "Diamond Push Ups");
        exerciseMap.put("tr1", "Triceps Extensions");
        exerciseMap.put("tr2", "Jacksons");
        exerciseMap.put("tr3", "Dips");
        exerciseMap.put("tr4", "Skull Crushers");
        exerciseMap.put("tr5", "Tricep Press Machine");
        exerciseMap.put("ba1", "Lat Pulldown");
        exerciseMap.put("ba2", "Closed Lat Pulldown");
        exerciseMap.put("ba3", "Rows");
        exerciseMap.put("ba4", "Pull Ups");
        exerciseMap.put("ba5", "Closed Pull Ups");
        exerciseMap.put("le1", "Leg Extensions");
        exerciseMap.put("le2", "Hamstring Pulls");
        exerciseMap.put("le3", "Bulgarian Splits");
        exerciseMap.put("le4", "Squats");
        exerciseMap.put("le5", "Calve Raises");


        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        btnAdd = v.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnView = v.findViewById(R.id.btnView);
        btnView.setOnClickListener(this);


        return v;
    }
    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Intent j = new Intent(getContext(), CreateTraining.class);
            startActivity(j);
        }
        if (view == btnView) {
            firebaseDB db = new firebaseDB();
            FirebaseFirestore f = db.fs;
            String path = extractUsername(db.auth.getCurrentUser().getEmail());
            Query query = f.collection("trainings").whereEqualTo("belongsTo",path);
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        int count = task.getResult().size();
                        if (count > 0) {
                            // User has at least one training, launch the intent
                            Intent intent = new Intent(getContext(), ViewTrainings.class);
                            startActivity(intent);
                        } else {
                            // User has no training, show a message or take some other action
                            Toast.makeText(getContext(), "No trainings planned!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getContext(), "error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    public static String extractUsername(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex >= 0) {
            return email.substring(0, atIndex);
        } else {
            return null; // or throw an exception, depending on your needs
        }
    }
}
