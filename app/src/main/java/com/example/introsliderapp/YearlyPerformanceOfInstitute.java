package com.example.introsliderapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class YearlyPerformanceOfInstitute extends AppCompatActivity {


    private static final String TAG = "my tag in yearly performance";
    private LineChart lineChart;
    private Toolbar toolbar;
    ArrayList<Entry> lineChartEntries = new ArrayList<>();
    ArrayList<ILineDataSet> dataset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_performance_of_institute);

        initViews();
        toolbar.setTitle("YEARLY PERFORMANCE");
        toolbar.setTitleTextColor(Color.WHITE);

        FirebaseAnalytics mAnalytics = FirebaseAnalytics.getInstance(this);

        //lineChart.setOnChartGestureListener(YearlyPerformanceOfInstitute.this);
        //lineChart.setOnChartValueSelectedListener(YearlyPerformanceOfInstitute.this);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(false);

        //setting the limit line:
        LimitLine lowerLimit = new LimitLine(20f);
        lowerLimit.setLineWidth(1f);
        lowerLimit.setLineColor(Color.BLUE);
        lowerLimit.enableDashedLine(10f, 5f, 0f);
        lowerLimit.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);

        //code for removing the left Y axis:
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.removeAllLimitLines();
        leftAxis.addLimitLine(lowerLimit);
        leftAxis.setAxisMaximum(0f);
        leftAxis.setAxisMaximum(100f);

        lineChart.getAxisRight().setEnabled(false);

        //adding values to the line data entries list:
        lineChartEntries.add(new Entry(0, 60f));
        lineChartEntries.add(new Entry(1, 50f));
        lineChartEntries.add(new Entry(2, 70f));
        lineChartEntries.add(new Entry(3, 30f));
        lineChartEntries.add(new Entry(4, 100f));
        lineChartEntries.add(new Entry(5, 50f));
        lineChartEntries.add(new Entry(6, 20f));
        lineChartEntries.add(new Entry(7, 80f));
        lineChartEntries.add(new Entry(8, 40f));
        lineChartEntries.add(new Entry(9, 10f));

        //checking for too low performance and logging event:
        for(int i=0;i<lineChartEntries.size();i++){
            Entry entry = lineChartEntries.get(i);
            if(entry.getY() <= lowerLimit.getLimit()){
                Bundle params = new Bundle();
                mAnalytics.logEvent("Performance_too_low",params);
            }
        }


        //preparing data set
        LineDataSet set1 = new LineDataSet(lineChartEntries, "DATA SET 1");
        set1.setFillAlpha(110);
        set1.setColor(Color.RED);
        set1.setLineWidth(2f);
        set1.setValueTextSize(5f);
        set1.setCircleColor(Color.BLUE);
        set1.notifyDataSetChanged();

        //adding data set and preparing data:
        dataset.add(set1);
        LineData data = new LineData(dataset);

        //setting data:
        lineChart.setData(data);

        //creating custom axis values:
        final String[] years = {"2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027"};
        XAxis xAxis = lineChart.getXAxis();
        /*xAxis.setValueFormatter(new  IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return years[(int) value]; // xVal is a string array
            }
        });*/



    }
    private void initViews () {
        lineChart = this.findViewById(R.id.line_chart_yearly_performance);
        toolbar = this.findViewById(R.id.yearly_performance_line_chart);

    }
}

