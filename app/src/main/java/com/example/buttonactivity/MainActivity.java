package com.example.buttonactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    // TODO: 05/12/2022 FIX HOME FRAGMENT
    BottomNavigationView bottomNavigationView;
    profileFragment profileFragment = new profileFragment();
    HomeFragment homeFragment = new HomeFragment();
    settingsFragment settingsFragment = new settingsFragment();
    GymFragment gymFragment = new GymFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,gymFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId()){

                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;
                    case R.id.logout:
                        //HERE INTENT TO LOGIN PAGE
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
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }



}