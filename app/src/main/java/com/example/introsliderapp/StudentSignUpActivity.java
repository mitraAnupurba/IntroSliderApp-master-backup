package com.example.introsliderapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
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

//class for the student sign up page functionality:

public class StudentSignUpActivity extends AppCompatActivity implements View.OnClickListener{

    //log tag for displaying Logs for testing purpose:
    private static final String TAG = "my tag";

    //Variables for initialising the views:

        //edittext variables:
        private EditText emailSignUp,passwordSignUp,
                confirmPasswordSignUp,phoneNumberSignUp,userNameSignUp,
                instNameSignUp;
        //button variable
        private Button signUpButton;
        //progressbar variable
        private ProgressBar progressBar;
        //textview variable:
        private TextView examNameTextViewStudent,dateOfBirthTextViewStudent,
                        seleceCoachingName;
        //spinner variable:
        private Spinner spinnerStudent,spinnerCoaching;

        //datepicker variable :
        private DatePickerDialog.OnDateSetListener dateSetListener;

    //firebase auth variable
    private FirebaseAuth mAuth;

    //variables for taking values from the views:
    private String email,password,confirmPassword,phoneNumber,
            instituteName,userName,dob,
    examName,coachingName;


    //arraylist variables for different institutes:
    /*public static List<IITJEEInstitute> iitjeeInstitutes = new ArrayList<>();
    public static List<MedicalEntranceInstitute> medicalEntranceInstitutes = new ArrayList<>();
    public static List<EngineeringExamInstitute> engineeringExamInstitutes = new ArrayList<>();
    public static List<NeetPGInstitute> neetPGInstitutes = new ArrayList<>();
    public static List<ComerceExamInstitute> comerceExamInstitutes = new ArrayList<>();
    public static List<ScienceExamInstitute> scienceExamInstitutes = new ArrayList<>();
    public static List<UpscInstitute> upscInstitutes = new ArrayList<>();
    public static List<BankExamInstitute> bankExamInstitutes = new ArrayList<>();
    public static List<CollegePlacementTraininhInstitute>
            collegePlacementTraininhInstitutes = new ArrayList<>();
    public static List<GREInstitute> greInstitutes = new ArrayList<>();
    public static List<ManagementExamInstitute>
            managementExamInstitutes = new ArrayList<>();
    public static List<Institute> Institutes = new ArrayList<>();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        initViews();

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


        //taking the array adapter object that converts a dropdown ment
        //to a list of views:
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(StudentSignUpActivity.this, R.array.list_of_exams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStudent.setAdapter(adapter);
        spinnerStudent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                examName = parent.getItemAtPosition(position).toString();
                Log.d(TAG, examName);
                //initLists(examName);
                setSpinnerCoachingContent(examName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //spinnerCoaching.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();

        //signup button on click listener:
        signUpButton.setOnClickListener(this);

        //textView date picker OnCliclListener
        dateOfBirthTextViewStudent.setOnClickListener(this);

    }

    //function to initialise the views:
    private void initViews(){


        emailSignUp = this.findViewById(R.id.email_address_editText);
        passwordSignUp = this.findViewById(R.id.password_editText);
        signUpButton = this.findViewById(R.id.signup_button);
        progressBar = this.findViewById(R.id.progress_bar);
        confirmPasswordSignUp = this.findViewById(R.id.confirm_password_editText);
        phoneNumberSignUp = this.findViewById(R.id.phone_number_editText);
        userNameSignUp = this.findViewById(R.id.user_name_editText);
        instNameSignUp = this.findViewById(R.id.institute_name_editText);
        spinnerStudent = this.findViewById(R.id.select_exam_spinner_sign_up_student);
        examNameTextViewStudent = this.findViewById(R.id.exam_name_editText);
        dateOfBirthTextViewStudent = this.findViewById(R.id.dob_student_signup_textView);
        seleceCoachingName = this.findViewById(R.id.coaching_name_editText);
        spinnerCoaching = this.findViewById(R.id.select_coaching_spinner_sign_up_student);

    }
    //function to initialise the views ends here.



    //function to set the content of dynamic spinner named : spinner coaching
    //NOTE FOR AYAN CHOUDHURY: the probllem is with this spinner:
    private void setSpinnerCoachingContent(String examName){

        Log.d(TAG,"Enters Select Spinner Content");
        switch(examName){

            case "IIT-JEE":
                Log.d(TAG,"Enters IIT-JEE Exams");
                ArrayAdapter<IITJEEInstitute> adapter = new ArrayAdapter<IITJEEInstitute>(this,  android.R.layout.simple_spinner_item,iitjee);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener);
                break;

            case "Medical Entrance Exams":
                Log.d(TAG,"Enters Medical Entrance Exams");
                ArrayAdapter<MedicalEntranceInstitute> adapter1 = new ArrayAdapter<MedicalEntranceInstitute>(this,  android.R.layout.simple_spinner_item,medicalEntrance);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener1 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener1);
                break;

            case "GATE-IES-ESE":
                ArrayAdapter<EngineeringExamInstitute> adapter2 = new ArrayAdapter<EngineeringExamInstitute>(this,  android.R.layout.simple_spinner_item,engineeringExam);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener2 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener2);
                break;

            case "NEET-PG":
                ArrayAdapter<NeetPGInstitute> adapter3 = new ArrayAdapter<NeetPGInstitute>(this,  android.R.layout.simple_spinner_item,neetPG);
                adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener3 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener3);
                break;

            case "Comerce":
                ArrayAdapter<ComerceExamInstitute> adapter4 = new ArrayAdapter<ComerceExamInstitute>(this,  android.R.layout.simple_spinner_item,comerceExam);
                adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter4);
                adapter4.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener4 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener4);
                break;

            case "JRF-NET":
                ArrayAdapter<ScienceExamInstitute> adapter5 = new ArrayAdapter<ScienceExamInstitute>(this,  android.R.layout.simple_spinner_item,scienceExam);
                adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter5);
                adapter5.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener5 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener5);
                break;

            case "UPSC-ICS":
                ArrayAdapter<UpscInstitute> adapter6 = new ArrayAdapter<UpscInstitute>(this,  android.R.layout.simple_spinner_item,upsc);
                adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter6);
                adapter6.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener6 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener6);
                break;

            case "BANK-SBI-PO":
                ArrayAdapter<BankExamInstitute> adapter7 = new ArrayAdapter<BankExamInstitute>(this,  android.R.layout.simple_spinner_item,bankExam);
                adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter7);
                adapter7.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener7 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener7);
                break;

            case "College Placements":
                ArrayAdapter<CollegePlacementTraininhInstitute> adapter8 = new ArrayAdapter<CollegePlacementTraininhInstitute>(this,  android.R.layout.simple_spinner_item,collegePlacementTraininh);
                adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter8);
                adapter8.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener8 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener8);
                break;

            case "GRE-IELTS":
                ArrayAdapter<GREInstitute> adapter9 = new ArrayAdapter<GREInstitute>(this,  android.R.layout.simple_spinner_item,gre);
                adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter9);
                adapter9.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener9 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener9);
                break;

            case "CAT-MAT":
                ArrayAdapter<ManagementExamInstitute> adapter10 = new ArrayAdapter<ManagementExamInstitute>(this,  android.R.layout.simple_spinner_item,managementExam);
                adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCoaching.setAdapter(adapter10);
                adapter10.notifyDataSetChanged();
                AdapterView.OnItemSelectedListener listener10 = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            coachingName = parent.getItemAtPosition(position).toString();
                            Log.d(TAG, coachingName);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                };
                spinnerCoaching.setOnItemSelectedListener(listener10);
                break;
        }


    }
    //function to set the content of dynamic spinner named : spinner coaching ends here

    //function for setting the date:
    private void setDate(){

        //calender object for getting the selected day, month and year:
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int age = calendar.get(Calendar.MILLISECOND);

        //creating and implementing the datepicker dialog:
        DatePickerDialog datePickerDialog = new DatePickerDialog(StudentSignUpActivity.this,
                android.R.style.Theme_Holo_Light_Dialog
                ,dateSetListener,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();


        //initialising object of dateSetListener:
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSetListener :"+dayOfMonth+"/"+month+"/"+year );
                dob = dayOfMonth+"/"+month+"/"+year;
                dateOfBirthTextViewStudent.setText(dob);
            }
        };

    }
    ////function for setting the date ends here.


    //function for registering the user and storing its data in firebase database:
    private void registerUser(){
        email = emailSignUp.getText().toString().trim();
        password = passwordSignUp.getText().toString().trim();
        confirmPassword = confirmPasswordSignUp.getText().toString().trim();
        phoneNumber = phoneNumberSignUp.getText().toString().trim();
        userName = userNameSignUp.getText().toString().trim();
        instituteName = instNameSignUp.getText().toString().trim();

        if(validate(email,password,confirmPassword,phoneNumber,userName,dob,instituteName)){
            progressBar.setVisibility(View.VISIBLE);

            //function for creating the user with email and password:
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        //here we will store the custom fields in firebase database and start the profile activity
                        Student student = new Student(userName,email,phoneNumber,instituteName,examName,dob,coachingName);
                        FirebaseDatabase.getInstance().getReference("users")
                        .child("student").child(FirebaseAuth.getInstance()
                                .getCurrentUser().getUid()).setValue(student)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(StudentSignUpActivity.this,"user Information stored in db",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(StudentSignUpActivity.this,"error",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                        startActivity(new Intent(getApplicationContext(),StudentProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                        emailSignUp.setText("");
                        passwordSignUp.setText("");
                        StudentSignUpActivity.this.finish();
                    }
                    else{
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(), "User with this email is already registered ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            //function for creating the user with email and password ends here.

        }
    }

    //function to validate the user details provided:
    private boolean validate(String email, String password,String confirmPassword,String phNumber,String userName,String dob,String instName){
        if(email.isEmpty()){
            emailSignUp.setError("Email id field is required");
            emailSignUp.requestFocus();
            return false;
        }else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUp.setError("Email id field is invalid");
            emailSignUp.requestFocus();
            return false;
        }
        else if (password.isEmpty() || confirmPassword.isEmpty() || phNumber.isEmpty() || userName.isEmpty() || dob.isEmpty() || instName.isEmpty()) {
            Toast.makeText(getApplicationContext(),"none of the fields can be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(phNumber.length() != 10){
            phoneNumberSignUp.setError("Phone number length must be 10 digits");
            phoneNumberSignUp.requestFocus();
            return false;
        }
        else if (!password.equals(confirmPassword)) {
            passwordSignUp.setError("password and confirm password fields must match");
            passwordSignUp.requestFocus();
            return false;
        }
        else if(password.length() <6){
            passwordSignUp.setError("minimum length of password should be 6");
            passwordSignUp.requestFocus();
            return false;
        }
        else{
            return true;
        }

    }
    //function to validate the user details ends here:

    //method to be implemented for the View.OnlickListener interface:
    @Override
    public void onClick(View v) {


        switch(v.getId()){
            case R.id.signup_button:
                registerUser();
                break;
            case R.id.dob_student_signup_textView:
                setDate();
                break;

        }
    }
    //method to be implemented for the View.OnlickListener interface ends here.


}
