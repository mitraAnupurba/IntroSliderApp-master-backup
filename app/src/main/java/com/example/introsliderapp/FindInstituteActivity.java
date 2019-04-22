package com.example.introsliderapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import com.example.introsliderapp.model.Student;
import com.example.introsliderapp.model.UpscInstitute;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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

public class FindInstituteActivity extends AppCompatActivity implements View.OnClickListener{

    private Button iitjeeButton,medicalButton,engineeringButton,neetButton,
                comerceButton,jrfnetButton,upscIcsButton,bankExamButton,
                placementTrainingButton,greieltsButton,managementButton;
    private static final String TAG = "my tag";
    //private ArrayList<Float> avgArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_institute);

        initViews();

        iitjeeButton.setOnClickListener(this);
        medicalButton.setOnClickListener(this);
        engineeringButton.setOnClickListener(this);
        neetButton.setOnClickListener(this);
        comerceButton.setOnClickListener(this);
        jrfnetButton.setOnClickListener(this);
        upscIcsButton.setOnClickListener(this);
        bankExamButton.setOnClickListener(this);
        placementTrainingButton.setOnClickListener(this);
        greieltsButton.setOnClickListener(this);
        managementButton.setOnClickListener(this);
    }

    private void initViews(){

        iitjeeButton = this.findViewById(R.id.button_iitjee);
        medicalButton = this.findViewById(R.id.button_medical_entrance);
        engineeringButton = this.findViewById(R.id.button_engineering_entrance);
        neetButton = this.findViewById(R.id.button_medical_pg_entrance);
        comerceButton = this.findViewById(R.id.button_comerce);
        jrfnetButton = this.findViewById(R.id.button_research_fellowship);
        upscIcsButton = this.findViewById(R.id.button_upsc);
        bankExamButton = this.findViewById(R.id.button_bank_exam);
        placementTrainingButton = this.findViewById(R.id.button_college_placement);
        greieltsButton = this.findViewById(R.id.button_foreign_study);
        managementButton = this.findViewById(R.id.button_management);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_iitjee:
                updateIITJEEaverage();
                break;
            case R.id.button_medical_entrance:
                updateMedicalEntravceAverage();
                break;
            case R.id.button_engineering_entrance:
                updateEngineeringEntranceAverage();
                break;
            case R.id.button_medical_pg_entrance:
                updateMedicalPGEntranceAverage();
                break;
            case R.id.button_comerce:
                updateComerceAverage();
                break;
            case R.id.button_research_fellowship:
                updateScienceExamAverage();
                break;
            case R.id.button_upsc:
                updateUpscExamAverage();
                break;
            case R.id.button_bank_exam:
                updateBankExamAverage();
                break;
            case R.id.button_college_placement:
                updateCollegePlacementTrainingAverage();
                break;
            case R.id.button_foreign_study:
                updateGreIeltsAverage();
                break;
            case R.id.button_management:
                updateManagementInstituteAverage();
                break;

        }
    }




    //method for updating the average of iitjee institutes :
    private void updateIITJEEaverage() {

        //for updating average :
        for(int i=0;i<iitjee.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("IIT-JEE").child(iitjee.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    IITJEEInstitute institute = new IITJEEInstitute();
                    Log.d(TAG,iitjee.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        //IITJEEInstitute institute = new IITJEEInstitute();
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        Intent intent = new Intent(FindInstituteActivity.this,IITJEEInstitutesView.class);
        startActivity(intent);

    }
    //method for updating the average of iitjee institutes :

    //medhod for updating medical entrance exams average:
    private void updateMedicalEntravceAverage() {

        for(int i=0;i<medicalEntrance.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("Medical Entrance Exams").child(medicalEntrance.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    MedicalEntranceInstitute institute = new MedicalEntranceInstitute();
                    Log.d(TAG,medicalEntrance.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),MedicalInstitutesView.class));

    }
    //medhod for updating medical entrance exams average ends here:

    //method for updating average of engineering exam institutes:
    private void updateEngineeringEntranceAverage() {

        for(int i=0;i<engineeringExam.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("GATE-IES-ESE").child(engineeringExam.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    EngineeringExamInstitute institute = new EngineeringExamInstitute();
                    Log.d(TAG,engineeringExam.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),EngineeringInstitutesView.class));


    }
    //method for updating average of engineering exam institutes ends here:

    //method for updating average of medical PG institutes:
    private void updateMedicalPGEntranceAverage() {

        for(int i=0;i<neetPG.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("NEET-PG").child(neetPG.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    NeetPGInstitute institute = new NeetPGInstitute();
                    Log.d(TAG,neetPG.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),MedicalPGInstituteView.class));

    }
    //method for updating average of medical PG institutes ends here.

    //method for updating the average of comerce institutes:
    private void updateComerceAverage() {

        for(int i=0;i<comerceExam.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("Comerce").child(comerceExam.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    ComerceExamInstitute institute = new ComerceExamInstitute();
                    Log.d(TAG,comerceExam.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),ComerceInstitutesView.class));


    }
    //method for updating the average of comerce institutes ends here.

    //method for updating the average of science exam JRF/NEt institutes:
    private void updateScienceExamAverage() {

        for(int i=0;i<scienceExam.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("JRF-NET").child(scienceExam.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    ScienceExamInstitute institute = new ScienceExamInstitute();
                    Log.d(TAG,scienceExam.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),ScienceExamInstitutesView.class));


    }
    //method for updating the average of science exam JRF/NEt institutes ends here:

    //method for updating upsc exam average:
    private void updateUpscExamAverage() {

        for(int i=0;i<upsc.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("UPSC-ICS").child(upsc.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    UpscInstitute institute = new UpscInstitute();
                    Log.d(TAG,upsc.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),UpscExamInstituteView.class));


    }
    //method for updating upsc exam average ends here:

    //method for updating gre ielts institute average:
    private void updateGreIeltsAverage() {

        for(int i=0;i<gre.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("GRE-IELTS").child(gre.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    GREInstitute institute = new GREInstitute();
                    Log.d(TAG,gre.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),GreIeltaInstitutesView.class));


    }
    //method for updating gre ielts institute average:


    //method for updating bank exam average:
    private void updateBankExamAverage() {

        for(int i=0;i<bankExam.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("BANK-SBI-PO").child(bankExam.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    BankExamInstitute institute = new BankExamInstitute();
                    Log.d(TAG,bankExam.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),BankExamInstituteView.class));



    }
    //method for updating bank exam average ends here:

    //method for updating college placement trtaining average here:
    private void updateCollegePlacementTrainingAverage() {

        for(int i=0;i<collegePlacementTraininh.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("College Placements").child(collegePlacementTraininh.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    CollegePlacementTraininhInstitute institute =
                            new CollegePlacementTraininhInstitute();
                    Log.d(TAG,collegePlacementTraininh.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),PlacementTrainingInstituteView.class));


    }
    ////method for updating college placement trtaining average ends here:

    //method for updating management institutes average:
    private void updateManagementInstituteAverage() {

        for(int i=0;i<managementExam.size();i++){

            final int count = i;

            final DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("users").child("institute")
                    .child("CAT-MAT").child(managementExam.get(i).toString())
                    .child("students of this institute");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    float average = 0.0f;
                    HashMap<String,Object> studentMap = null;
                    ManagementExamInstitute institute =
                            new ManagementExamInstitute();
                    Log.d(TAG,managementExam.get(count).toString().toUpperCase());
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        studentMap = (HashMap<String,Object>)snapshot.getValue();
                        Log.d(TAG,studentMap.entrySet().toString());
                        float rating =Float.parseFloat(studentMap.get("rating submitted").toString()) ;
                        Log.d(TAG,studentMap.get("rating submitted").toString());
                        institute.setRatingArray(rating);
                        average = institute.getAvg();
                        Log.d(TAG,"current average : " + Float.toString(average));
                    }
                    institute.setAvg(average);
                    Log.d(TAG,Float.toString(institute.getAvg()));
                    DatabaseReference parent = mRef.getParent();
                    parent.child("avg").setValue(average);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        startActivity(new Intent(getApplicationContext(),ManagementInstitutesView.class));


    }
    //method for updating management institute average ends here

}
