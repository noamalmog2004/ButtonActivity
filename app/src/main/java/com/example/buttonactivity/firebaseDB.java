package com.example.buttonactivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class firebaseDB {
    public FirebaseAuth auth;
    public FirebaseFirestore fs;
    public FirebaseStorage firebaseStorage;
    public FirebaseDatabase fd;
    public static User currentUser = null;
    static enum SIGNUP_RESULTS {
        SUCCESS,
        EMAIL_EXISTS,
    }
    class User {
        public String email, password, fullName, weight, age;
        public FirebaseUser user;

        User(String email, String password, String age, String weight, String fullName)
        {
            this.email = email;
            this.password = password;
            this.fullName = fullName;
            this.weight = weight;
            this.age = age;
            this.user = null;
        }
    }


    public firebaseDB()
    {
        this.auth = FirebaseAuth.getInstance();
        this.fs = FirebaseFirestore.getInstance();
        this.firebaseStorage = FirebaseStorage.getInstance();
        this.fd = FirebaseDatabase.getInstance();
    }


    public SIGNUP_RESULTS register(String email, String password, String age, String weight, String fullname)
    {
        String a="";
        User user = new User(email, password, age, weight, fullname);
        Task<SignInMethodQueryResult> task =  this.auth.fetchSignInMethodsForEmail(email);
        while(!task.isComplete());
        boolean isNewUser = task.getResult().getSignInMethods().isEmpty();
        if(isNewUser)
        {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String a = "ok";
                }
            });
            fs.collection("users").document(email).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    System.out.println("MADE IT");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("ERROR");
                            System.out.println(e.toString());
                            String a = "not ok";


                        }
                    });
            System.out.println(a);
            return SIGNUP_RESULTS.SUCCESS;
        }
        return SIGNUP_RESULTS.EMAIL_EXISTS;

    }

    public boolean login(String email, String password)
    {
        Task<AuthResult> task = this.auth.signInWithEmailAndPassword(email, password);
        while(!task.isComplete()) {}
        if(!task.isSuccessful())
        {
            return false;
        }
        //firebaseDB.currentUser = new User(email, password, "", "","");
        return true;
    }

    public void logout()
    {
        this.auth.signOut();
        firebaseDB.currentUser = null;
    }

    public boolean fullnameValid(String fullName) {
        // Split the full name into first and last name
        String[] names = fullName.trim().split("\\s+");

        // Check if there are exactly two names
        if (names.length != 2) {
            return false;
        }

        // Check if each name is non-empty and contains only letters
        for (String name : names) {
            if (name.isEmpty() || !name.matches("[a-zA-Z]+")) {
                return false;
            }
        }

        return true;
    }
    public boolean emailValid(String email)
    {
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";
        if (email.isEmpty())
            return false;
        else if (!email.matches(checkEmail))
            return false;
        return true;
    }

    public boolean passwordValid(String password)
    {
        //this.auth.createUserWithEmailAndPassword(email, password);
        if (password.isEmpty())
            return false;
        else if (password.length() < 6)
            return false;
        return true;
    }
    public boolean ageValid(String age)
    {
        //IF I == 2, AGE IS 100+
        if (age.length()>2)
            return false;

        for (int i = 0; i < age.length(); i++)
        {
            if(!(age.charAt(i)>'0' && age.charAt(i)<'9'))
                return false;
        }
        return true;
    }
    public boolean weightValid(String weight) {
        try {
            double weightValue = Double.parseDouble(weight);
            return weightValue > 0 && weightValue <= 250;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
