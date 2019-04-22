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

import com.example.introsliderapp.model.GREInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.gre;

public class GreIeltaInstitutesView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewGRE;
    private final String TAG = "My Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gre_ielta_institutes_view);

        toolBar = this.findViewById(R.id.gre_toolbar);
        listViewGRE = this.findViewById(R.id.gre_listView);

        toolBar.setTitle("FOREIGN STUDY : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting");
        Log.d(TAG,gre.toString());

        GREInstitute institute = new GREInstitute();
        Collections.sort(gre,institute);

        Log.d(TAG,"After Sorting");
        Log.d(TAG,gre.toString());

        ArrayAdapter<GREInstitute> greAdapter = new ArrayAdapter<>(this,
                R.layout.user_info_layout,R.id.tv,gre);
        listViewGRE.setAdapter(greAdapter);
        listViewGRE.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GREInstitute inst =(GREInstitute) listViewGRE.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(GreIeltaInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });

    }
}
