package com.example.buttonactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class profileFragment extends Fragment implements View.OnClickListener {

    //for camera
    private static final int CAMERA_REQUEST = 1;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    //private FirebaseFirestore db;
    private firebaseDB db = new firebaseDB();
    private TextView textViewFullName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    ImageView profile_picture;
    TextView tvName, tvAge, tvEmail, tvPassword, tvWeight;
    Button btnChangeGallery,btnChangeCamera, btnSaveNew;
    private final int GALLERY_REQ_CODE2 = 1000;
    public ImageView imgGallery3;
    public Uri imageUri3;
    boolean changedPic=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChangeGallery = v.findViewById(R.id.btnChangeGallery);
        btnChangeGallery.setOnClickListener(this);
        btnChangeCamera = v.findViewById(R.id.btnChangeCamera);
        btnChangeCamera.setOnClickListener(this);
        btnSaveNew= v.findViewById(R.id.btnSaveNew);
        btnSaveNew.setOnClickListener(this);
        profile_picture = v.findViewById(R.id.profile_picture);

        tvName = v.findViewById(R.id.tvName);
        textViewEmail = v.findViewById(R.id.tvEmail);
        textViewPassword = v.findViewById(R.id.tvPassword);
        tvWeight = v.findViewById(R.id.tvWeight);
        tvAge = v.findViewById(R.id.tvAge);
        retrieveUserCredentials();

        return v;
    }

    private void retrieveUserCredentials()
    {
        String userEmail = db.auth.getInstance().getCurrentUser().getEmail();
        //String userEmail = db.auth.getCurrentUser().getEmail();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("images/"+userEmail+".jpg");

        try {
            //creating a temporary local file in which we will be storing the image
            //that we will fetch from the firebase storage
            File localFile = File.createTempFile("tempfile", ".jpg");
            storageReference.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            //we'll get the image inside this bitmap variable
                            //using BitmapFactory we could decode the file
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            profile_picture.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        db.fs.collection("users")
                .whereEqualTo("email", userEmail)
                    .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task)
                            {
                                if (task.isSuccessful())
                                {
                                    for (QueryDocumentSnapshot document : task.getResult())
                                    {
                                        String name = document.getString("fullName");
                                        String password = document.getString("password");
                                        String email = document.getString("email");
                                        String age = document.getString("age");
                                        String weight = document.getString("weight");
                                        tvName.setText(name);
                                        tvWeight.setText(weight+"kg");
                                        tvAge.setText(age+" years old");
                                        textViewEmail.setText(email);
                                        textViewPassword.setText(password);
                                    }
                                }
                            }
                        });
    }


@Override
    public void onClick(View view) {
        if (view == btnChangeGallery)
        {
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery, GALLERY_REQ_CODE2);
        }
        if (view == btnChangeCamera)
        {
            //todo: open camera and make pic
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                // Permission is already granted, launch camera
                launchCamera();
            } else {
                // Permission is not granted, request it
                requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            }
        }
        if (view == btnSaveNew)
        {
            //TODO:SAVE NEW PIC TO FIREBASE AND DELTE OLD ONE
            if(changedPic)
            {
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                String userEmail = db.auth.getInstance().getCurrentUser().getEmail();
                StorageReference scheduleRef = storageRef.child("images/"+userEmail+".jpg");
                // Delete the file that already exists to prevent loss of storage
                //there's always a picture already existing.
                scheduleRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "file deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@org.checkerframework.checker.nullness.qual.NonNull Exception exception) {
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
                //upload image
                scheduleRef.putFile(imageUri3)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                //imageSchedule.setImageURI(null);
                                Toast.makeText(getContext(), "Successfully uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@org.checkerframework.checker.nullness.qual.NonNull Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
            else
            {
                Toast.makeText(getContext(), "Didn't change your pic!", Toast.LENGTH_SHORT).show();
            }

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == -1)
        {
            changedPic=true;
            if (requestCode == GALLERY_REQ_CODE2)
            {
                imageUri3 = data.getData();
                profile_picture.setImageURI(imageUri3);
            }
            if (requestCode == CAMERA_REQUEST)
            {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                String fileName = "my_image.jpg";
                File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

                if (storageDir != null) {
                    File imageFile = new File(storageDir, fileName);

                    try {
                        FileOutputStream fos = new FileOutputStream(imageFile);
                        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        fos.flush();
                        fos.close();

                        imageUri3 = Uri.fromFile(imageFile);
                        profile_picture.setImageURI(imageUri3);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    // Method to launch camera
    public void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(requireContext().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        } else {
            Toast.makeText(getContext(), "No camera app available", Toast.LENGTH_SHORT).show();
        }
    }
    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted, launch camera
                launchCamera();
            } else {
                // Permission is denied, show a toast message
                Toast.makeText(getContext(), "Camera permission is required to use the camera", Toast.LENGTH_SHORT).show();
            }
        }
    }
}