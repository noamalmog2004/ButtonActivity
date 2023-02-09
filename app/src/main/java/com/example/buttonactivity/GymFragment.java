package com.example.buttonactivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;

import java.io.File;

public class GymFragment extends Fragment implements View.OnClickListener {


    //for uriPath
    public static String PACKAGE_NAME;

    File localFile;
    firebaseDB f = new firebaseDB();
    Button btnChest, btnTri, btnBi, btnLegs, btnBack;
    Button btnBackToGym1, btnBackToGym2, btnBackToGym3, btnBackToGym4, btnBackToGym5;
    VideoView video,video2,video3,video4,video5;
    String uriPath;
    Uri uri;
    int idVideo, idVideo2, idVideo3, idVideo4, idVideo5;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_gym, container, false);
        View vAlert =  inflater.inflate(R.layout.dialog_chest, container, false);

       // btnBackToGym1 = vAlert.findViewById(R.id.btnBackToGym1);
        //btnBackToGym1.setOnClickListener(this);
        btnChest = v.findViewById(R.id.button);
        btnChest.setOnClickListener(this);
        // Inflate the layout for this fragment
        video = vAlert.findViewById(R.id.video);
        video2 = vAlert.findViewById(R.id.video2);
        video3 = vAlert.findViewById(R.id.video3);
        video4 = vAlert.findViewById(R.id.video4);
        video5 = vAlert.findViewById(R.id.video5);
        video.setVisibility(View.VISIBLE);
        video2.setVisibility(View.VISIBLE);
        video3.setVisibility(View.VISIBLE);
        video4.setVisibility(View.VISIBLE);
        video5.setVisibility(View.VISIBLE);
        idVideo = R.raw.vid1;
        idVideo2 = R.raw.vid1;
        idVideo3 = R.raw.vid1;
        idVideo4 = R.raw.vid1;
        idVideo5 = R.raw.vid1;
        //for uriPath
        return v;
    }

    @Override
    public void onClick(View view) {

        AlertDialog.Builder tempBuilder = new AlertDialog.Builder(getActivity());
        View tempDialogView = getLayoutInflater().inflate(R.layout.dialog_chest, null, false);
        tempBuilder.setView(tempDialogView);
        AlertDialog tempAd = tempBuilder.create();
        //tempAd.setCancelable(false);
        if (view == btnChest)
        {
//            PACKAGE_NAME = getView().getContext().getPackageName();
//            uriPath="android.resource://" + PACKAGE_NAME +"/" +idVideo;
//            uri=Uri.parse(uriPath);
//            video.setVideoURI(uri);
//            video.start();
//            video2.setVideoURI(uri);
//            video2.start();
//            video3.setVideoURI(uri);
//            video3.start();
//            video4.setVideoURI(uri);
//            video4.start();
//            video5.setVideoURI(uri);
//            video5.start();
            String str = "gs://finalproject-3b98c.appspot.com/chest/vid1.mp4";
            Uri uri = Uri.parse(str);
            video.setVideoURI(uri);
            video.start();
            tempAd.show();
            if(view == btnBackToGym1)
            {
                tempAd.cancel();
                tempAd.dismiss();
            }
        }
    }
}