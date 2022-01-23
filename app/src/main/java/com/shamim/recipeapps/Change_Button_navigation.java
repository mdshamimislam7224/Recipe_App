package com.shamim.recipeapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Change_Button_navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__button_navigation);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Birani_recipe()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.next:
                            selectedFragment = new Birani_recipe();
                            Toast.makeText(Change_Button_navigation.this, "Next", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.previous:
                            selectedFragment = new Birani_recipe();
                            Toast.makeText(Change_Button_navigation.this, "Previous", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.home1:
                            Intent intent = new Intent(Change_Button_navigation.this, MainActivity.class);
                            startActivity(intent);
                            break;
                    }
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}