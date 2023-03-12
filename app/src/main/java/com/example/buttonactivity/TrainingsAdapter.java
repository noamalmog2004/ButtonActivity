package com.example.buttonactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrainingsAdapter extends ArrayAdapter<Training> {

    private Context context;
    private ArrayList<Training> trainingArrayList;

    public TrainingsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Training> trainingArrayList) {
        super(context, resource, trainingArrayList);

        this.context = context;
        this.trainingArrayList = trainingArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.training_row, null, false);
        Training training = trainingArrayList.get(position);

        TextView tvDate = view.findViewById(R.id.tvDate);
        TextView tvExer1 = view.findViewById(R.id.tvExer1);
        TextView tvExer2 = view.findViewById(R.id.tvExer2);
        TextView tvExer3 = view.findViewById(R.id.tvExer3);
        TextView tvExer4 = view.findViewById(R.id.tvExer4);

        tvDate.setText(training.getDate());
        tvExer1.setText(training.getExercise1());
        tvExer2.setText(training.getExercise2());
        tvExer3.setText(training.getExercise3());
        tvExer4.setText(training.getExercise4());

        return view;
    }
}
