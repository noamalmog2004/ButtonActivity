package com.example.buttonactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class ViewTrainings extends AppCompatActivity implements EventListener<QuerySnapshot> {

    private FirebaseFirestore firestore;
    private ArrayList<Training> trainingArrayList;
    private ListView trainingListView;
    private TrainingsAdapter adapter;
    private firebaseDB db = new firebaseDB();
    public String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trainings);
        trainingListView = findViewById(R.id.listViewTrainings);
        trainingArrayList = new ArrayList<Training>();
        adapter = new TrainingsAdapter(this, R.layout.training_row, trainingArrayList);
        trainingListView.setAdapter(adapter);
        path = extractUsername(db.auth.getCurrentUser().getEmail());
        firestore = db.fs;
        firestore
                .collection("trainings")
                .addSnapshotListener(this);


        firestore
                .collection("trainings")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                            List<DocumentSnapshot> docList =  task.getResult().getDocuments();
                            trainingArrayList.clear();

                            for (DocumentSnapshot doc : docList)
                            {
                                if (doc.getString("belongsTo").equals(path))
                                {
                                    String date = doc.getString("date");
                                    String ex1 = doc.getString("exercise1");
                                    String ex2 = doc.getString("exercise2");
                                    String ex3 = doc.getString("exercise3");
                                    String ex4 = doc.getString("exercise4");

                                    Training training = new Training(date, ex1, ex2, ex3, ex4, path);

                                    trainingArrayList.add(training);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(ViewTrainings.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    public static String extractUsername(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex >= 0) {
            return email.substring(0, atIndex);
        } else {
            return null; // or throw an exception, depending on your needs
        }
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
        List<DocumentSnapshot> docList =  value.getDocuments();
        trainingArrayList.clear();

        for (DocumentSnapshot doc : docList)
        {
            if (removeOutdatedTrainings(doc.getString("date")))
                firestore.collection("trainings").document(doc.getId()).delete();
            else if (doc.getString("belongsTo").equals(path))
            {
                String date = doc.getString("date");
                String ex1 = doc.getString("exercise1");
                String ex2 = doc.getString("exercise2");
                String ex3 = doc.getString("exercise3");
                String ex4 = doc.getString("exercise4");

                Training training = new Training(date, ex1, ex2, ex3, ex4, path);

                trainingArrayList.add(training);
            }
        }
        adapter.notifyDataSetChanged();
    }


    public boolean removeOutdatedTrainings(String date1) {
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