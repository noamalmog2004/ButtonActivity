package com.example.buttonactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
public class profileFragment extends Fragment implements View.OnClickListener {


    //private FirebaseFirestore db;
    private firebaseDB db = new firebaseDB();
    private TextView textViewFullName;
    private TextView textViewEmail;
    private TextView textViewPassword;
    TextView tvName, tvAge, tvEmail, tvPassword;
    Button btnChange;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        btnChange = v.findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);

        tvName = v.findViewById(R.id.tvName);
        textViewEmail = v.findViewById(R.id.tvEmail);
        textViewPassword = v.findViewById(R.id.tvPassword);
        retrieveUserCredentials();

        return v;
    }

    private void retrieveUserCredentials()
    {
        String userEmail = db.auth.getInstance().getCurrentUser().getEmail();
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
                                        String email = document.getString("email");
                                        String password = document.getString("password");
                                        tvName.setText(name);
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