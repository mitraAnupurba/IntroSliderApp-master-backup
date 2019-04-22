package com.example.introsliderapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.introsliderapp.model.Admin;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
//class for the main app dashboard display:


//error: - the app was crashing on clicking the back button
//how to fix : after starting the actiivity, the app should not finish. that is, we cant call this.finish()


//in order to add action bar only to specific items, apply theme to indivisual activities in the manifests.xml file

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="my tag" ;
    String adminEmail, adminPassword;


    //arraylist variables for different institutes:
    public static ArrayList<IITJEEInstitute> iitjee = new ArrayList<>();
    public static String[] iitjeeNames = new String[256];
    public static ArrayList<MedicalEntranceInstitute> medicalEntrance = new ArrayList<>();
    public static String[] medicalNames = new String[256];
    public static ArrayList<EngineeringExamInstitute> engineeringExam = new ArrayList<>();
    public static String[] engineeringNames = new String[256];
    public static ArrayList<NeetPGInstitute> neetPG = new ArrayList<>();
    public static String[] neetPGNames = new String[256];
    public static ArrayList<ComerceExamInstitute> comerceExam = new ArrayList<>();
    public static String[] comerceNames = new String[256];
    public static ArrayList<ScienceExamInstitute> scienceExam = new ArrayList<>();
    public static String[] scienceNames = new String[256];
    public static ArrayList<UpscInstitute> upsc = new ArrayList<>();
    public static String[] upscNames = new String[256];
    public static ArrayList<BankExamInstitute> bankExam = new ArrayList<>();
    public static String[] bankNames = new String[256];
    public static ArrayList<CollegePlacementTraininhInstitute>
            collegePlacementTraininh = new ArrayList<>();
    public static String[] placementNames = new String[256];
    public static ArrayList<GREInstitute> gre = new ArrayList<>();
    public static String[] greNames = new String[256];
    public static ArrayList<ManagementExamInstitute>
            managementExam = new ArrayList<>();
    public static String[] managementNames = new String[256];
    public static List<String> institutes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating object for the singelton class admin:
        adminEmail = getString(R.string.admin_email);
        adminPassword = getString(R.string.admin_password);
        Admin.createObject(adminEmail,adminPassword);
        Log.d(TAG,adminEmail+adminPassword);


        initLists();
        //checkNull();

        //displaying logs for init institute  call:

        Log.d(TAG,"diaplay 1 ");
        Log.d(TAG,iitjee.toString());
        Log.d(TAG,medicalEntrance.toString());
        Log.d(TAG,engineeringExam.toString());
        Log.d(TAG,neetPG.toString());
        Log.d(TAG,scienceExam.toString());
        Log.d(TAG,comerceExam.toString());
        Log.d(TAG,upsc.toString());
        Log.d(TAG,bankExam.toString());
        Log.d(TAG,collegePlacementTraininh.toString());
        Log.d(TAG,managementExam.toString());
        Log.d(TAG,gre.toString());
    }

    private void getInstituteTypeList(){

        DatabaseReference dRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute");
        dRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                institutes.add(dataSnapshot.getKey());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               int index =  institutes.indexOf(dataSnapshot.getKey());
               institutes.set(index,dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                institutes.remove(dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void initLists(){


        //for IITJEE:
        DatabaseReference mRefiitjee = FirebaseDatabase.getInstance().
                getReference("users").child("institute").child("IIT-JEE");
        mRefiitjee.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                iitjee.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    IITJEEInstitute inst = snapshot.getValue(IITJEEInstitute.class);
                    iitjee.add(inst);
                    iitjeeNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //for medical entrance examinations:
        DatabaseReference mRefMedical = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("Medical Entrance Exams");

        mRefMedical.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                medicalEntrance.clear();
                int count = 0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    MedicalEntranceInstitute inst =
                            snapshot.getValue(MedicalEntranceInstitute.class);
                    medicalEntrance.add(inst);
                    medicalNames[count++] = inst.toString().toUpperCase();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for engineering entrance examinations:
        final DatabaseReference mRefEngg = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("GATE-IES-ESE");
        mRefEngg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                engineeringExam.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    EngineeringExamInstitute inst =
                            snapshot.getValue(EngineeringExamInstitute.class);
                    engineeringExam.add(inst);
                    engineeringNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for Neet PG :
        final DatabaseReference mRefNeetPg = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("NEET-PG");
        mRefNeetPg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                neetPG.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    NeetPGInstitute inst =
                            snapshot.getValue(NeetPGInstitute.class);
                    neetPG.add(inst);
                    neetPGNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for comerce exam institutes:
        final DatabaseReference mRefComerce = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("Comerce");
        mRefComerce.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                comerceExam.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ComerceExamInstitute inst =
                            snapshot.getValue(ComerceExamInstitute.class);
                    comerceExam.add(inst);
                    comerceNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for science exam institutes:
        final DatabaseReference mRefScience = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("JRF-NET");
        mRefScience.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                scienceExam.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ScienceExamInstitute inst =
                            snapshot.getValue(ScienceExamInstitute.class);
                    scienceExam.add(inst);
                    scienceNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for upsc/ics:
        final DatabaseReference mRefUpscIcs = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("UPSC-ICS");
        mRefUpscIcs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                upsc.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    UpscInstitute inst =
                            snapshot.getValue(UpscInstitute.class);
                    upsc.add(inst);
                    upscNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for bank exams:
        final DatabaseReference mRefBank = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("BANK-SBI-PO");
        mRefBank.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bankExam.clear();
                int count =0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    BankExamInstitute inst =
                            snapshot.getValue(BankExamInstitute.class);
                    bankExam.add(inst);
                    bankNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for college placements:
        final DatabaseReference mRefCollegePlacement = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("College Placements");
        mRefCollegePlacement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collegePlacementTraininh.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CollegePlacementTraininhInstitute inst =
                            snapshot.getValue(CollegePlacementTraininhInstitute.class);
                    collegePlacementTraininh.add(inst);
                    placementNames[count++] = inst.toString().toUpperCase();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for GRE IELTS:
        final DatabaseReference mRefGre = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("GRE-IELTS");
        mRefGre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                gre.clear();
                int count =0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    GREInstitute inst =
                            snapshot.getValue(GREInstitute.class);
                    gre.add(inst);
                    greNames[count++] = inst.toString().toUpperCase();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //for management institute:
        final DatabaseReference mRefMgmt = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child("CAT-MAT");
        mRefMgmt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                managementExam.clear();
                int count = 0;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ManagementExamInstitute inst =
                            snapshot.getValue(ManagementExamInstitute.class);
                    managementExam.add(inst);
                    managementNames[count++] = inst.toString().toUpperCase();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    /*private void checkNull(){


        //for IITJEE institutes:
        for(int i=0;i<iitjee.size();i++){
            if(iitjee.get(i) == null){
                iitjee.remove(i);
            }
        }
        Log.d(TAG,iitjee.toString());

    }*/

    //method implementation for the load slides button:
    public void loadSlides(View view) {
        new PreferenceManager(this).clearPreference();
        startActivity(new Intent(this,WelcomeActivity.class));

    }
    //method implementation for the load slides button ends here.


    //method for the locateInstitutes button:
    public void loadInstitutes(View view) {

        initLists();
        //checkNull();
        //displaying logs for init institute  call 2:

        Log.d(TAG,"diaplay 2 ");
        Log.d(TAG,iitjee.toString());
        Log.d(TAG,medicalEntrance.toString());
        Log.d(TAG,engineeringExam.toString());
        Log.d(TAG,neetPG.toString());
        Log.d(TAG,scienceExam.toString());
        Log.d(TAG,comerceExam.toString());
        Log.d(TAG,upsc.toString());
        Log.d(TAG,bankExam.toString());
        Log.d(TAG,collegePlacementTraininh.toString());
        Log.d(TAG,managementExam.toString());
        Log.d(TAG,gre.toString());

        startActivity(new Intent(this,FindInstituteActivity.class));

    }
    //method for the locateInstitutes button ends here.


    //method for the institute sign up button:
    public void loadInstituteLogin(View view) {

        initLists();
        //checkNull();
        startActivity(new Intent(this,InstituteLoginActivity.class));

    }
    //method for the institute signup button ends here

    //method for the student sign up button:
    public void loadStudentLogin(View view) {

        initLists();

        //checkNull();
        startActivity(new Intent(this,StudentLoginActivity.class));

    }
    //method for the student signup button ends here

    //method for the parent sign up button:
    public void loadParentLogin(View view) {

        initLists();
        //checkNull();
        startActivity(new Intent(this,ParentLoginActivity.class));

    }
    //method for the parent signup button ends here

    //method for the admin sign up button:
    public void loadAdminLogin(View view) {

        initLists();
        getInstituteTypeList();
        //checkNull();
        startActivity(new Intent(this,AdminLoginActivity.class));

    }
    //method for the admin signup button ends here


    //method for the load app settings button:
    public void loadAppSettings(View view) {

        startActivity(new Intent(this,AppSettings.class));

    }
    //method for the load app settings button ends here.

    //method that displays the alert dialogue before exitting the app:
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //the set Cancellable method doesnt allow us to cancel the dialog box without choosing yes or no
        builder.setMessage("Are you sure you want to exit the app?").setCancelable(false)
        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                MainActivity.super.onBackPressed();
            }
        })
        .setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }) ;
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        //if we use this, then the app crashes on clicking the exit button:
        //super.onBackPressed();
    }
    //method that displays the alert dialogue before exitting the app ends here.
}
