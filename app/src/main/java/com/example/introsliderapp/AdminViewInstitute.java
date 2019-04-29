package com.example.introsliderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

//import static com.example.introsliderapp.ExpandibleListViewAdapter.childNames;
import com.example.introsliderapp.model.AdminAnalysis;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.introsliderapp.ExpandibleListViewAdapter.childNames;
import static com.example.introsliderapp.ExpandibleListViewAdapter.groupNames;
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

    public static int positiveScore;
    public static int negativeScore;
    public static int neutralScore;

    DatabaseReference mRef;

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

        fetchData();

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(AdminViewInstitute.this,institutes.get(groupPosition)+" Expanded"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(AdminViewInstitute.this,institutes.get(groupPosition)+" Collapsed"
                        ,Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                String childClicked = childNames[groupPosition][childPosition];
                String groupClicked = groupNames[groupPosition];

                Toast.makeText(AdminViewInstitute.this,
                        childNames[groupPosition][childPosition]
                        ,Toast.LENGTH_SHORT).show();

                Intent intent =
                        new Intent(AdminViewInstitute.this,ViewAnalysisGraph.class);
                intent.putExtra("childClicked",childClicked);
                intent.putExtra("groupClicked",groupClicked);
                startActivity(intent);



                return false;
            }
        });

    }

    private void fetchData() {

        mRef = FirebaseDatabase.getInstance().getReference("users")
                .child("admin analysis").child("institute1");
        mRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                AdminAnalysis admin = dataSnapshot.getValue(AdminAnalysis.class);
                positiveScore = admin.getPositiveScore();
                negativeScore = admin.getNegativeScore();
                neutralScore = admin.getNeutralScore();

                Log.d(TAG,Integer.toString(positiveScore));
                Log.d(TAG,Integer.toString(negativeScore));
                Log.d(TAG,Integer.toString(neutralScore));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
