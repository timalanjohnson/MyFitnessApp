package com.example.myfitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WeightActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText editTextUpdateWeight;
    Button buttonUpdateWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        setTitle("Update Weight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        editTextUpdateWeight = findViewById(R.id.editTextUpdateWeight);
        buttonUpdateWeight  = findViewById(R.id.buttonUpdateWeight);
        editTextUpdateWeight.setText(User.getUserWeight());

        UpdateData();
    }

    private void UpdateData() {
        buttonUpdateWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Fetch values from fields.
                User.setUserWeight(editTextUpdateWeight.getText().toString());

                boolean updateData = db.updateUser();

                if (updateData == true){
                    Toast.makeText(WeightActivity.this, "Details successfully updated.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(WeightActivity.this, "Error updating details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
