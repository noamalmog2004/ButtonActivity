package com.example.buttonactivity;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class firebaseDB {
    private FirebaseAuth auth;
    private FirebaseFirestore fs;
    public firebaseDB()
    {
        this.auth = FirebaseAuth.getInstance();
        this.fs = FirebaseFirestore.getInstance();
    }
    public void register(String password, String email, String age, String firstname)
    {
        User user = new User(firstname, email, password, age);
        this.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

            }
        });
        this.fs.collection("users").document(email).set(user);
    }
    class User
    {
        public String firstname;
        public String password;
        public String email;
        public String age;
        User( String firstname,  String email,  String password, String age)
        {
            this.age = age;
            this.firstname = firstname;
            this.email = email;
            this.password = password;
        }
    }
    public boolean firstnameValid(String firstname)
    {
        if (firstname.length() == 0) {
            return false;
        }
        for (int i = 0; i < firstname.length(); i++) {
            if (!(firstname.charAt(i) > 'a' && firstname.charAt(i) < 'z' || firstname.charAt(i) > 'A' && firstname.charAt(i) < 'Z')) {
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
        String checkPassword = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
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
}
