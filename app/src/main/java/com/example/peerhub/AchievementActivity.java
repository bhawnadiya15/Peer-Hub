package com.example.peerhub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AchievementActivity extends AppCompatActivity {

    private EditText organizationEditText;
    private EditText achievementEditText;
    private EditText dateEditText;
    private EditText urlEditText;
    private Button saveButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievements);

        organizationEditText = findViewById(R.id.organization);
        achievementEditText = findViewById(R.id.achievement);
        dateEditText = findViewById(R.id.date_picker);
        urlEditText = findViewById(R.id.cgpa_percentage);
        saveButton = findViewById(R.id.achievementibtn);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("achievements");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAchievement();
            }
        });
    }

    private void saveAchievement() {
        String organization = organizationEditText.getText().toString().trim();
        String achievement = achievementEditText.getText().toString().trim();
        String date = dateEditText.getText().toString().trim();
        String url = urlEditText.getText().toString().trim();

        String id = databaseReference.push().getKey();
        Achievement newAchievement = new Achievement(organization, achievement, date, url);

        if (id != null) {
            databaseReference.child(id).setValue(newAchievement);
        }

        // You can add a success message or other functionality here
    }
}
