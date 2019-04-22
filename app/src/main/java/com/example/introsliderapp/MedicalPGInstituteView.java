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
import com.example.introsliderapp.model.NeetPGInstitute;

import java.util.Collections;
import java.util.List;
import static com.example.introsliderapp.MainActivity.neetPG;

public class MedicalPGInstituteView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewMedicalPG;
    private static final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_pginstitute_view);

        toolBar = this.findViewById(R.id.medical_pg_toolbar);
        listViewMedicalPG =  this.findViewById(R.id.medical_pg_listView);


        toolBar.setTitle("NEET PG INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before sorting");
        Log.d(TAG,neetPG.toString());
        NeetPGInstitute institute = new NeetPGInstitute();
        Collections.sort(neetPG,institute);
        Log.d(TAG,"after sorting");
        Log.d(TAG,neetPG.toString());

        ArrayAdapter<NeetPGInstitute> medicalPGAdapter
                        = new ArrayAdapter<>(this,R.layout.user_info_layout
                                ,R.id.tv,neetPG);
        listViewMedicalPG.setAdapter(medicalPGAdapter);
        listViewMedicalPG.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NeetPGInstitute inst =(NeetPGInstitute) listViewMedicalPG.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(MedicalPGInstituteView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });


    }
}
