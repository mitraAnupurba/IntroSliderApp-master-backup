package com.example.introsliderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.introsliderapp.model.IITJEEInstitute;
import com.example.introsliderapp.model.ScienceExamInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.scienceExam;

public class ScienceExamInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewScience;
    private final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_exam_institutes_view);

        toolBar = this.findViewById(R.id.jrfnet_toolbar);
        listViewScience = this.findViewById(R.id.jrfnet_listView);

        toolBar.setTitle("JRF NET COACHING INSTITUTE'S: ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting :");
        Log.d(TAG,scienceExam.toString());
        ScienceExamInstitute institute =
                new ScienceExamInstitute();
        Collections.sort(scienceExam,institute);
        Log.d(TAG,"after Sorting :");
        Log.d(TAG,scienceExam.toString());

        ArrayAdapter<ScienceExamInstitute> scienceAdapter =
                new ArrayAdapter<>(this,R.layout.user_info_layout,
                                R.id.tv,scienceExam);
        listViewScience.setAdapter(scienceAdapter);
        listViewScience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScienceExamInstitute inst =(ScienceExamInstitute) listViewScience.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(ScienceExamInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });

    }
}
