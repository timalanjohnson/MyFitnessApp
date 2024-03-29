package com.example.myfitnessapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class WeightActivity extends AppCompatActivity {

    DatabaseHelper db;
    LineChart weightGraph;
    EditText editTextUpdateWeight;
    EditText editTextUpdateWeightGoal;
    Button buttonUpdateWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        setTitle("Update Weight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        weightGraph = findViewById(R.id.weightGraph);
        editTextUpdateWeight = findViewById(R.id.editTextUpdateWeight);
        editTextUpdateWeightGoal = findViewById(R.id.editTextUpdateWeightGoal);
        editTextUpdateWeight.setText(User.getUserWeight());
        editTextUpdateWeightGoal.setText(User.getUserWeightGoal());
        buttonUpdateWeight  = findViewById(R.id.buttonUpdateWeight);

        UpdateGraph();
        UpdateData();
    }

    private void UpdateGraph() {

        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXEStarget = new ArrayList<>();
        ArrayList<Entry> yAXEScurrent = new ArrayList<>();

        double x = 0 ;
        int numDataPoints = 100;

        for(int i=0;i<numDataPoints;i++){
            float target = Float.parseFloat(User.getUserWeightGoal());
            float current = Float.parseFloat(User.getUserWeight());
            x = x + 10;
            yAXEStarget.add(new Entry(target,i));
            yAXEScurrent.add(new Entry(current,i));
            xAXES.add(i, String.valueOf(x));
        }

        String[] xaxes = new String[xAXES.size()];
        for(int i=0; i<xAXES.size();i++){
            xaxes[i] = xAXES.get(i).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAXEStarget,"Target");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.RED);

        LineDataSet lineDataSet2 = new LineDataSet(yAXEScurrent,"Current");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.WHITE);


        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);

        weightGraph.setData(new LineData(xaxes,lineDataSets));

        weightGraph.setVisibleXRangeMaximum(65f);
    }

    private void UpdateData() {
        buttonUpdateWeight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Fetch values from fields.
                User.setUserWeight(editTextUpdateWeight.getText().toString());
                User.setUserWeightGoal(editTextUpdateWeightGoal.getText().toString());

                boolean updateData = db.updateUser();

                if (updateData == true){
                    Toast.makeText(WeightActivity.this, "Details successfully updated.", Toast.LENGTH_SHORT).show();
                    UpdateGraph();
                }
                else {
                    Toast.makeText(WeightActivity.this, "Error updating details.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
