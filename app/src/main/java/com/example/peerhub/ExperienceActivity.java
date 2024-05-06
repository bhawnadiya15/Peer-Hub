package com.example.peerhub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExperienceActivity extends AppCompatActivity {

    private EditText editDesignation;
    private EditText editCompany;
    private EditText editLocation;
    private EditText fromDateLabel;
    private EditText fromDateLabel2;
    private CheckBox currentlyStudying;
    private Button saveButton;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.experience);

        // Initialize Firebase
        userRef = FirebaseDatabase.getInstance().getReference().child("Users");

        // Initialize EditText fields
        editDesignation = findViewById(R.id.editdesignation);
        editCompany = findViewById(R.id.editcompany);
        editLocation = findViewById(R.id.editlocation);
        fromDateLabel = findViewById(R.id.from_date_label);
        fromDateLabel2 = findViewById(R.id.from_date_label2);
        currentlyStudying = findViewById(R.id.currently_studying);

        // Initialize the Save button
        saveButton = findViewById(R.id.expbtn);

        // Set onClick listener for the Save button
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from EditText fields
                String designation = editDesignation.getText().toString();
                String company = editCompany.getText().toString();
                String location = editLocation.getText().toString();
                String fromDate = fromDateLabel.getText().toString();
                String toDate = fromDateLabel2.getText().toString();
                boolean studyingCurrently = currentlyStudying.isChecked();

                // Get the current user's ID
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String userId = currentUser.getUid();

                    // Retrieve username from the database
                    userRef.child(userId).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String username = dataSnapshot.getValue(String.class);

                            // Save experience data to the database
                            DatabaseReference experienceRef = FirebaseDatabase.getInstance().getReference().child("Experience").child(userId);
                            Experience experience = new Experience(designation, company, location, fromDate, toDate, studyingCurrently, username);
                            experienceRef.setValue(experience);

                            // Display a success message
                            Toast.makeText(ExperienceActivity.this, "Experience data saved successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle database error
                            Toast.makeText(ExperienceActivity.this, "Failed to retrieve username from database", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // User is not authenticated, handle accordingly
                    Toast.makeText(ExperienceActivity.this, "User not authenticated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
