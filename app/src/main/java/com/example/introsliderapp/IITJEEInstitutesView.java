package com.example.introsliderapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.introsliderapp.model.BankExamInstitute;
import com.example.introsliderapp.model.CollegePlacementTraininhInstitute;
import com.example.introsliderapp.model.ComerceExamInstitute;
import com.example.introsliderapp.model.EngineeringExamInstitute;
import com.example.introsliderapp.model.GREInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;
import com.example.introsliderapp.model.Institute;
import com.example.introsliderapp.model.ManagementExamInstitute;
import com.example.introsliderapp.model.MedicalEntranceInstitute;
import com.example.introsliderapp.model.NeetPGInstitute;
import com.example.introsliderapp.model.ScienceExamInstitute;
import com.example.introsliderapp.model.UpscInstitute;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.introsliderapp.MainActivity.bankExam;
import static com.example.introsliderapp.MainActivity.collegePlacementTraininh;
import static com.example.introsliderapp.MainActivity.comerceExam;
import static com.example.introsliderapp.MainActivity.engineeringExam;
import static com.example.introsliderapp.MainActivity.gre;
import static com.example.introsliderapp.MainActivity.iitjee;
import static com.example.introsliderapp.MainActivity.managementExam;
import static com.example.introsliderapp.MainActivity.medicalEntrance;
import static com.example.introsliderapp.MainActivity.neetPG;
import static com.example.introsliderapp.MainActivity.scienceExam;
import static com.example.introsliderapp.MainActivity.upsc;

public class IITJEEInstitutesView extends AppCompatActivity {

    private static final String TAG = "My tag";
    ListView listView ;
    private Toolbar toolBar;
    ArrayList<String> displayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iitjeeinstitutes_view);

        toolBar = this.findViewById(R.id.iitjee_toolbar);

        toolBar.setTitle("IIT JEE INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);


        IITJEEInstitute institute = new IITJEEInstitute();
        Collections.sort(iitjee,institute);
        Log.d(TAG,iitjee.toString());


        listView = this.findViewById(R.id.iitjee_listView);
        ArrayAdapter<IITJEEInstitute> iitjeeAdapter =
                new ArrayAdapter<>(this,R.layout.user_info_layout,R.id.tv,iitjee);
                listView.setAdapter(iitjeeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IITJEEInstitute inst =(IITJEEInstitute) listView.getItemAtPosition(position);
                String name = inst.toString();
                String type = inst.getInstituteType();

                Log.d(TAG, "Name: "+name+"\nType: "+type);
                Intent intent = new Intent(IITJEEInstitutesView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);
                startActivity(intent);
            }
        });


    }

}
