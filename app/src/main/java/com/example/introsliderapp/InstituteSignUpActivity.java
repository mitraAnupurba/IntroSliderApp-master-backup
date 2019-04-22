package com.example.introsliderapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.example.introsliderapp.model.UpscInstitute;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InstituteSignUpActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    //tag for displaying the logs for testing purpose.
        private static final String TAG = "my tag";

    //variables for getting the views:

        //variable for the edittext's where value is entered
        private EditText emailSignUpInstitute,passwordSignUpInstitute,
                confirmPasswordSignUpInstitute,phNumberSignUpInstitute,
                instituteNameSignUpInstitute,instituteAddressSignUpInstitute;

        //variable for buttons:
        private Button signUpButtonInstitute,addAddressButtonInstitute;

        //variable for progressbar
        private ProgressBar progressBarInstitute;

        //variable for spinner:
        private Spinner spinnerInstitute;

        //variable for grid layout where the address will be added dynamically.
        private GridLayout myLayout;

    //firebase suthentication variable
    private  FirebaseAuth mAuth;

    //string variable for storing the institutes data:
    private String email,password,confirmPassword,phNumber,instName,instAddress
                ,instType,newAddres;

    //context variable to be passed to the object of dynamic view class:
    private Context context;

    //variable for storing the uid of the pirticular user:
    String uid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_sign_up);

        initViews();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(InstituteSignUpActivity.this, R.array.list_of_exams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstitute.setAdapter(adapter);
        spinnerInstitute.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();


        signUpButtonInstitute.setOnClickListener(this);
        addAddressButtonInstitute.setOnClickListener(this);
    }

    private void initViews(){


        emailSignUpInstitute = this.findViewById(R.id.email_address_editText_institute);
        passwordSignUpInstitute = this.findViewById(R.id.password_editText__institute);
        signUpButtonInstitute = this.findViewById(R.id.signup_button_institute);
        progressBarInstitute = this.findViewById(R.id.progress_bar_institute);
        confirmPasswordSignUpInstitute = this.findViewById(R.id.confirm_password_editText_institute);
        phNumberSignUpInstitute = this.findViewById(R.id.phone_number_editText_institute);
        instituteNameSignUpInstitute = this.findViewById(R.id.user_name_editText_institute);
        instituteAddressSignUpInstitute = this.findViewById(R.id.address_edittext_institute);
        addAddressButtonInstitute = this.findViewById(R.id.add_address_edittext_institute);
        spinnerInstitute = this.findViewById(R.id.select_type_spinner_sign_up_institute);
        myLayout = this.findViewById(R.id.add_items_layout);

    }


    //listener for Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        instType = parent.getItemAtPosition(position).toString();
        Log.d(TAG,instType);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void addAddress(){

        final AlertDialog.Builder addItemDialog = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.add_item_layout,null);

        final EditText addAddress = v.findViewById(R.id.add_address_edittext);

        addItemDialog.setView(v);
        addItemDialog.setTitle("ADD ITEM");
        addItemDialog.setMessage("enter the address of branch you want to add");
        addItemDialog.create();
        addItemDialog.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DynamicView dmv = new DynamicView(context);
                newAddres = addAddress.getText().toString().trim();
                Log.d(TAG,"new item adding");
                myLayout.addView(dmv.addTextView(getApplicationContext(),newAddres ),1);
                Log.d(TAG,"new item added");
            }
        });
        addItemDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        addItemDialog.setCancelable(false);
        addItemDialog.show();

    }

    private void registerUser(){
        email = emailSignUpInstitute.getText().toString().trim();
        password = passwordSignUpInstitute.getText().toString().trim();
        confirmPassword = confirmPasswordSignUpInstitute.getText().toString().trim();
        phNumber = phNumberSignUpInstitute.getText().toString().trim();
        instName = instituteNameSignUpInstitute.getText().toString().trim();
        instAddress = instituteAddressSignUpInstitute.getText().toString().trim();
        if(validate(email,password,confirmPassword,phNumber,
                instName,instAddress,instType)){
            progressBarInstitute.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBarInstitute.setVisibility(View.GONE);
                    if(task.isSuccessful()){
                        addInstitutes(instName,instAddress,email,phNumber,instType);
                        startActivity(new Intent(getApplicationContext(),InstituteProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                        emailSignUpInstitute.setText("");
                        passwordSignUpInstitute.setText("");
                        InstituteSignUpActivity.this.finish();
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

        }
    }

    private void addInstitutes(String instName, String instAddress, String email, String phNumber, String instType) {

        //database reference variable:
        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute").child(instType).child(instName);


        switch (instType){
            case "IIT-JEE":
                IITJEEInstitute inst = new IITJEEInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst);
                break;

            case "Medical Entrance Exams":
                MedicalEntranceInstitute inst1 = new MedicalEntranceInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst1);
                break;

            case "GATE-IES-ESE":
                EngineeringExamInstitute inst2 = new EngineeringExamInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst2);
                break;

            case "NEET-PG":
                NeetPGInstitute inst3 = new NeetPGInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst3);
                break;

            case "Comerce":
                ComerceExamInstitute inst4 = new ComerceExamInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst4);
                break;

            case "JRF-NET":
                ScienceExamInstitute inst5 = new ScienceExamInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst5);
                break;

            case "UPSC-ICS":
                UpscInstitute inst6 = new UpscInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst6);
                break;

            case "BANK-SBI-PO":
                BankExamInstitute inst7 = new BankExamInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst7);
                break;

            case "College Placements":
                CollegePlacementTraininhInstitute inst8 =
                        new CollegePlacementTraininhInstitute(instName,instAddress,
                        email,phNumber,instType,0.0f);
                mRef.setValue(inst8);
                break;

            case "GRE-IELTS":
                GREInstitute inst9 =
                        new GREInstitute(instName,instAddress,
                                email,phNumber,instType,0.0f);
                mRef.setValue(inst9);
                break;

            case "CAT-MAT":
                ManagementExamInstitute inst10 =
                        new ManagementExamInstitute(instName,instAddress,
                                email,phNumber,instType,0.0f);
                mRef.setValue(inst10);
                break;
            default:
                Toast.makeText(getApplicationContext(),"type doesnt exist",Toast.LENGTH_SHORT).show();
                break;
        }




    }

    private boolean validate(String email, String password,String confirmPassword,String phNumber,
                             String instituteName,String instituteAddress,String instituteType){
        if(email.isEmpty()){
            emailSignUpInstitute.setError("Email id field is required");
            emailSignUpInstitute.requestFocus();
            return false;
        }else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUpInstitute.setError("Email id field is invalid");
            emailSignUpInstitute.requestFocus();
            return false;
        }
        else if (password.isEmpty() || confirmPassword.isEmpty() || phNumber.isEmpty() || instituteName.isEmpty() || instituteAddress.isEmpty() || instituteType.isEmpty() ) {
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phNumber.length() != 10) {
            phNumberSignUpInstitute.setError("Phone number must be 10 digits long");
            phNumberSignUpInstitute.requestFocus();
            return false;
        } else if (password.length() < 6) {
            passwordSignUpInstitute.setError("minimum length of password should be 6");
            passwordSignUpInstitute.requestFocus();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.add_address_edittext_institute:
                addAddress();
                break;
            case R.id.signup_button_institute:
                registerUser();
                break;
        }


    }


}
