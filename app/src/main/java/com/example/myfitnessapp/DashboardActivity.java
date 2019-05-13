package com.example.myfitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button buttonDashProfile;
    Button buttonDashGoals;
    Button buttonDashWeight;
    Button buttonDashPhoto;
    Button buttonDashSettings;
    Button buttonDashLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        buttonDashProfile = (Button)findViewById(R.id.buttonDashProfile);
        buttonDashGoals = (Button)findViewById(R.id.buttonDashGoals);
        buttonDashWeight = (Button)findViewById(R.id.buttonDashWeight);
        buttonDashPhoto = (Button)findViewById(R.id.buttonDashPhoto);
        buttonDashSettings = (Button)findViewById(R.id.buttonDashSettings);
        buttonDashLogout = (Button)findViewById(R.id.buttonDashLogout);

        // Launches corresponding activity on clicking a button.
        launchProfile();
        launchGoals();
        launchWeight();
        launchPhoto();
        launchSettings();
        logout();
    }

    private void launchProfile() {
        buttonDashProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchGoals() {
        buttonDashGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, GoalsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchWeight() {
        buttonDashWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, WeightActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchPhoto() {
        buttonDashPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void launchSettings() {
        buttonDashSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logout() {
        buttonDashLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
