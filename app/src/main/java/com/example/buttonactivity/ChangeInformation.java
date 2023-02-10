package com.example.buttonactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChangeInformation extends AppCompatActivity implements View.OnClickListener {

    public Uri imageUri;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery;
    Button btnSave, btnCamera;
    boolean changedProfile = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        imgGallery = findViewById(R.id.imgGallery);
        Button btnGallery = findViewById(R.id.btnCamera);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(this);
        btnGallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == btnCamera)
        {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE);
        }

        if (view == btnSave)
        {
            firebaseDB db = new firebaseDB();
            if (changedProfile){
                db.addFile(imageUri);
            }
            Intent intent = new Intent(ChangeInformation.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == GALLERY_REQ_CODE)
            {
                //for gallery
                imgGallery.setImageURI(data.getData());
                imageUri = data.getData();
                changedProfile = true;
            }
        }
    }
}