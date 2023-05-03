package com.example.buttonactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

//import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.IOException;

public class GymFragment extends Fragment implements View.OnClickListener {

    Button btnChest, btnTri, btnBi, btnLegs, btnBa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_gym, container, false);
        btnChest = v.findViewById(R.id.button);
        btnChest.setOnClickListener(this);
        btnTri = v.findViewById(R.id.button2);
        btnTri.setOnClickListener(this);
        btnBa = v.findViewById(R.id.button3);
        btnBa.setOnClickListener(this);
        btnLegs = v.findViewById(R.id.button4);
        btnLegs.setOnClickListener(this);
        btnBi = v.findViewById(R.id.button5);
        btnBi.setOnClickListener(this);
        return v;
    }
    @Override
    public void onClick(View view) {
        if (view == btnChest)
        {
            Intent k = new Intent(getActivity(), VideosActivity.class);
            k.putExtra("type", "ch");
            startActivity(k);
        }
        if(view == btnBi)
        {
            Intent k = new Intent(getActivity(), VideosActivity.class);
            k.putExtra("type", "bi");
            startActivity(k);
        }
        if(view == btnTri)
        {
            Intent k = new Intent(getActivity(), VideosActivity.class);
            k.putExtra("type", "tr");
            startActivity(k);
        }
        if(view == btnLegs)
        {
            Intent k = new Intent(getActivity(), VideosActivity.class);
            k.putExtra("type", "le");
            startActivity(k);
        }
        if(view == btnBa)
        {
            Intent k = new Intent(getActivity(), VideosActivity.class);
            k.putExtra("type", "ba");
            startActivity(k);
        }
    }
}