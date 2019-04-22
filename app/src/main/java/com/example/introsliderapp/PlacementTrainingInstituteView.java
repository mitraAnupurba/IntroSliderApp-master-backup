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

import com.example.introsliderapp.model.CollegePlacementTraininhInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.collegePlacementTraininh;

public class PlacementTrainingInstituteView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewPlacement;
    private final String TAG = "My Tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_training_institute_view);

        toolBar = this.findViewById(R.id.placement_toolbar);
        listViewPlacement = this.findViewById(R.id.placement_listView);

        toolBar.setTitle("PLACEMENT TRAINING  : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting : ");
        Log.d(TAG,collegePlacementTraininh.toString());

        CollegePlacementTraininhInstitute institute =
                new CollegePlacementTraininhInstitute();
        Collections.sort(collegePlacementTraininh,institute);

        Log.d(TAG,"after Sorting : ");
        Log.d(TAG,collegePlacementTraininh.toString());

        ArrayAdapter<CollegePlacementTraininhInstitute> placementAdapter =
                new ArrayAdapter<>(this,R.layout.user_info_layout,R.id.tv,
                        collegePlacementTraininh);
        listViewPlacement.setAdapter(placementAdapter);
        listViewPlacement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CollegePlacementTraininhInstitute inst
                        =(CollegePlacementTraininhInstitute) listViewPlacement.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(PlacementTrainingInstituteView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });
    }
}
