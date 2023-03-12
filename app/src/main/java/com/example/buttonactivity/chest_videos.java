package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.badge.BadgeUtils;

public class chest_videos extends AppCompatActivity implements View.OnClickListener {

    //for videos
    private MediaController mediaController;
    VideoView video_1,video_2,video_3,video_4,video_5;
    String uriPath;
    Uri uri;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_videos);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        video_1 = (VideoView) findViewById(R.id.video_1);
        video_2 = (VideoView) findViewById(R.id.video_2);
        video_3 = (VideoView) findViewById(R.id.video_3);
        video_4 = (VideoView) findViewById(R.id.video_4);
        video_5 = (VideoView) findViewById(R.id.video_5);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(video_1);
        video_1.setMediaController(mediaController);



        Intent takeit = getIntent();
        String type = takeit.getStringExtra("type");
        //to check which button was clicked on
        switch (type)
        {
            case "ch":
                //TODO:FIX VIDEO URL
//                video_1.setVideoURI(Uri.parse("https://www.veed.io/embed/3e2dc82e-576c-4d05-b9ac-9c0f3e5ef799"));
//                video_1.requestFocus();
//                video_1.start();
//                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_2.requestFocus();
//                video_2.start();
//                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_3.requestFocus();
//                video_3.start();
//                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_4.requestFocus();
//                video_4.start();
//                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_5.requestFocus();
//                video_5.start();
                break;
            case "ba":
//                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_2.requestFocus();
//                video_2.start();
//                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_3.requestFocus();
//                video_3.start();
//                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_4.requestFocus();
//                video_4.start();
//                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_5.requestFocus();
//                video_5.start();
                break;
            case "bi":
//                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_2.requestFocus();
//                video_2.start();
//                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_3.requestFocus();
//                video_3.start();
//                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_4.requestFocus();
//                video_4.start();
//                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//                video_5.requestFocus();
//                video_5.start();
                break;
            case "le":
//                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_2.requestFocus();
////                video_2.start();
////                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_3.requestFocus();
////                video_3.start();
////                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_4.requestFocus();
////                video_4.start();
////                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_5.requestFocus();
////                video_5.start();
                break;
            case "tr":
//                  video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_2.requestFocus();
////                video_2.start();
////                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_3.requestFocus();
////                video_3.start();
////                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_4.requestFocus();
////                video_4.start();
////                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
////                video_5.requestFocus();
////                video_5.start();
                break;
        }

        //TODO:MAKE VIDEOS AND UPLOAD THEM HERE.
//        String path = "android.resource://" + getPackageName() +"/" + R.raw.vid1;
//        video_1 = (VideoView) findViewById(R.id.video_1);
//        video_1.setVideoPath(path);
//        video_2 = (VideoView) findViewById(R.id.video_2);
//        video_2.setVideoPath(path);
        //video_chest_3 = (VideoView) findViewById(R.id.video_chest_3);
//        video_chest_3.setVideoPath(path);
//        video_chest_4 = (VideoView) findViewById(R.id.video_chest_4);
//        video_chest_4.setVideoPath(path);
//        video_chest_5 = (VideoView) findViewById(R.id.video_chest_5);
//        video_chest_5.setVideoPath(path);




    }

    @Override
    public void onClick(View view) {
        if (view == btnBack)
        {
            Intent j = new Intent(chest_videos.this, MainActivity.class);
            startActivity(j);
        }
    }
}