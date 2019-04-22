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
import com.example.introsliderapp.model.UpscInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.upsc;

public class UpscExamInstituteView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewUpsc;
    private final String TAG = "my tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsc_exam_institute_view);

        toolBar = this.findViewById(R.id.upsc_toolbar);
        listViewUpsc = this.findViewById(R.id.upsc_listView);


        toolBar.setTitle("ICS COACHING INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting");
        Log.d(TAG,upsc.toString());
        UpscInstitute institute = new UpscInstitute();
        Collections.sort(upsc,institute);
        Log.d(TAG,"after Sorting");
        Log.d(TAG,upsc.toString());

        ArrayAdapter<UpscInstitute> upscAdapter =
                new ArrayAdapter<>(this, R.layout.user_info_layout,
                                R.id.tv,upsc);
        listViewUpsc.setAdapter(upscAdapter);
        listViewUpsc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UpscInstitute inst =(UpscInstitute) listViewUpsc.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(UpscExamInstituteView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });

    }
}
