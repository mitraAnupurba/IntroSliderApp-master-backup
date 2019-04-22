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
import com.example.introsliderapp.model.ManagementExamInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.managementExam;

public class ManagementInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewManagement;
    private final String TAG = "My Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_institutes_view);

        toolBar = this.findViewById(R.id.management_toolbar);
        listViewManagement = this.findViewById(R.id.management_listView);

        toolBar.setTitle("MBA COACHING INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting");
        Log.d(TAG,managementExam.toString());

        ManagementExamInstitute institute = new ManagementExamInstitute();
        Collections.sort(managementExam,institute);

        Log.d(TAG,"after Sorting");
        Log.d(TAG,managementExam.toString());

        ArrayAdapter<ManagementExamInstitute> managementAdapter =
                    new ArrayAdapter<>(this,R.layout.user_info_layout,R.id.tv,
                            managementExam);
        listViewManagement.setAdapter(managementAdapter);

        listViewManagement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ManagementExamInstitute inst =(ManagementExamInstitute) listViewManagement.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(ManagementInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });


    }
}
