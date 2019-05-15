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
    EditText editTarget;

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
        editTarget = findViewById(R.id.editTarget);

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
                String target = editTarget.getText().toString();

                boolean userExists = false;//db.checkUser(username);

                if (userExists){
                    Toast.makeText(RegisterUserActivity.this, "Sorry, that username is already taken", Toast.LENGTH_SHORT).show();
                } else {

                    boolean insertData = db.addUser(username, password, weight, height, target);
                    Toast.makeText(RegisterUserActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();

                    User.setUsername(username);
                    User.setUserPassword(password);
                    User.setUserWeight(weight);
                    User.setUserHeight(height);
                    User.setUserTarget(target);

                    Intent intent = new Intent(RegisterUserActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
