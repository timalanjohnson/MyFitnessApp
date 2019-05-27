package com.example.myfitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterUserActivity.class.getSimpleName();

    DatabaseHelper db;
    Button buttonRegister;
    EditText editName;
    EditText editPassword;
    EditText editWeight;
    EditText editHeight;
    EditText editWeightGoal;
    EditText editStepGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        setTitle("Register");

        db = new DatabaseHelper(this);
        buttonRegister = findViewById(R.id.buttonRegister);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        editWeightGoal = findViewById(R.id.editWeightGoal);
        editStepGoal = findViewById(R.id.editStepGoal);

        AddUser();
    }

    public void AddUser(){
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String username = editName.getText().toString();
                String password = editPassword.getText().toString();
                String weight = editWeight.getText().toString();
                String height = editHeight.getText().toString();
                String weight_goal = editWeightGoal.getText().toString();
                String step_goal = editStepGoal.getText().toString();

                boolean userExists = db.checkUser(username);

                if (userExists){
                    Toast.makeText(RegisterUserActivity.this, "Sorry, that username is already taken", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Create user in the database
                    db.addUser(username, password, weight, height, weight_goal, step_goal);

                    // Populate static User with relevant details
                    db.makeUser(username);

                    Toast.makeText(RegisterUserActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();

                    // Log into the dashboard
                    Intent intent = new Intent(RegisterUserActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
