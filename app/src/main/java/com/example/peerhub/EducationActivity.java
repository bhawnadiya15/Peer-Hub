package com.example.peerhub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EducationActivity extends AppCompatActivity {

    private EditText universityCollegeEditText;
    private EditText degreeSpecializationEditText;
    private EditText fromDateEditText;
    private EditText toDateEditText;
    private CheckBox currentlyStudyingCheckBox;
    private EditText cgpaPercentageEditText;
    private Button saveButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education);

        // Initialize views
        universityCollegeEditText = findViewById(R.id.university_college);
        degreeSpecializationEditText = findViewById(R.id.degree_specialization);
        fromDateEditText = findViewById(R.id.from_date_label);
        toDateEditText = findViewById(R.id.from_date_label2);
        currentlyStudyingCheckBox = findViewById(R.id.currently_studying);
        cgpaPercentageEditText = findViewById(R.id.cgpa_percentage);
        saveButton = findViewById(R.id.save_button);

        // Initialize Firebase
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // Get username from Firebase
            String username = currentUser.getDisplayName();
            // Initialize database reference
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Education").child(username);
        }

        // Set click listener for the save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText fields
                String universityCollege = universityCollegeEditText.getText().toString();
                String degreeSpecialization = degreeSpecializationEditText.getText().toString();
                String fromDate = fromDateEditText.getText().toString();
                String toDate = toDateEditText.getText().toString();
                boolean currentlyStudying = currentlyStudyingCheckBox.isChecked();
                String cgpaPercentage = cgpaPercentageEditText.getText().toString();

                // Create an Education object
                Education education = new Education(universityCollege, degreeSpecialization, fromDate, toDate, currentlyStudying, cgpaPercentage);

                // Save data to database
                databaseReference.push().setValue(education);

                // Show a toast message indicating successful save
                Toast.makeText(EducationActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
