package com.example.buttonactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button btnRegister, btnBack, btnCamera2;
    public EditText etPassword, etEmail, etFullname, etUsername, etAge, etWeight;
    private FirebaseAuth mAuth;
    private final int GALLERY_REQ_CODE2 = 1000;
    public ImageView imgGallery2, imageSchedule2;
    public Uri imageUri2;
    public boolean changedProfile = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.loginbtn);
        btnRegister.setOnClickListener(this);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullname);
        etEmail = findViewById(R.id.etEmail1);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        imgGallery2 = findViewById(R.id.imgGallery2);
        btnCamera2 = findViewById(R.id.btnCamera2);
        btnCamera2.setOnClickListener(this);
    }
    public void onClick(View view) {
        firebaseDB db = new firebaseDB();
        String fullname  = etFullname.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();
        String age = etAge.getText().toString();
        String weight = etWeight.getText().toString();
        if (view == btnRegister && changedProfile) {
//            if (!(db.fullnameValid(fullname)))
//                Toast.makeText(getApplicationContext(), "Full name is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.passwordValid(password)))
//                Toast.makeText(getApplicationContext(), "Password is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.emailValid(email)))
//                Toast.makeText(getApplicationContext(), "Email is not valid", Toast.LENGTH_SHORT).show();
//            else if (!(db.usernameValid(username)))
//                Toast.makeText(getApplicationContext(), "username is not valid", Toast.LENGTH_SHORT).show();
//            else {
            try {

                db.register(email, password, age, weight, fullname);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                //String userEmail = db.auth.getInstance().getCurrentUser().getEmail();
                StorageReference scheduleRef = storageRef.child("images/" + email+ ".jpg");
                scheduleRef.putFile(imageUri2)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                imgGallery2.setImageURI(null);
                                Toast.makeText(Register.this, "Successfully uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                db.login(email,password);
                Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);
            }
                catch (Exception e){
                    Toast.makeText(Register.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            //login before intent

        }
        else if(!changedProfile && view == btnRegister)
        {
            Toast.makeText(Register.this, "You must pick a profile picture!", Toast.LENGTH_SHORT).show();

        }
        if (view == btnBack)
        {
            Intent i = new Intent(Register.this, Login.class);
            startActivity(i);
        }
        if(view == btnCamera2)
        {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {
            if (requestCode == GALLERY_REQ_CODE2)
            {
                //for gallery
                //imgGallery.setImageURI(data.getData());
                imageUri2 = data.getData();
                imgGallery2.setImageURI(data.getData());
                changedProfile = true;
            }
        }
    }


}