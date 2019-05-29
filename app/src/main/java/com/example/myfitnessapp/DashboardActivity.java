package com.example.myfitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DashboardActivity extends AppCompatActivity {

    ImageButton imageButtonProfile;
    ImageButton imageButtonGoals;
    ImageButton imageButtonWeight;
    ImageButton imageButtonSteps;
    ImageButton imageButtonPhoto;
    ImageButton imageButtonSettings;
    ImageButton imageButtonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageButtonProfile = findViewById(R.id.imageButtonProfile);
        imageButtonGoals = findViewById(R.id.imageButtonGoals);
        imageButtonWeight = findViewById(R.id.imageButtonWeight);
        imageButtonSteps = findViewById(R.id.imageButtonSteps);
        imageButtonPhoto = findViewById(R.id.imageButtonPhoto);
        imageButtonSettings = findViewById(R.id.imageButtonSettings);
        imageButtonLogout = findViewById(R.id.imageButtonLogout);

        // Launches corresponding activity on clicking a button.
        launchProfile();
        launchGoals();
        launchWeight();
        launchStepCounter();
        launchPhoto();
        launchSettings();
        logout();
    }

    private void launchProfile() {
        imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchGoals() {
        imageButtonGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, GoalsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchWeight() {
        imageButtonWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, WeightActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchStepCounter(){
        imageButtonSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, StepCounterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchPhoto() {
        imageButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchSettings() {
        imageButtonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        imageButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
