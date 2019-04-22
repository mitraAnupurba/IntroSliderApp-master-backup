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

import com.example.introsliderapp.model.ComerceExamInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.comerceExam;

public class ComerceInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewComerce;
    private static final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comerce_institutes_view);

        toolBar = this.findViewById(R.id.comerce_toolbar);
        listViewComerce = this.findViewById(R.id.comerce_listView);


        toolBar.setTitle("IIT JEE INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before sorting : ");
        Log.d(TAG,comerceExam.toString());

        ComerceExamInstitute institute =
                new ComerceExamInstitute();
        Collections.sort(comerceExam,institute);

        Log.d(TAG,"After Sorting");
        Log.d(TAG,comerceExam.toString());

        ArrayAdapter<ComerceExamInstitute> comerceAdapter = new ArrayAdapter<>(this,
                            R.layout.user_info_layout,R.id.tv,comerceExam);
        listViewComerce.setAdapter(comerceAdapter);
        listViewComerce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ComerceExamInstitute inst =(ComerceExamInstitute) listViewComerce.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(ComerceInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });

    }
}
