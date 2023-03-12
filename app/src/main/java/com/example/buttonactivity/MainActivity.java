package com.example.buttonactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.buttonactivity.firebaseDB;
public class MainActivity extends AppCompatActivity {




    BottomNavigationView bottomNavigationView;
    profileFragment profileFragment = new profileFragment();
    HomeFragment homeFragment = new HomeFragment();
    //settingsFragment settingsFragment = new settingsFragment();
    GymFragment gymFragment = new GymFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId()){

                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.logout:
                        firebaseDB db = new firebaseDB();
                        db.logout();
                        //onBackPressed();
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;
                    case R.id.gym:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,gymFragment).commit();
                        return true;
                    case R.id.calendar:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,calendarFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}