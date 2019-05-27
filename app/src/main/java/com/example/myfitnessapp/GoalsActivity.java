package com.example.myfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GoalsActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editWeightGoal;
    EditText editStepGoal;
    Button buttonSaveStepGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        setTitle("Goals");

        db = new DatabaseHelper(this);

        editWeightGoal = findViewById(R.id.editTextWeightGoal);

        editStepGoal = findViewById(R.id.editTextStepGoal);

        editWeightGoal.setText(User.getUserWeightGoal());

        editStepGoal.setText(User.getUserStepGoal());

        buttonSaveStepGoal  = findViewById(R.id.buttonSaveStepGoal);

        UpdateData();
    }

    private void UpdateData() {
        buttonSaveStepGoal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Fetch values from fields.
                User.setUserWeightGoal(editWeightGoal.getText().toString());
                User.setUserStepGoal(editStepGoal.getText().toString());

                boolean updateData = db.updateUser();

                if (updateData == true){
                    Toast.makeText(GoalsActivity.this, "Details successfully updated.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(GoalsActivity.this, "Error updating details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
