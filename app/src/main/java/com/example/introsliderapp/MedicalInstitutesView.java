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
import com.example.introsliderapp.model.MedicalEntranceInstitute;
import static com.example.introsliderapp.MainActivity.medicalEntrance;

import java.util.Collections;
import java.util.List;

public class MedicalInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewMedical;
    private static final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_institutes_view);

        listViewMedical = this.findViewById(R.id.medical_listView);
        toolBar = this.findViewById(R.id.medical_toolbar);


        toolBar.setTitle("MEDICAL ENTRANCE INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"Before Sorting");
        Log.d(TAG,medicalEntrance.toString());
        MedicalEntranceInstitute institute = new MedicalEntranceInstitute();
        Collections.sort(medicalEntrance,institute);
        Log.d(TAG,"after sorting");
        Log.d(TAG,medicalEntrance.toString());


        ArrayAdapter<MedicalEntranceInstitute> medicalAdapter
                = new ArrayAdapter<>(this,R.layout.user_info_layout,
                                R.id.tv,medicalEntrance);
        listViewMedical.setAdapter(medicalAdapter);
        listViewMedical.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedicalEntranceInstitute inst =(MedicalEntranceInstitute) listViewMedical.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(MedicalInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });

    }
}
