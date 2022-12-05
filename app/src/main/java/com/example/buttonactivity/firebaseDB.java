package com.example.buttonactivity;

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
    public void register(String password, String email)
    {
        this.auth.createUserWithEmailAndPassword(email, password);
    }

}
