package com.example.myfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GoalsActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editTarget;
    Button buttonSaveStepGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        setTitle("Goals");

        db = new DatabaseHelper(this);
        editTarget = findViewById(R.id.editTextStepGoal);
        buttonSaveStepGoal  = findViewById(R.id.buttonSaveStepGoal);
        editTarget.setText(User.getUserTarget());

        UpdateData();
    }

    private void UpdateData() {
        buttonSaveStepGoal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Fetch values from fields.
                User.setUserTarget(editTarget.getText().toString());

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
