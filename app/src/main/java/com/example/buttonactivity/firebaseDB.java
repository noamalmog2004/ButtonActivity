package com.example.buttonactivity;

import android.app.DownloadManager;
import android.net.Uri;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class firebaseDB {
    private FirebaseAuth auth;
    private FirebaseFirestore fs;
    public static User currentUser = null;
    static enum SIGNUP_RESULTS {
        SUCCESS,
        EMAIL_EXISTS,
        INVALID_USERNAME,
        INVALID_PASSWORD,
        INVALID_NAME,

    }

    class User
    {
        public String email, password, fullName, username;
        public FirebaseUser user;
        User(String email, String password, String fullName, String username, FirebaseUser user)
        {
            this.email = email;
            this.password = password;
            this.fullName = fullName;
            this.username = username;
            this.user = user;
        }
        User(String email, String password, String fullName, String username)
        {
            this.email = email;
            this.password = password;
            this.fullName = fullName;
            this.username = username;
            this.user = null;
        }
    }


    public firebaseDB()
    {
        this.auth = FirebaseAuth.getInstance();
        this.fs = FirebaseFirestore.getInstance();
    }
    public SIGNUP_RESULTS register(String email, String password, String fullName, String username)
    {
        User user = new User(email, password, fullName, username);
        boolean exists;
        Task<SignInMethodQueryResult> task =  this.auth.fetchSignInMethodsForEmail(email);
        boolean isNewUser =  task.getResult().getSignInMethods().isEmpty();
        if(isNewUser)
        {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    int x = 5;
                }
            });
            fs.collection("users").document(email).set(user);
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
        firebaseDB.currentUser = new User(email, password, "", "", this.auth.getCurrentUser());
        return true;
    }

    public void logout()
    {
        this.auth.signOut();
    }




    public boolean fullnameValid(String fullname)
    {


        if (fullname.length() == 0) {
            return false;
        }
        for (int i = 0; i < fullname.length(); i++) {
            if (!(fullname.charAt(i) >= 'a' && fullname.charAt(i) <= 'z' || fullname.charAt(i) >= 'A' && fullname.charAt(i) <= 'Z')) {
                return false;
            }
        }
        return true;
    }
    public  boolean emailValid(String email)
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
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (password.isEmpty())
            return false;
        else if (!password.matches(checkPassword))
            return false;
        else
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
    class UsernameValidator {
        public static final String regularExpression = "^[a-zA-Z][a-zA-Z0-9_]{6,19}$";
    }
    public boolean usernameValid(String userName)
    {
        if (userName.matches(UsernameValidator.regularExpression)) {
            return true;
        }
        return false;
    }

}
