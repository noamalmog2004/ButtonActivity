package com.example.buttonactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class profileFragment extends Fragment implements View.OnClickListener {

    TextView tvName, tvAge, tvEmail;
    Button btnChange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChange = v.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);




        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == btnChange)
        {
            Intent i = new Intent(getActivity(), ChangeInformation.class);
            startActivity(i);
        }
    }
}