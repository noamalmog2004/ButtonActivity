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
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.checkerframework.checker.nullness.qual.NonNull;

public class VideosActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img1,img2,img3,img4,img5;
    TextView ds1,ds2,ds3,ds4,ds5;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_videos);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);

        ds1 = findViewById(R.id.ds1);
        ds2 = findViewById(R.id.ds2);
        ds3 = findViewById(R.id.ds3);
        ds4 = findViewById(R.id.ds4);
        ds5 = findViewById(R.id.ds5);

        //to check which button was clicked on
        Intent takeit = getIntent();
        String type = takeit.getStringExtra("type");


        switch (type)
        {
            case "ch":

                Glide.with(this).load(R.drawable.bench).into(img1);
                Glide.with(this).load(R.drawable.pec_flys).into(img2);
                Glide.with(this).load(R.drawable.cable).into(img3);
                Glide.with(this).load(R.drawable.push_ups).into(img4);
                Glide.with(this).load(R.drawable.diamond).into(img5);
                ds1.setText("Bench Press - A chest exercise performed with a barbell or dumbbells, involving pressing the weight away from the chest while lying on a bench. ID=ch1");
                ds2.setText("Pec Flies - A chest exercise performed with dumbbells, involving opening and closing the arms while lying on a bench with the weights held above the chest. ID=ch2");
                ds3.setText("Cable Flies - A chest exercise performed with cables or resistance bands, involving opening and closing the arms in front of the body. ID=ch3");
                ds4.setText("Push Ups - A chest and triceps exercise performed by pushing the body up and down while in a plank position. ID=ch4");
                ds5.setText("Diamond Push Ups - A triceps exercise performed by placing the hands close together in a diamond shape and pushing the body up and down while in a plank position. ID=ch5");
                break;
            case "ba":
                Glide.with(this).load(R.drawable.lat_pulldown).into(img1);
                Glide.with(this).load(R.drawable.closed_pull_ups).into(img2);
                Glide.with(this).load(R.drawable.rows).into(img3);
                Glide.with(this).load(R.drawable.pull_ups).into(img4);
                Glide.with(this).load(R.drawable.closed_lat_pull_down).into(img5);

                ds1.setText("Lat Pulldown - A back exercise performed on a machine with a bar overhead, involving pulling the bar down towards the chest while sitting down. ID=ba1");
                ds2.setText("Closed Lat Pulldown - A back exercise performed on a machine with a close-grip handle overhead, involving pulling the handle down towards the chest while sitting down. ID=ba2");
                ds3.setText("Rows - A back exercise performed with dumbbells or a barbell, involving pulling the weight towards the body while leaning forward. ID=ba3");
                ds4.setText("Pull Ups - A back exercise performed by hanging from a bar and pulling the body weight up towards the bar. ID=ba4");
                ds5.setText("Closed Pull Ups - A back exercise performed by hanging from a bar with a narrow grip and pulling the body weight up towards the bar. ID=ba5");
                break;
            case "bi":
                Glide.with(this).load(R.drawable.seated_curls).into(img1);
                Glide.with(this).load(R.drawable.hammer_curls).into(img2);
                Glide.with(this).load(R.drawable.barbel_curls).into(img3);
                Glide.with(this).load(R.drawable.chin_ups).into(img4);
                Glide.with(this).load(R.drawable.reverse_barbel_curls).into(img5);

                ds1.setText("Seated Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while seated. ID=bi1");
                ds2.setText("Hammer Curls - A bicep exercise performed with dumbbells, involving curling the weight towards the body while keeping the palms facing inwards. ID=bi2");
                ds3.setText("Barbel Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while standing. ID=bi3");
                ds4.setText("Chin Ups - A bicep and back exercise performed by hanging from a bar and pulling oneself up until the chin is above the bar. ID=bi4");
                ds5.setText("Reverse Grip Curls - A bicep exercise performed with a barbell, involving curling the weight towards the body while keeping the palms facing down. ID=bi5");
                break;
            case "le":

                Glide.with(this).load(R.drawable.extensions).into(img1);
                Glide.with(this).load(R.drawable.hamstring).into(img2);
                Glide.with(this).load(R.drawable.bulgarians).into(img3);
                Glide.with(this).load(R.drawable.squats).into(img4);
                Glide.with(this).load(R.drawable.calves).into(img5);

                ds1.setText("Leg Extensions - A leg exercise performed with a machine, involving extending the legs against resistance. ID=le1");
                ds2.setText("Hamstring Pulls - A leg exercise performed with a machine or cable, involving pulling the weight towards the body by bending the knee. ID=le2");
                ds3.setText("Bulgarian Splits - A leg exercise performed by standing with one foot on a bench or step behind the body, and bending the knee of the other leg to lower the body towards the ground. ID=le3");
                ds4.setText("Squats - A leg exercise performed with a barbell or dumbbells, involving lowering the body by bending the knees and hips, and then returning to standing position. ID=le4");
                ds5.setText("Calve Raises - A leg exercise performed with a machine or weight, involving raising the heels up and down while standing. ID=le5");
                break;
            case "tr":
                Glide.with(this).load(R.drawable.triceps_extensions).into(img1);
                Glide.with(this).load(R.drawable.jacksons).into(img2);
                Glide.with(this).load(R.drawable.dips).into(img3);
                Glide.with(this).load(R.drawable.skull_crushers).into(img4);
                Glide.with(this).load(R.drawable.tricpes_pressmachine).into(img5);
                ds1.setText("Triceps Extensions - A triceps exercise performed with dumbbells, involving extending the arms overhead and then lowering the weight behind the head with bent arms. ID=tr1");
                ds2.setText("Jacksons - A triceps exercise performed with dumbbells, involving lifting the weight with straight arms from the side of the body to overhead, and then lowering the weight behind the head with bent arms. ID=tr2");
                ds3.setText("Dips - A triceps exercise performed on parallel bars or a dip machine, involving lowering and lifting the body by bending and straightening the arms. ID=tr3");
                ds4.setText("Skull Crushers - A triceps exercise performed with dumbbells or a barbell, involving lowering the weight towards the head while lying on a bench, and then extending the arms back up. ID=tr4");
                ds5.setText("Tricep Press Machine - A triceps exercise performed on a machine with handles, involving pressing the handles down and extending the arms to work the triceps. ID=tr5");
                break;
        }
    }
    @Override
    public void onClick(View view) {
        if (view == btnBack)
        {
            Intent j = new Intent(VideosActivity.this, MainActivity.class);
            j.putExtra("whereToGo","goToGym");
            startActivity(j);
        }
    }
}