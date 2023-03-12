package com.example.buttonactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        Button startAnimButton = v.findViewById(R.id.start_anim_button);
        ImageView animImage = v.findViewById(R.id.anim_image);
        startAnimButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_animation);
                animImage.startAnimation(animation);
                animImage.setVisibility(View.VISIBLE);
            }
        });



        return v;
    }
}