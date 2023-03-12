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

public class CalendarFragment extends Fragment implements View.OnClickListener {

    Button btnAdd, btnSaveTraining, btnView;
    EditText etDay, etMonth, etYear;
    private Calendar calendar = Calendar.getInstance();

    private EditText dateEditText;
    private EditText exercise1EditText;
    private EditText exercise2EditText;
    private EditText exercise3EditText;
    private EditText exercise4EditText;
    private Button saveButton;
    private RecyclerView recyclerView;
    //   private GymAdapter adapter;

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
        View vAlert = inflater.inflate(R.layout.dialog_add_training, container, false);
        etDay = vAlert.findViewById(R.id.etDay);
        etMonth = vAlert.findViewById(R.id.etMonth);
        etYear = vAlert.findViewById(R.id.etYear);
        btnSaveTraining = vAlert.findViewById(R.id.btnSaveTraining);
        btnSaveTraining.setOnClickListener(this);
        exercise1EditText = vAlert.findViewById(R.id.id1);
        exercise2EditText = vAlert.findViewById(R.id.id2);
        exercise3EditText = vAlert.findViewById(R.id.id3);
        exercise4EditText = vAlert.findViewById(R.id.id4);


        //adapter = new GymAdapter(getActivity(), null);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // recyclerView.setAdapter(adapter);


//        dbHelper = new GymDatabaseHelper(getContext());
//        updateCalendar();


        return v;
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        if (view == btnAdd) {
            Intent j = new Intent(getContext(), CreateTraining.class);
            startActivity(j);
        }
        if (view == btnView)
        {
            Intent j = new Intent(getContext(), ViewTrainings.class);
            startActivity(j);
        }
        if (view == btnSaveTraining) {
            String day = etDay.getText().toString();
            String month = etMonth.getText().toString();
            String year = etYear.getText().toString();
            String exercise1 = exercise1EditText.getText().toString();
            String exercise2 = exercise2EditText.getText().toString();
            String exercise3 = exercise3EditText.getText().toString();
            String exercise4 = exercise4EditText.getText().toString();
            boolean ok = true;
            if(!exerciseMap.containsKey(exercise1) ||!exerciseMap.containsKey(exercise2)||!exerciseMap.containsKey(exercise3)||!exerciseMap.containsKey(exercise4) )
            {
                Toast.makeText(getContext(), "invalid id", Toast.LENGTH_SHORT).show();
                ok = false;
            }

//            if (isValidFutureDate(day, month, year) && ok) {
                String date = year + "-" + month + "-" + day;
                firebaseDB db = new firebaseDB();

//                HashMap<String, String> hashMap = new HashMap<>();
//                hashMap.put("Date", date);
//                hashMap.put("Exercise1", exercise1);
//                hashMap.put("Exercise2", exercise2);
//                hashMap.put("Exercise3", exercise3);
//                hashMap.put("Exercise4", exercise4);
                TrainingManager trainingManager = new TrainingManager();
                trainingManager.addTraining(db.auth.getCurrentUser().getEmail(), date, exerciseMap.get(exercise1), exerciseMap.get(exercise2), exerciseMap.get(exercise3), exerciseMap.get(exercise4));

                //TODO: CREATE REALTIME FB DATABASE

                //dbHelper.insertWorkout(date, exerciseMap.get(exercise1), exerciseMap.get(exercise2), exerciseMap.get(exercise3), exerciseMap.get(exercise4));
                // updateCalendar();
            //}
            //else {
                //Toast.makeText(getContext(), "dates are invalid", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static boolean isValidFutureDate(String day, String month, String year) {
//        try {
//            LocalDate date = LocalDate.parse(year + "-" + month + "-" + day);
//            return date.isAfter(LocalDate.now());
//        } catch (DateTimeParseException e) {
//            return false;
//        }
//    }
