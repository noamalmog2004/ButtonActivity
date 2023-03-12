package com.example.buttonactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.io.File;
import java.io.IOException;

public class profileFragment extends Fragment implements View.OnClickListener {


    //private FirebaseFirestore db;
    private firebaseDB db = new firebaseDB();
    private TextView textViewFullName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    ImageView profile_picture;
    TextView tvName, tvAge, tvEmail, tvPassword, tvWeight;
    Button btnChange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChange = v.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);

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
        if (view == btnChange)
        {
            Intent i = new Intent(getActivity(), ChangeInformation.class);
            startActivity(i);
        }
    }
}