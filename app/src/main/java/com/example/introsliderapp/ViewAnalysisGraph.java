package com.example.introsliderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introsliderapp.model.AdminAnalysis;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.introsliderapp.AdminViewInstitute.negativeScore;
import static com.example.introsliderapp.AdminViewInstitute.neutralScore;
import static com.example.introsliderapp.AdminViewInstitute.positiveScore;

public class ViewAnalysisGraph extends AppCompatActivity {


    private static final String TAG = "My tag";
    private String instituteType;
    private String instituteName;
    private TextView textViewAnalysis;
    private ArrayList<PieEntry> pieEntries = new ArrayList<>();
    PieChart pieChart;
    private DatabaseReference mRef;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_analysis_graph);

        Intent intent = getIntent();
        instituteName = intent.getStringExtra("childClicked");
        instituteType = intent.getStringExtra("groupClicked");

        Log.d(TAG,"institute name = "+instituteName);
        Log.d(TAG,"institute type = " + instituteType);

        initViews();
        setSupportActionBar(toolbar);
        toolbar.setTitle("PERFORMANCE : ");
        toolbar.setTitleTextColor(Color.WHITE);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        //setting pie entry values:
        pieEntries.add(new PieEntry(Float.parseFloat(Integer.toString(positiveScore)),"Positive Sentiment"));
        pieEntries.add(new PieEntry(Float.parseFloat(Integer.toString(negativeScore)),"Negative Sentiment"));
        pieEntries.add(new PieEntry(Float.parseFloat(Integer.toString(neutralScore)),"Neutral Sentiment"));

        //creating pie datasets:
        PieDataSet pieDataSet= new PieDataSet(pieEntries,"Customer Sentiment");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        //creating pi data:
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);

    }



    private void initViews(){
        pieChart = this.findViewById(R.id.pie_chart);
        toolbar = this.findViewById(R.id.toolBar_analysis_view);

    }

    //function to map the main menu layout to our layout:
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_analysis,menu);
        return true;
    }
    //function to map the main menu layout to our layout ends here:


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch(item.getItemId()){
            case R.id.barGraph:
                Toast.makeText(ViewAnalysisGraph.this,"BAR GRAPH",
                        Toast.LENGTH_SHORT).show();
                showBarGraph();
                break;
            case R.id.lineGraph_yearlyPerformance:
                Toast.makeText(ViewAnalysisGraph.this,"LINE GRAPH",
                        Toast.LENGTH_SHORT).show();
                showLineGraph();
                break;
            case R.id.registered_student:
                Toast.makeText(ViewAnalysisGraph.this,"LINE GRAPH 2",
                        Toast.LENGTH_SHORT).show();
                showNextLineGraph();
                break;
            case R.id.radarChart:
                Toast.makeText(ViewAnalysisGraph.this,"RADAR GRAPH 2",
                        Toast.LENGTH_SHORT).show();
                showRadarGraph();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showRadarGraph() {

        Intent intent = new Intent(ViewAnalysisGraph.this,RadarChartActivity.class);
        intent.putExtra("Institute name =",instituteName);
        intent.putExtra("Institute type = ",instituteType);
        startActivity(intent);


    }

    private void showNextLineGraph() {
    }

    private void showBarGraph() {

        Intent intent = new Intent(ViewAnalysisGraph.this,BarGraphActivity.class);
        intent.putExtra("Institute name",instituteName);
        intent.putExtra("Institute type",instituteType);
        startActivity(intent);
    }

    private void showLineGraph() {
        startActivity(new Intent(ViewAnalysisGraph.this,YearlyPerformanceOfInstitute.class));
        this.finish();
    }
}
