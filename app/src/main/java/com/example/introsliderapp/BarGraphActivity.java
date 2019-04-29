package com.example.introsliderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static  com.example.introsliderapp.MainActivity.iitjeeAvg;
import static  com.example.introsliderapp.MainActivity.medicalAvg;
import static  com.example.introsliderapp.MainActivity.neetPGAvg;
import static  com.example.introsliderapp.MainActivity.engineeringAvg;
import static  com.example.introsliderapp.MainActivity.comerceAvg;
import static  com.example.introsliderapp.MainActivity.bankAvg;
import static  com.example.introsliderapp.MainActivity.upscAvg;
import static  com.example.introsliderapp.MainActivity.greAvg;
import static  com.example.introsliderapp.MainActivity.managementAvg;
import static  com.example.introsliderapp.MainActivity.placementAvg;
import static  com.example.introsliderapp.MainActivity.scienceAvg;

public class BarGraphActivity extends AppCompatActivity {

    private static final String TAG ="my tag" ;
    private String instituteType;
    private String instituteName;
    private BarChart barChart;
    private Toolbar toolbar;
    private int barCount;
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    BarDataSet set;
    BarData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);

        Intent intent = getIntent();
        instituteName = intent.getStringExtra("Institute name");
        instituteType = intent.getStringExtra("Institute type");

        initViews();
        toolbar.setTitle("COMPARATIVE ANALYSIS");
        toolbar.setTitleTextColor(Color.WHITE);
        getNumberOfBars();
        Log.d(TAG,"bar count = "+Integer.toString(barCount));

        barChart.getDescription().setEnabled(false);



    }



    private void getNumberOfBars() {

        barEntries.clear();

        switch(instituteType){
            case "IIT-JEE":
                barCount = (iitjeeAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = iitjeeAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "Medical Entrance Exams":
                barCount = (medicalAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = medicalAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "GATE-IES-ESE":
                barCount = (engineeringAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = engineeringAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "NEET-PG":
                barCount = (neetPGAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = neetPGAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "Comerce":
                barCount = (comerceAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = comerceAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "JRF-NET":
                barCount = (scienceAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = scienceAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "UPSC-ICS":
                barCount = (upscAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = upscAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "BANK-SBI-PO":
                barCount = (bankAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = bankAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "College Placements":
                barCount = (placementAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = placementAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "GRE-IELTS":
                barCount = (greAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = greAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;
            case "CAT-MAT":
                barCount = (managementAvg.size()/3);
                //setting data to bar chart:
                for(int i=0;i<barCount;i++){
                    float value = managementAvg.get(i);
                    barEntries.add(new BarEntry(i,value));
                }
                set = new BarDataSet(barEntries,"Data Set");
                set.setColors(ColorTemplate.MATERIAL_COLORS);
                data = new BarData(set);
                barChart.setData(data);
                barChart.invalidate();
                barChart.animateY(500);
                break;

        }

    }

    private void initViews() {
        barChart = this.findViewById(R.id.bar_graph);
        toolbar = this.findViewById(R.id.toolbar_barGraph);

    }
}
