package com.example.peerhub;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ChatActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    chat chat;
    chat profie;
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        chat = new chat();
        profie = new chat();
        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item) {
                return false;
            }
        });
    }
}
