package com.example.buttonactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

public class VideosActivity extends AppCompatActivity implements View.OnClickListener {

    //for videos
    private MediaController mediaController1,mediaController2,mediaController3,mediaController4,mediaController5;
    VideoView video_1,video_2,video_3,video_4,video_5;
    TextView ds1,ds2,ds3,ds4,ds5;
    String uriPath;
    Uri uri;
    Button btnBack;
    Button btnPlay1,btnPlay2,btnPlay3,btnPlay4,btnPlay5;
    Button btnStop1,btnStop2,btnStop3,btnStop4,btnStop5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_videos);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnPlay1 = findViewById(R.id.btnPlay1);
        btnPlay2 = findViewById(R.id.btnPlay2);
        btnPlay3 = findViewById(R.id.btnPlay3);
        btnPlay4 = findViewById(R.id.btnPlay4);
        btnPlay5 = findViewById(R.id.btnPlay5);

        btnStop1 = findViewById(R.id.btnStop1);
        btnStop2 = findViewById(R.id.btnStop2);
        btnStop3 = findViewById(R.id.btnStop3);
        btnStop4 = findViewById(R.id.btnStop4);
        btnStop5 = findViewById(R.id.btnStop5);


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

//        mediaController1 = new MediaController(this);
//        mediaController1.setAnchorView(video_1);
//        video_1.setMediaController(mediaController1);
//
//        mediaController2 = new MediaController(this);
//        mediaController2.setAnchorView(video_2);
//        video_2.setMediaController(mediaController2);
//
//        mediaController3 = new MediaController(this);
//        mediaController3.setAnchorView(video_3);
//        video_3.setMediaController(mediaController3);
//
//        mediaController4 = new MediaController(this);
//        mediaController4.setAnchorView(video_4);
//        video_4.setMediaController(mediaController4);
//
//        mediaController5 = new MediaController(this);
//        mediaController5.setAnchorView(video_5);
//        video_5.setMediaController(mediaController5);

        Intent takeit = getIntent();
        String type = takeit.getStringExtra("type");
        //to check which button was clicked on
        // Set the media controller for the video view


        switch (type)
        {
            case "ch":
                //TODO:FIX VIDEO URL
//                Uri videoUri = Uri.parse(videoPath);
//                video_1.setVideoURI(videoUri);
//                video_2.setVideoURI(videoUri);
//                video_3.setVideoURI(videoUri);
//                video_4.setVideoURI(videoUri);
//                video_5.setVideoURI(videoUri);
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vid1;
                Uri videoUri = Uri.parse(videoPath);
                video_1.setVideoURI(videoUri);

                String videoPath2 = "android.resource://" + getPackageName() + "/" + R.raw.vid1;
                Uri videoUri2 = Uri.parse(videoPath2);
                video_2.setVideoURI(videoUri2);
                video_3.setVideoURI(videoUri2);
                video_4.setVideoURI(videoUri2);
                video_5.setVideoURI(videoUri2);

                video_1.start();
                video_1.pause();

                video_2.start();
                video_2.pause();

//                video_3.start();
//                video_3.pause();
//
//                video_4.start();
//                video_4.pause();
//
//                video_5.start();
//                video_5.pause();

                ds1.setText("Bench Press - A chest exercise performed with a barbell or dumbbells, involving pressing the weight away from the chest while lying on a bench. ID=ch1");
                ds2.setText("Pec Flies - A chest exercise performed with dumbbells, involving opening and closing the arms while lying on a bench with the weights held above the chest. ID=ch2");
                ds3.setText("Cable Flies - A chest exercise performed with cables or resistance bands, involving opening and closing the arms in front of the body. ID=ch3");
                ds4.setText("Push Ups - A chest and triceps exercise performed by pushing the body up and down while in a plank position. ID=ch4");
                ds5.setText("Diamond Push Ups - A triceps exercise performed by placing the hands close together in a diamond shape and pushing the body up and down while in a plank position. ID=ch5");
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
                ds1.setText("Lat Pulldown - A back exercise performed on a machine with a bar overhead, involving pulling the bar down towards the chest while sitting down. ID=ba1");
                ds2.setText("Closed Lat Pulldown - A back exercise performed on a machine with a close-grip handle overhead, involving pulling the handle down towards the chest while sitting down. ID=ba2");
                ds3.setText("Rows - A back exercise performed with dumbbells or a barbell, involving pulling the weight towards the body while leaning forward. ID=ba3");
                ds4.setText("Pull Ups - A back exercise performed by hanging from a bar and pulling the body weight up towards the bar. ID=ba4");
                ds5.setText("Closed Pull Ups - A back exercise performed by hanging from a bar with a narrow grip and pulling the body weight up towards the bar. ID=ba5");
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
                ds1.setText("Seated Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while seated. ID=bi1");
                ds2.setText("Hammer Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while keeping the palms facing inwards. ID=bi2");
                ds3.setText("Barbel Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while standing. ID=bi3");
                ds4.setText("Chin Ups - A bicep and back exercise performed by hanging from a bar and pulling oneself up until the chin is above the bar. ID=bi4");
                ds5.setText("Reverse Grip Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while keeping the palms facing down. ID=bi5");
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
                ds1.setText("Leg Extensions - A leg exercise performed with a machine, involving extending the legs against resistance. ID=le1");
                ds2.setText("Hamstring Pulls - A leg exercise performed with a machine or cable, involving pulling the weight towards the body by bending the knee. ID=le2");
                ds3.setText("Bulgarian Splits - A leg exercise performed by standing with one foot on a bench or step behind the body, and bending the knee of the other leg to lower the body towards the ground. ID=le3");
                ds4.setText("Squats - A leg exercise performed with a barbell or dumbbells, involving lowering the body by bending the knees and hips, and then returning to standing position. ID=le4");
                ds5.setText("Calve Raises - A leg exercise performed with a machine or weight, involving raising the heels up and down while standing. ID=le5");
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
                ds1.setText("Triceps Extensions - A triceps exercise performed with dumbbells, involving extending the arms overhead and then lowering the weight behind the head with bent arms. ID=tr1");
                ds2.setText("Jacksons - A triceps exercise performed with dumbbells, involving lifting the weight with straight arms from the side of the body to overhead, and then lowering the weight behind the head with bent arms. ID=tr2");
                ds3.setText("Dips - A triceps exercise performed on parallel bars or a dip machine, involving lowering and lifting the body by bending and straightening the arms. ID=tr3");
                ds4.setText("Skull Crushers - A triceps exercise performed with dumbbells or a barbell, involving lowering the weight towards the head while lying on a bench, and then extending the arms back up. ID=tr4");
                ds5.setText("Tricep Press Machine - A triceps exercise performed on a machine with handles, involving pressing the handles down and extending the arms to work the triceps. ID=tr5");
                break;
        }

       //TODO:MAKE VIDEOS AND UPLOAD THEM HERE.
        String path = "android.resource://" + getPackageName() +"/" + R.raw.vid1;
        video_1 = (VideoView) findViewById(R.id.video_1);
        video_1.setVideoPath(path);
        video_2 = (VideoView) findViewById(R.id.video_2);
        video_2.setVideoPath(path);





    }

    @Override
    public void onClick(View view) {
        if (view == btnBack)
        {
            Intent j = new Intent(VideosActivity.this, MainActivity.class);
            j.putExtra("whereToGo","goToGym");
            startActivity(j);
        }
        if(view == btnPlay1)
            playVideo(video_1);
        if(view == btnPlay2)
            playVideo(video_2);
        if(view == btnPlay3)
            playVideo(video_3);
        if(view == btnPlay4)
            playVideo(video_4);
        if(view == btnPlay5)
            playVideo(video_5);
        if(view == btnStop1)
            stopVideo(video_1);
        if(view == btnStop2)
            stopVideo(video_2);
        if(view == btnStop3)
            stopVideo(video_3);
        if(view == btnStop4)
            stopVideo(video_4);
        if(view == btnStop2)
            stopVideo(video_5);



    }
    //for video handling
    private VideoView currentVideoView;

    public void playVideo(VideoView videoView) {
        // Check if there is a video currently playing
        if (currentVideoView != null) {
            // If there is, stop it
            currentVideoView.stopPlayback();
        }

        // Start the new video
        videoView.start();

        // Set the new video as the current video
        currentVideoView = videoView;
    }

    public void stopVideo(VideoView videoView) {
        // Stop the video
        videoView.stopPlayback();

        // Reset the current video view if it is the one being stopped
        if (currentVideoView == videoView) {
            currentVideoView = null;
        }
    }
}