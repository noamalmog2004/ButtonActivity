package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VideosActivity extends AppCompatActivity implements View.OnClickListener {

    //for videos
    private MediaController mediaController;
    VideoView video_1,video_2,video_3,video_4,video_5;
    TextView ds1,ds2,ds3,ds4,ds5;
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

        ds1 = findViewById(R.id.ds1);
        ds2 = findViewById(R.id.ds2);
        ds3 = findViewById(R.id.ds3);
        ds4 = findViewById(R.id.ds4);
        ds5 = findViewById(R.id.ds5);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(video_1);
        video_1.setMediaController(mediaController);



        Intent takeit = getIntent();
        String type = takeit.getStringExtra("type");
        //to check which button was clicked on
        // Set the media controller for the video view
        MediaController mediaController1 = new MediaController(this);
        mediaController.setAnchorView(video_1);
        video_1.setMediaController(mediaController);

        MediaController mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(video_2);
        video_2.setMediaController(mediaController2);

        MediaController mediaController3 = new MediaController(this);
        mediaController3.setAnchorView(video_3);
        video_3.setMediaController(mediaController3);

        MediaController mediaController4 = new MediaController(this);
        mediaController4.setAnchorView(video_4);
        video_4.setMediaController(mediaController4);

        MediaController mediaController5 = new MediaController(this);
        mediaController5.setAnchorView(video_5);
        video_5.setMediaController(mediaController5);

        //TODO:test
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vid1;

        String videoUrl="";
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference videoRef = storageRef.child("videos/myvideo.mp4");
        videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri downloadUrl) {
                // Use download Url to show the video in your app
                String videoUrl= downloadUrl.toString();
                video_1.setVideoURI(Uri.parse(videoUrl));
                video_1.start();
                Toast.makeText(VideosActivity.this, videoUrl, Toast.LENGTH_SHORT).show();
            }
        });
        //video_1.setVideoURI(Uri.parse(videoUrl));


//        switch (type)
//        {
//            case "ch":
//                //TODO:FIX VIDEO URL
//                Uri videoUri = Uri.parse(videoPath);
//                video_1.setVideoURI(videoUri);
//                video_2.setVideoURI(videoUri);
//                video_3.setVideoURI(videoUri);
//                video_4.setVideoURI(videoUri);
//                video_5.setVideoURI(videoUri);
//
//
//
//                ds1.setText("Bench Press - A chest exercise performed with a barbell or dumbbells, involving pressing the weight away from the chest while lying on a bench. ID=ch1");
//                ds2.setText("Pec Flies - A chest exercise performed with dumbbells, involving opening and closing the arms while lying on a bench with the weights held above the chest. ID=ch2");
//                ds3.setText("Cable Flies - A chest exercise performed with cables or resistance bands, involving opening and closing the arms in front of the body. ID=ch3");
//                ds4.setText("Push Ups - A chest and triceps exercise performed by pushing the body up and down while in a plank position. ID=ch4");
//                ds5.setText("Diamond Push Ups - A triceps exercise performed by placing the hands close together in a diamond shape and pushing the body up and down while in a plank position. ID=ch5");
//                break;
//            case "ba":
////                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
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
//                ds1.setText("Lat Pulldown - A back exercise performed on a machine with a bar overhead, involving pulling the bar down towards the chest while sitting down. ID=ba1");
//                ds2.setText("Closed Lat Pulldown - A back exercise performed on a machine with a close-grip handle overhead, involving pulling the handle down towards the chest while sitting down. ID=ba2");
//                ds3.setText("Rows - A back exercise performed with dumbbells or a barbell, involving pulling the weight towards the body while leaning forward. ID=ba3");
//                ds4.setText("Pull Ups - A back exercise performed by hanging from a bar and pulling the body weight up towards the bar. ID=ba4");
//                ds5.setText("Closed Pull Ups - A back exercise performed by hanging from a bar with a narrow grip and pulling the body weight up towards the bar. ID=ba5");
//                break;
//            case "bi":
////                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
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
//                ds1.setText("Seated Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while seated. ID=bi1");
//                ds2.setText("Hammer Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while keeping the palms facing inwards. ID=bi2");
//                ds3.setText("Barbel Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while standing. ID=bi3");
//                ds4.setText("Chin Ups - A bicep and back exercise performed by hanging from a bar and pulling oneself up until the chin is above the bar. ID=bi4");
//                ds5.setText("Reverse Grip Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while keeping the palms facing down. ID=bi5");
//                break;
//            case "le":
////                video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_2.requestFocus();
//////                video_2.start();
//////                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_3.requestFocus();
//////                video_3.start();
//////                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_4.requestFocus();
//////                video_4.start();
//////                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_5.requestFocus();
//////                video_5.start();
//                ds1.setText("Leg Extensions - A leg exercise performed with a machine, involving extending the legs against resistance. ID=le1");
//                ds2.setText("Hamstring Pulls - A leg exercise performed with a machine or cable, involving pulling the weight towards the body by bending the knee. ID=le2");
//                ds3.setText("Bulgarian Splits - A leg exercise performed by standing with one foot on a bench or step behind the body, and bending the knee of the other leg to lower the body towards the ground. ID=le3");
//                ds4.setText("Squats - A leg exercise performed with a barbell or dumbbells, involving lowering the body by bending the knees and hips, and then returning to standing position. ID=le4");
//                ds5.setText("Calve Raises - A leg exercise performed with a machine or weight, involving raising the heels up and down while standing. ID=le5");
//                break;
//            case "tr":
////                  video_2.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_2.requestFocus();
//////                video_2.start();
//////                video_3.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_3.requestFocus();
//////                video_3.start();
//////                video_4.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_4.requestFocus();
//////                video_4.start();
//////                video_5.setVideoURI(Uri.parse("https://vimeo.com/805910667"));
//////                video_5.requestFocus();
//////                video_5.start();
//                ds1.setText("Triceps Extensions - A triceps exercise performed with dumbbells, involving extending the arms overhead and then lowering the weight behind the head with bent arms. ID=tr1");
//                ds2.setText("Jacksons - A triceps exercise performed with dumbbells, involving lifting the weight with straight arms from the side of the body to overhead, and then lowering the weight behind the head with bent arms. ID=tr2");
//                ds3.setText("Dips - A triceps exercise performed on parallel bars or a dip machine, involving lowering and lifting the body by bending and straightening the arms. ID=tr3");
//                ds4.setText("Skull Crushers - A triceps exercise performed with dumbbells or a barbell, involving lowering the weight towards the head while lying on a bench, and then extending the arms back up. ID=tr4");
//                ds5.setText("Tricep Press Machine - A triceps exercise performed on a machine with handles, involving pressing the handles down and extending the arms to work the triceps. ID=tr5");
//                break;
//        }

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
            Intent j = new Intent(VideosActivity.this, MainActivity.class);
            startActivity(j);
        }
    }
}