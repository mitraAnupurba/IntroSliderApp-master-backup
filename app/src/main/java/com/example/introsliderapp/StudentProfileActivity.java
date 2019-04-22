package com.example.introsliderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

//Class for the opearation's in students profile activity, like view data, update data,
//rate institute, delete data, etc.
public class StudentProfileActivity extends AppCompatActivity {

    //variable to display Logs for testing purpose:
        private static final String TAG ="my tag" ;

    //Firebase Auth variable.
        private FirebaseAuth mAuth;

    //View's variables:
        private Toolbar toolbar;
        private String uid;
        private RatingBar ratingBar;
    //edittext's for update dialog:
        private EditText phoneNumberStudentUpdate
                ,userNameStudentUpdate,
                instNameStudentUpdate,
            reviewStudentEdittext;
    //textViews for the update student dialog:
        private TextView choseExamNameUpdateDialog,choseCoachingNameUpdateDialog;
    //Spinner for update dialog
        private Spinner spinnerStudentUpdateDialog,spinnerStudentCoachingDialog;
    //test view for user data display
        private TextView emailAddressStudent,phoneNumberStudent,userNameStudent,
                            dobStudent,instNameStudent
                        ,examNameStudent,coachingNameStudent;

    //strings for initial data store and display:
        private String email,phNumber,userName,dob,instName,examName,coachingName;

    //strings for updated value:
        private String phNumberUpdatedValue,userNameUpdatedValue
                ,instNameUpdatedValue,examNameUpdatedValue
            ,coachingNameUpdatedValue;

    //variable to get the rating and review:
        private String studentReview;
        private float studentRating;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        initViews();
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();


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

        //fetch data:
        fetchData();

        Log.d(TAG,"mytag");

        //initLists(examName);



    }

    //Function to initialise the view components:
    private void initViews(){

        emailAddressStudent = this.findViewById(R.id.email_address_textView);
        phoneNumberStudent = this.findViewById(R.id.phone_number_textView);
        userNameStudent = this.findViewById(R.id.user_name_textView);
        dobStudent = this.findViewById(R.id.dob_student_signup_textView);
        instNameStudent = this.findViewById(R.id.institute_name_textView);
        examNameStudent = this.findViewById(R.id.exam_name_textView);
        toolbar = this.findViewById(R.id.toolbar_student_profile);
        coachingNameStudent = this.findViewById(R.id.coaching_name_textView);
    }
    //Function to initialise the view components ends here.


    //Function to set the view components, called from the fetchData() function:
    private void setViews(){
        emailAddressStudent.setText("Email Address : "+email);
        phoneNumberStudent.setText("Phone NUmber : "+phNumber);
        userNameStudent.setText("User Name : "+userName);
        dobStudent.setText("Date Of Birth"+dob);
        instNameStudent.setText("Institute Name : "+instName);
        examNameStudent.setText("Exam Preparing For : "+examName);
        coachingNameStudent.setText("Coaching Institute Studying In:"+coachingName);
        toolbar.setTitle(userName);
        toolbar.setSubtitle("welcome");
    }
    //Function to set the view components ends here.


    //function to map the menu_main layout to out layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }
    //function to map the menu_main layout to out layout ends here.


    //function to make a functional toolbar, adding functionality to each of the menu items:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.update:
                showUpdateDialog();
                break;
            case R.id.delete:
                showDeleteDialog();
                break;
            case R.id.logout:
                showLogout();
                break;
            case R.id.rate:
                showRatingBar();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    //Function to make a functional toolbar ends here.


    //function to show the update dialog and update data:
    private void showUpdateDialog() {

        //variable to get Alert dialog:
        AlertDialog.Builder updateDialog = new AlertDialog.Builder(StudentProfileActivity.this);
        View v = getLayoutInflater().inflate(R.layout.update_dialog,null);

        //getting the views:
        phoneNumberStudentUpdate = v.findViewById(R.id.phone_number_edittext_update);
        userNameStudentUpdate = v.findViewById(R.id.user_name_edittext_update);
        instNameStudentUpdate = v.findViewById(R.id.institute_name_edittext_update);
        choseExamNameUpdateDialog = v.findViewById(R.id.exam_name_textView_updateDialog);
        //choseCoachingNameUpdateDialog = v.findViewById(R.id.coaching_name_textView_update_dialog);
        spinnerStudentUpdateDialog = v.findViewById(R.id.select_exam_spinner_update_student);
        spinnerStudentCoachingDialog = v.findViewById(R.id.select_coaching_spinner_update_student);

        //seting default values to the view items:
        phoneNumberStudentUpdate.setText(phNumber);
        userNameStudentUpdate.setText(userName);
        instNameStudentUpdate.setText(instName);

        //setting array adapter for spinnerStudentUpdateDialog:
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(StudentProfileActivity.this, R.array.list_of_exams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStudentUpdateDialog.setAdapter(adapter);
        spinnerStudentUpdateDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                examNameUpdatedValue = parent.getItemAtPosition(position).toString();
                Log.d(TAG,examNameUpdatedValue);
                setSpinnerCoachingContent(examNameUpdatedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //code for creating the alert dialog and adding finctionality to it:
        updateDialog.setView(v);
        updateDialog.setTitle("UPDATE");
        updateDialog.setMessage("UPDATE VALUES: Email and D.O.B are NON Editable");
        updateDialog.create();
        updateDialog.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //getting the updated values.
                phNumberUpdatedValue = phoneNumberStudentUpdate.getText().toString().trim();
                userNameUpdatedValue = userNameStudentUpdate.getText().toString().trim();
                instNameUpdatedValue = instNameStudentUpdate.getText().toString().trim();


                //validation for the updated data entries
                if(phNumberUpdatedValue.isEmpty()){
                    phoneNumberStudentUpdate.setError("field is empty");
                    phoneNumberStudentUpdate.requestFocus();

                }
                else if(userNameUpdatedValue.isEmpty()){
                    userNameStudentUpdate.setError("field is empty");
                    userNameStudentUpdate.requestFocus();

                }
                else if(instNameUpdatedValue.isEmpty()){
                    instNameStudentUpdate.setError("field is empty");
                    instNameStudentUpdate.requestFocus();

                }
                //validation ends here
                else{
                        //function call for the update data functionality:
                        updateData(phNumberUpdatedValue,userNameUpdatedValue
                        ,instNameUpdatedValue,examNameUpdatedValue,coachingNameUpdatedValue);

                        //setting the updated values in the main users profile page:
                        phoneNumberStudent.setText(phNumberUpdatedValue);
                        userNameStudent.setText(userNameUpdatedValue);
                        instNameStudent.setText(instNameUpdatedValue);
                        examNameStudent.setText(examNameUpdatedValue);
                        coachingNameStudent.setText(coachingNameUpdatedValue);
                }


            }
        });
        updateDialog.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        updateDialog.setCancelable(false);
        updateDialog.show();

        //Toast.makeText(this,"update clicked",Toast.LENGTH_SHORT).show();
    }
    //function to create and show the update dialog ends here.



    //function to init lists:



    //function to set content of the spinner dynamically:
    private void setSpinnerCoachingContent(String examNameUpdatedValue){

        Log.d(TAG,"Enters Select Spinner Content");
        switch(examNameUpdatedValue){

            case "IIT-JEE":
                ArrayAdapter<IITJEEInstitute> adapter = new ArrayAdapter<IITJEEInstitute>(this,  android.R.layout.simple_spinner_item,iitjee);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener);
                break;

            case "Medical Entrance Exams":
                Log.d(TAG,"Enters Medical Entrance Exams");
                ArrayAdapter<MedicalEntranceInstitute> adapter1 = new ArrayAdapter<MedicalEntranceInstitute>(this,  android.R.layout.simple_spinner_item,medicalEntrance);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener1);
                break;

            case "GATE-IES-ESE":
                ArrayAdapter<EngineeringExamInstitute> adapter2 = new ArrayAdapter<EngineeringExamInstitute>(this,  android.R.layout.simple_spinner_item,engineeringExam);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener2);
                break;

            case "NEET-PG":
                ArrayAdapter<NeetPGInstitute> adapter3 = new ArrayAdapter<NeetPGInstitute>(this,  android.R.layout.simple_spinner_item,neetPG);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener3 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener3);
                break;

            case "Comerce":
                ArrayAdapter<ComerceExamInstitute> adapter4 = new ArrayAdapter<ComerceExamInstitute>(this,  android.R.layout.simple_spinner_item,comerceExam);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter4);
                adapter4.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener4 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener4);
                break;

            case "JRF-NET":
                ArrayAdapter<ScienceExamInstitute> adapter5 = new ArrayAdapter<ScienceExamInstitute>(this,  android.R.layout.simple_spinner_item,scienceExam);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter5);
                adapter5.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener5 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener5);
                break;

            case "UPSC-ICS":
                ArrayAdapter<UpscInstitute> adapter6 = new ArrayAdapter<UpscInstitute>(this,  android.R.layout.simple_spinner_item,upsc);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter6);
                adapter6.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener6 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener6);
                break;

            case "BANK-SBI-PO":
                ArrayAdapter<BankExamInstitute> adapter7 = new ArrayAdapter<BankExamInstitute>(this,  android.R.layout.simple_spinner_item,bankExam);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter7);
                adapter7.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener7 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener7);
                break;

            case "College Placements":
                ArrayAdapter<CollegePlacementTraininhInstitute> adapter8 = new ArrayAdapter<CollegePlacementTraininhInstitute>(this,  android.R.layout.simple_spinner_item,collegePlacementTraininh);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter8);
                adapter8.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener8 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener8);
                break;

            case "GRE-IELTS":
                ArrayAdapter<GREInstitute> adapter9 = new ArrayAdapter<GREInstitute>(this,  android.R.layout.simple_spinner_item,gre);
                adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter9);
                adapter9.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener9 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener9);
                break;

            case "CAT-MAT":
                ArrayAdapter<ManagementExamInstitute> adapter10 = new ArrayAdapter<ManagementExamInstitute>(this,  android.R.layout.simple_spinner_item,managementExam);
                adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerStudentCoachingDialog.setAdapter(adapter10);
                adapter10.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener10 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        coachingNameUpdatedValue = parent.getItemAtPosition(position).toString();
                        Log.d(TAG, coachingNameUpdatedValue);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerStudentCoachingDialog.setOnItemSelectedListener(listener10);
                break;
        }


    }
    //function to set the content of dynamic spinner named : spinner coaching ends here


    //function to update the data of users and storing it in firebase database:
    private void updateData(String phNumberUpdatedValue, String userNameUpdatedValue, String instNameUpdatedValue, String examNameUpdatedValue,String coachingNameUpdatedValue) {

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("student").child(uid);
        Student updatedStudent = new Student(userNameUpdatedValue,email,phNumberUpdatedValue
                            ,instNameUpdatedValue,examNameUpdatedValue,dob,coachingNameUpdatedValue);
        mRef.setValue(updatedStudent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(StudentProfileActivity.this,"data updated successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //function to update data and store it in firebse database ends here.


    //function to show the delete dialog and delete data:
    private void showDeleteDialog() {

        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(StudentProfileActivity.this);
        deleteDialog.setMessage("Are you sure you want to delete this account?");
        deleteDialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DatabaseReference mRef = FirebaseDatabase.getInstance()
                        .getReference("users").child("student").child(uid);

                DatabaseReference mRefDeleteFromInst = FirebaseDatabase.getInstance()
                        .getReference("users").child("institute")
                        .child(examName).child(coachingName)
                        .child("students of this institute").child(uid);

                //firebase current user to be deleted:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                mRefDeleteFromInst.removeValue();
                mRef.removeValue();
                mAuth.signOut();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(StudentProfileActivity.this,"User Deleted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),StudentLoginActivity.class));
                            StudentProfileActivity.this.finish();
                        }
                    }
                });
                Toast.makeText(StudentProfileActivity.this,"Account Deleted",Toast.LENGTH_SHORT).show();

            }
        });
        deleteDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        deleteDialog.setCancelable(false);
        deleteDialog.show();

    }
    //function to show the delete dialog and delete te account ends here.

    //function to sign out from the account:
    private void showLogout() {
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),StudentLoginActivity.class));
        StudentProfileActivity.this.finish();
    }
    //function to signout ends here.

    //function to show the rating dialog and rate institute:
    private void showRatingBar(){

        android.app.AlertDialog.Builder ratingDialog = new android.app.AlertDialog.Builder(StudentProfileActivity.this);
        View view = getLayoutInflater().inflate(R.layout.rating_layout,null);
        //initListsForUpdate(examName);

       /* Log.d(TAG,StudentSignUpActivity.iitjeeInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.medicalEntranceInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.engineeringExamInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.neetPGInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.scienceExamInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.comerceExamInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.upscInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.bankExamInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.collegePlacementTraininhInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.managementExamInstitutes.toString());
        Log.d(TAG,StudentSignUpActivity.greInstitutes.toString());*/

        //getting the views:
        ratingBar = view.findViewById(R.id.rating_bar);
        reviewStudentEdittext = view.findViewById(R.id.review_edittext);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                studentRating = rating;
                Log.d(TAG,"\n details are as follows \n");
                Log.d(TAG,Float.toString(rating)+"\n");
                Log.d(TAG,Float.toString(studentRating)+"\n");


            }
        });

        ratingDialog.setView(view);
        ratingDialog.setTitle("RATE US");
        ratingDialog.create();
        ratingDialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                //data base reference where the student, who has submitted the
                //rating , his information will be stored.
                DatabaseReference mRef = FirebaseDatabase.getInstance()
                        .getReference("users").child("institute")
                        .child(examName).child(coachingName)
                        .child("students of this institute").child(uid);



                studentReview = reviewStudentEdittext.getText().toString().trim();

                //HashMap to store the institute:
                HashMap<String,Object> studentOfInstitute = new HashMap<>();
                studentOfInstitute.put("rating submitted",studentRating);
                studentOfInstitute.put("Review submitted",studentReview);
                studentOfInstitute.put("Student Name",userName);
                studentOfInstitute.put("Phone Number",phNumber);
                //studentOfInstitute.put("D.O.B",dob);
                studentOfInstitute.put("Email Address",email);
                studentOfInstitute.put("Exam Preparing For",examName);
                studentOfInstitute.put("Institute where Studying",instName);
                //hash map to store the institute, ends here.


                //setting a value to the reference:
                mRef.setValue(studentOfInstitute).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){


                            Toast.makeText(getApplicationContext(),"rating submitted successfully",Toast.LENGTH_SHORT).show();


                            //initialising the array lists, again for getting the exact current information::
                        }
                    }
                });

                


                Log.d(TAG,"\n inside set positve button Listener \n");

                Log.d(TAG,studentReview+"\n");
            }
        });
        ratingDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ratingDialog.setCancelable(false);
        ratingDialog.show();


        //sortLists(examName,studentRating);
    }
    //function to show the rating dialog and rate institute ends here.




    //function to fetch data from firebase realtime database:
    private void fetchData(){

        FirebaseDatabase.getInstance().getReference("users").
                child("student").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);

                //get values:
                email = student.getEmailAddressStudent();
                phNumber = student.getPhoneNumberStudent();
                userName = student.getUserNameStudent();
                dob = student.getDobStudent();
                instName = student.getInstituteNameStudent();
                examName = student.getExamName();
                coachingName = student.getCoachingInstituteName();
                setViews();

                //logs display for testing:
                Log.d(TAG,"onDataChanged : Student Information");
                Log.d(TAG,"Email Address : "+email);
                Log.d(TAG,"User Name : "+userName);
                Log.d(TAG,"DOB : "+dob);
                Log.d(TAG,"Phone Number : " + phNumber);
                Log.d(TAG,"Institute Name : "+instName);
                Log.d(TAG,"Exam Preparing For : " + examName);
                Log.d(TAG,"Coaching Institute : " + coachingName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentProfileActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
    //function to fetch data from firebase realtime database ends here.
}
