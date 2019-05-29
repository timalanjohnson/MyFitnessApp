package com.example.myfitnessapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;

    TextView textStepCount;
    ProgressBar progressbarStepGoal;

    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);
        setTitle("Step Count");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textStepCount = findViewById(R.id.textStepCount);
        progressbarStepGoal = findViewById(R.id.progressbarStepGoal);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        int stepGoal = Integer.parseInt(User.getUserStepGoal());
        progressbarStepGoal.setMax(stepGoal);
    }

    @Override
    protected void onResume(){
        super.onResume();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null) {
            sensorManager.registerListener(this, countSensor, sensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(this, "Sensor not found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        running = false;
        // If you unregister the hardware will stop detecting steps
        // sensorManager.unregister(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            textStepCount.setText(String.valueOf(event.values[0])); // Float

            int stepCount = Math.round(event.values[0]);

            progressbarStepGoal.setProgress(stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
