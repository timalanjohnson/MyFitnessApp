package com.example.myfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button buttonUpdateDetails;
    EditText editName;
    EditText editPassword;
    EditText editWeight;
    EditText editHeight;
    EditText editTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Your Profile");

        db = new DatabaseHelper(this);
        buttonUpdateDetails = findViewById(R.id.buttonUpdateDetails);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        editTarget = findViewById(R.id.editTarget);

        // Initialize fields with values from the database
        initValues();

        // Update values in the database
        UpdateData();
    }

    private void UpdateData() {
        buttonUpdateDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Fetch values from fields.
                User.setUsername(editName.getText().toString());
                User.setUserPassword(editPassword.getText().toString());
                User.setUserWeight(editWeight.getText().toString());
                User.setUserHeight(editHeight.getText().toString());
                User.setUserTarget(editTarget.getText().toString());

                boolean updateData = db.updateUser();

                if (updateData == true){
                    Toast.makeText(ProfileActivity.this, "Details successfully updated.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Error updating details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initValues() {

        // Populate UI elements with data from User class
        editName.setText(User.getUsername());
        editPassword.setText(User.getUserPassword());
        editWeight.setText(User.getUserWeight());
        editHeight.setText(User.getUserHeight());
        editTarget.setText(User.getUserTarget());
    }
}
