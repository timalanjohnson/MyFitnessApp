package com.example.myfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    DatabaseHelper myFitDB;
    Button buttonUpdateDetails;
    EditText editName;
    EditText editEmail;
    EditText editPassword;
    EditText editWeight;
    EditText editHeight;
    EditText editTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        myFitDB = new DatabaseHelper(this);
        buttonUpdateDetails = (Button)findViewById(R.id.buttonUpdateDetails);
        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editWeight = (EditText)findViewById(R.id.editWeight);
        editHeight = (EditText)findViewById(R.id.editHeight);
        editTarget = (EditText)findViewById(R.id.editTarget);

        // Initialize fields with values from the database
        initValues();

        // Update values in the database
        UpdateData();
    }

    private void UpdateData() {
        buttonUpdateDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Update Details", Toast.LENGTH_SHORT).show();
                /*
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String weight = editWeight.getText().toString();
                String height = editHeight.getText().toString();
                String target = editTarget.getText().toString();

                boolean insertData = myFitDB.addData(name, email, password, weight, height, target);

                if (insertData == true){
                    Toast.makeText(ProfileActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ProfileActivity.this, "Error registering user.", Toast.LENGTH_SHORT).show();
                }
                */
            }
        });
    }

    private void initValues() {

        // Get user values from database
        String userName = "Test Name";
        String userEmail = "Test Email";
        String userPassword = "Password";
        String userWeight = "Test Weight";
        String userHeight = "Test Height";
        String userTarget = "Test Target";

        // Populate UI elements with data
        editName.setText(userName);
        editEmail.setText(userEmail);
        editPassword.setText(userPassword);
        editWeight.setText(userWeight);
        editHeight.setText(userHeight);
        editTarget.setText(userTarget);
    }
}
