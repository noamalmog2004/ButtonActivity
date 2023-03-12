package com.example.buttonactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.nullness.qual.NonNull;

public class ChangeInformation extends AppCompatActivity implements View.OnClickListener {

    public Uri imageUri;
    private final int GALLERY_REQ_CODE = 1000;
    ImageView imgGallery, imageSchedule;
    Button btnSave, btnCamera;
    boolean changedProfile = false;
    ProgressDialog progressDialog;
    private firebaseDB db = new firebaseDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        //imgGallery = findViewById(R.id.imgGallery);
        imageSchedule = findViewById(R.id.imgGallery);
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
                //db.addFile(imageUri);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                String userEmail = db.auth.getInstance().getCurrentUser().getEmail();
                StorageReference scheduleRef = storageRef.child("images/"+userEmail+".jpg");
                // Delete the file that already exists to prevent loss of storage
                //there's always a picture already existing.
                scheduleRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ChangeInformation.this, "file deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(ChangeInformation.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                //upload image
                scheduleRef.putFile(imageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                imageSchedule.setImageURI(null);
                                Toast.makeText(ChangeInformation.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                                if(progressDialog.isShowing())
                                    progressDialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(ChangeInformation.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
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
                //imgGallery.setImageURI(data.getData());
                imageUri = data.getData();
                imageSchedule.setImageURI(data.getData());
                changedProfile = true;
            }
        }
    }
}