package com.example.buttonactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class CreateTraining extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private Button btnShowCal;
    Button btnSaveTraining;
    private EditText exercise1EditText;
    private EditText exercise2EditText;
    private EditText exercise3EditText;
    private EditText exercise4EditText;
    public String day1, month1, year1;

    HashMap<String, String> exerciseMap = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);
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

        exercise1EditText = findViewById(R.id.id1);
        exercise2EditText = findViewById(R.id.id2);
        exercise3EditText = findViewById(R.id.id3);
        exercise4EditText = findViewById(R.id.id4);

        btnSaveTraining = findViewById(R.id.btnSaveTraining);
        btnSaveTraining.setOnClickListener(this);

        btnShowCal = findViewById(R.id.btnShowCal);
        btnShowCal.setOnClickListener(this);

        text = findViewById(R.id.text);

    }

    @Override
    public void onClick(View view) {
        if (view == btnSaveTraining)
        {
            String exercise1 = exercise1EditText.getText().toString();
            String exercise2 = exercise2EditText.getText().toString();
            String exercise3 = exercise3EditText.getText().toString();
            String exercise4 = exercise4EditText.getText().toString();
            boolean ok = true;
            if(!exerciseMap.containsKey(exercise1) ||!exerciseMap.containsKey(exercise2)||!exerciseMap.containsKey(exercise3)||!exerciseMap.containsKey(exercise4) )
            {
                Toast.makeText(CreateTraining.this, "invalid id", Toast.LENGTH_SHORT).show();
                ok = false;
            }
            exercise1 = exerciseMap.get(exercise1);
            exercise2 = exerciseMap.get(exercise2);
            exercise3 = exerciseMap.get(exercise3);
            exercise4 = exerciseMap.get(exercise4);
//            if (isValidFutureDate(day, month, year) && ok) {
            if (year1 == null||month1 == null||day1 == null)
                ok=false;

            String date = year1 + "-" + month1 + "-" + day1;

            if (alertUserIfDateIsOld(date))
            {
                Toast.makeText(CreateTraining.this, "Choose a date in the future!", Toast.LENGTH_SHORT).show();
            }
            if(ok)
            {
                firebaseDB db = new firebaseDB();
                String path = extractUsername(db.auth.getCurrentUser().getEmail());

                //trainingManager.addTraining(path, date, exerciseMap.get(exercise1), exerciseMap.get(exercise2), exerciseMap.get(exercise3), exerciseMap.get(exercise4));

                Training training = new Training(date, exercise1, exercise2, exercise3, exercise4, path);
                FirebaseFirestore fs = db.fs;
                fs
                        .collection("trainings")
                        .document(System.currentTimeMillis() + "")
                        .set(training)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(CreateTraining.this, "good", Toast.LENGTH_SHORT).show();
                                Intent k = new Intent(CreateTraining.this, MainActivity.class);
                                k.putExtra("whereToGo", "goToCal");
                                startActivity(k);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateTraining.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
            else
            {
                Toast.makeText(CreateTraining.this, "pick a date", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == btnShowCal)
        {
            openDialog();
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
    private void openDialog()
    {
         DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
             @Override
             public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    text.setText("THE DATE U SELECTED: "+year+"/"+(month+1)+"/"+day);
                    year1 = Integer.toString(year);
                    month1 = Integer.toString(month+1);
                    day1 = Integer.toString(day);

             }
         }, 2023, 0, 1);
         dialog.show();
    }

    public boolean alertUserIfDateIsOld(String date1) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = formatter.parse(date1);
        } catch (ParseException e) {
            // Handle the case where the date string is not in the expected format
            return false;
        }
        Date currentDate = new Date();
        return date.before(currentDate);
    }
}