package com.example.myfitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegisterUserActivity.class.getSimpleName();

    DatabaseHelper myFitDB;
    Button buttonRegister;
    EditText editName;
    EditText editEmail;
    EditText editPassword;
    EditText editWeight;
    EditText editHeight;
    EditText editTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        myFitDB = new DatabaseHelper(this);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);
        editName = (EditText)findViewById(R.id.editName);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editWeight = (EditText)findViewById(R.id.editWeight);
        editHeight = (EditText)findViewById(R.id.editHeight);
        editTarget = (EditText)findViewById(R.id.editTarget);

        AddData();
    }

    public void AddData(){
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String weight = editWeight.getText().toString();
                String height = editHeight.getText().toString();
                String target = editTarget.getText().toString();

                boolean insertData = myFitDB.addData(name, email, password, weight, height, target);

                if (insertData == true){
                    Toast.makeText(RegisterUserActivity.this, "Registered successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterUserActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterUserActivity.this, "Error registering user.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
