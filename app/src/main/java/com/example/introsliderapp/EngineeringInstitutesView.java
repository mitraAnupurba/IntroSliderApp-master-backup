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

import com.example.introsliderapp.model.EngineeringExamInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;

import java.util.Collections;
import java.util.List;

import static com.example.introsliderapp.MainActivity.engineeringExam;

public class EngineeringInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewEngineering;
    private static final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering_institutes_view);

        toolBar = this.findViewById(R.id.engineering_toolbar);
        listViewEngineering = this.findViewById(R.id.engineering_listView);


        toolBar.setTitle("ENGINEERING INSTITUTE'S :  ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting");
        Log.d(TAG,engineeringExam.toString());
        EngineeringExamInstitute institute =
                        new EngineeringExamInstitute();
        Collections.sort(engineeringExam,institute);
        Log.d(TAG,"After Sorting : ");
        Log.d(TAG,engineeringExam.toString());

        ArrayAdapter<EngineeringExamInstitute> engineeringAdapter =
                new ArrayAdapter<>(this,R.layout.user_info_layout,R.id.tv,
                        engineeringExam);
        listViewEngineering.setAdapter(engineeringAdapter);
        listViewEngineering.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EngineeringExamInstitute inst =(EngineeringExamInstitute) listViewEngineering.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(EngineeringInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });
    }
}
