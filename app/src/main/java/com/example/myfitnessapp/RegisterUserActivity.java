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

    DatabaseHelper myFitDB;
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

        myFitDB = new DatabaseHelper(this);
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
                String password = "'" + editPassword.getText().toString() + "'";
                String weight = editWeight.getText().toString();
                String height = editHeight.getText().toString();
                String target = editTarget.getText().toString();

                boolean userExists = myFitDB.checkUser(username);

                if (userExists){
                    Toast.makeText(RegisterUserActivity.this, "Sorry, that username is already taken", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insertData = myFitDB.addUser(username, password, weight, height, target);

                    if (insertData == true){
                        Toast.makeText(RegisterUserActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();
                        //myFitDB.makeUser(username);
                        Intent intent = new Intent(RegisterUserActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(RegisterUserActivity.this, "Error registering user.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
