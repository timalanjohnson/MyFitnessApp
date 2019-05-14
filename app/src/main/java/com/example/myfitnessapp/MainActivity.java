package com.example.myfitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myFitDB;
    EditText txtUsername;
    EditText txtPassword;
    Button buttonNewLogin;
    Button buttonNewRegister;
    String username;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFitDB = new DatabaseHelper(this);
        txtUsername = findViewById(R.id.editTextLoginUsername);
        txtPassword = findViewById(R.id.editTextLoginPassword);
        buttonNewLogin = findViewById(R.id.buttonNewLogin);
        buttonNewRegister = findViewById(R.id.buttonNewRegister);

        launchLogin();
        launchRegister();
    }

    // Validates the users credentials
    private void validate() {
        username = txtUsername.getText().toString();
        password = txtPassword.getText().toString();

        boolean userExists = myFitDB.checkUser(username, password);

        if (userExists) {
            createUser();
            launchDashboard();
        } else {
            Toast.makeText(MainActivity.this, "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
        }
    }

    private void createUser() {

        myFitDB.makeUser(username);

        String username = "username";
        String email = "TestEmail";
        String password = "pass";
        String weight = "85";
        String height = "177";
        String target = "5000";

        // Creates local user for convenience
        User.setUsername(username);
        User.setUserEmail(email);
        User.setUserPassword(password);
        User.setUserWeight(weight);
        User.setUserHeight(height);
        User.setUserTarget(target);
    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

    private void launchLogin() {
        buttonNewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            validate();
            }
        });
    }

    private void launchDashboard(){
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    private void launchRegister() {
        buttonNewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

}
