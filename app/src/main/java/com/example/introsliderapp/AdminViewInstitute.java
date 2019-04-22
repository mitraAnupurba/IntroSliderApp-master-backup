package com.example.introsliderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import static com.example.introsliderapp.MainActivity.institutes;
import static com.example.introsliderapp.MainActivity.upscNames;
import static com.example.introsliderapp.MainActivity.iitjeeNames;
import static com.example.introsliderapp.MainActivity.medicalNames;
import static com.example.introsliderapp.MainActivity.engineeringNames;
import static com.example.introsliderapp.MainActivity.neetPGNames;
import static com.example.introsliderapp.MainActivity.bankNames;
import static com.example.introsliderapp.MainActivity.comerceNames;
import static com.example.introsliderapp.MainActivity.greNames;
import static com.example.introsliderapp.MainActivity.scienceNames;
import static com.example.introsliderapp.MainActivity.placementNames;
import static com.example.introsliderapp.MainActivity.managementNames;




public class AdminViewInstitute extends AppCompatActivity {

    private static final String TAG = "My Tag";
    ExpandableListView expandableListView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_institute);

        Log.d(TAG,"List of institute types:");
        Log.d(TAG,institutes.toString());
        Log.d(TAG,"IITJEE :");

        expandableListView = this.findViewById(R.id.expandible_listView);
        ExpandibleListViewAdapter adapter =
                            new ExpandibleListViewAdapter(AdminViewInstitute.this);
        expandableListView.setAdapter(adapter);
    }
}
