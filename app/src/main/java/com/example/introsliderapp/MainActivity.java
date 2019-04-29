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
import com.google.firebase.analytics.FirebaseAnalytics;
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
        //IITJEE list:
            public static ArrayList<IITJEEInstitute> iitjee = new ArrayList<>();
        //IITJEE NAMES LIST
            public static String[] iitjeeNames = new String[256];
        //medical list
            public static ArrayList<MedicalEntranceInstitute> medicalEntrance = new ArrayList<>();
        //medical names list
            public static String[] medicalNames = new String[256];
        //engineering list
            public static ArrayList<EngineeringExamInstitute> engineeringExam = new ArrayList<>();
        //engineering names list
            public static String[] engineeringNames = new String[256];
        //neet pg list
            public static ArrayList<NeetPGInstitute> neetPG = new ArrayList<>();
        //neet pg names list
            public static String[] neetPGNames = new String[256];
        //comerce list
            public static ArrayList<ComerceExamInstitute> comerceExam = new ArrayList<>();
        //comerce names list
            public static String[] comerceNames = new String[256];
       //science list
            public static ArrayList<ScienceExamInstitute> scienceExam = new ArrayList<>();
       //science names list
            public static String[] scienceNames = new String[256];
        //upsc list
            public static ArrayList<UpscInstitute> upsc = new ArrayList<>();
        //upsc names list:
            public static String[] upscNames = new String[256];
        //bank list
            public static ArrayList<BankExamInstitute> bankExam = new ArrayList<>();
        //bank names list:
            public static String[] bankNames = new String[256];
        //college placements list
            public static ArrayList<CollegePlacementTraininhInstitute>
                    collegePlacementTraininh = new ArrayList<>();
        //college placement names list
            public static String[] placementNames = new String[256];
        //gre list:
            public static ArrayList<GREInstitute> gre = new ArrayList<>();
        //gre names list:
            public static String[] greNames = new String[256];
        //management list:
            public static ArrayList<ManagementExamInstitute>
                    managementExam = new ArrayList<>();
        //management names list:
            public static String[] managementNames = new String[256];
        //list of institutes:
            public static List<String> institutes = new ArrayList<>();

    //array list for storing the average of institutes under a specific type:
        public static ArrayList<Float> iitjeeAvg = new ArrayList<>();
        public static ArrayList<Float> medicalAvg = new ArrayList<>();
        public static ArrayList<Float> engineeringAvg = new ArrayList<>();
        public static ArrayList<Float> neetPGAvg = new ArrayList<>();
        public static ArrayList<Float> comerceAvg = new ArrayList<>();
        public static ArrayList<Float> bankAvg = new ArrayList<>();
        public static ArrayList<Float> scienceAvg = new ArrayList<>();
        public static ArrayList<Float> upscAvg = new ArrayList<>();
        public static ArrayList<Float> greAvg = new ArrayList<>();
        public static ArrayList<Float> placementAvg = new ArrayList<>();
        public static ArrayList<Float> managementAvg = new ArrayList<>();


    //Array list for storing the percentage information:

        public static ArrayList<Float> iitjeePercent = new ArrayList<>();
        public static ArrayList<Float> medicalPercent = new ArrayList<>();
        public static ArrayList<Float> engineeringPercent = new ArrayList<>();
        public static ArrayList<Float> neetPGPercent = new ArrayList<>();
        public static ArrayList<Float> comercePercent = new ArrayList<>();
        public static ArrayList<Float> bankPercent = new ArrayList<>();
        public static ArrayList<Float> sciencePercent = new ArrayList<>();
        public static ArrayList<Float> upscPercent = new ArrayList<>();
        public static ArrayList<Float> grePercent = new ArrayList<>();
        public static ArrayList<Float> placementPercent = new ArrayList<>();
        public static ArrayList<Float> managementPercent = new ArrayList<>();

    FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating object for the singelton class admin:
        adminEmail = getString(R.string.admin_email);
        adminPassword = getString(R.string.admin_password);
        Admin.createObject(adminEmail,adminPassword);
        Log.d(TAG,adminEmail+adminPassword);

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ID");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Anupurba");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Main Activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

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

    //method to get the list of all the different types of institutes:
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
    //method to get the list of all the different types of institutes ends here:


    //method to get the list of all the institutes under a specific type:
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
                    float percentage ;
                    IITJEEInstitute inst = snapshot.getValue(IITJEEInstitute.class);
                    iitjee.add(inst);
                    iitjeeNames[count++] = inst.toString().toUpperCase();
                    iitjeeAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = (float)((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        iitjeePercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    MedicalEntranceInstitute inst =
                            snapshot.getValue(MedicalEntranceInstitute.class);
                    medicalEntrance.add(inst);
                    medicalNames[count++] = inst.toString().toUpperCase();
                    medicalAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        medicalPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }


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
                    float percentage;
                    EngineeringExamInstitute inst =
                            snapshot.getValue(EngineeringExamInstitute.class);
                    engineeringExam.add(inst);
                    engineeringNames[count++] = inst.toString().toUpperCase();
                    engineeringAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        engineeringPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    NeetPGInstitute inst =
                            snapshot.getValue(NeetPGInstitute.class);
                    neetPG.add(inst);
                    neetPGNames[count++] = inst.toString().toUpperCase();
                    neetPGAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        neetPGPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);

                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    ComerceExamInstitute inst =
                            snapshot.getValue(ComerceExamInstitute.class);
                    comerceExam.add(inst);
                    comerceNames[count++] = inst.toString().toUpperCase();
                    comerceAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        comercePercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    ScienceExamInstitute inst =
                            snapshot.getValue(ScienceExamInstitute.class);
                    scienceExam.add(inst);
                    scienceNames[count++] = inst.toString().toUpperCase();
                    scienceAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        sciencePercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    UpscInstitute inst =
                            snapshot.getValue(UpscInstitute.class);
                    upsc.add(inst);
                    upscNames[count++] = inst.toString().toUpperCase();
                    upscAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        upscPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    BankExamInstitute inst =
                            snapshot.getValue(BankExamInstitute.class);
                    bankExam.add(inst);
                    bankNames[count++] = inst.toString().toUpperCase();
                    bankAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        bankPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    CollegePlacementTraininhInstitute inst =
                            snapshot.getValue(CollegePlacementTraininhInstitute.class);
                    collegePlacementTraininh.add(inst);
                    placementNames[count++] = inst.toString().toUpperCase();
                    placementAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        placementPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
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
                    float percentage;
                    GREInstitute inst =
                            snapshot.getValue(GREInstitute.class);
                    gre.add(inst);
                    greNames[count++] = inst.toString().toUpperCase();
                    greAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        grePercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }

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
                    float percentage;
                    ManagementExamInstitute inst =
                            snapshot.getValue(ManagementExamInstitute.class);
                    managementExam.add(inst);
                    managementNames[count++] = inst.toString().toUpperCase();
                    managementAvg.add(inst.getAvg());
                    try{
                        Log.d(TAG,"student in = "+Integer.toString(inst.getStudentIn()));
                        Log.d(TAG,"Student out = "+inst.getStudentOut());
                        percentage = ((float)inst.getStudentOut()/(float)inst.getStudentIn())*100;
                        managementPercent.add(percentage);
                        Log.d(TAG,"Percentage = "+percentage);
                    }
                    catch(ArithmeticException e){
                        Log.d(TAG,"Division by 0");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    //method to get the list of all the institutes under a specific type ends here:


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


        Log.d(TAG,"diaplay average: ");
        Log.d(TAG,iitjeeAvg.toString());
        Log.d(TAG,medicalAvg.toString());
        Log.d(TAG,engineeringAvg.toString());
        Log.d(TAG,neetPGAvg.toString());
        Log.d(TAG,scienceAvg.toString());
        Log.d(TAG,comerceAvg.toString());
        Log.d(TAG,upscAvg.toString());
        Log.d(TAG,bankAvg.toString());
        Log.d(TAG,placementAvg.toString());
        Log.d(TAG,greAvg.toString());
        Log.d(TAG,managementAvg.toString());

        Log.d(TAG,"diaplay percentage: ");
        Log.d(TAG,iitjeePercent.toString());
        Log.d(TAG,medicalPercent.toString());
        Log.d(TAG,engineeringPercent.toString());
        Log.d(TAG,neetPGPercent.toString());
        Log.d(TAG,sciencePercent.toString());
        Log.d(TAG,comercePercent.toString());
        Log.d(TAG,upscPercent.toString());
        Log.d(TAG,bankPercent.toString());
        Log.d(TAG,placementPercent.toString());
        Log.d(TAG,grePercent.toString());
        Log.d(TAG,managementPercent.toString());

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

        Bundle params = new Bundle();
        firebaseAnalytics.logEvent("Admin_Clicked",params);


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
