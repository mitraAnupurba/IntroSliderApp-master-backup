package com.example.introsliderapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introsliderapp.model.Parent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ParentSignUpActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {


    private static final String TAG = "my tag";
    private EditText emailSignUpParent,passwordSignUpParent,
            confirmPasswordSignUpParent,phoneNumberSignUpParent,userNameSignUpParent,
            instNameSignUpParent;
    private Button signUpButtonParent;
    private ProgressBar progressBarParent;
    private TextView examNameTextViewParent,dateOfBirthTextViewParent;
    private Spinner spinnerParent;

    //datepicker variable :
    private DatePickerDialog.OnDateSetListener dateSetListenerParent;

    //firebase auth variable
    private FirebaseAuth mAuth;

    //class ParentSignup variables
    private String email,password,confirmPassword,phoneNumber,
            instituteName,userName,examName,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_sign_up);

        initViews();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ParentSignUpActivity.this, R.array.list_of_exams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerParent.setAdapter(adapter);
        spinnerParent.setOnItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();

        signUpButtonParent.setOnClickListener(this);

        //textView date picker OnCliclListener
        dateOfBirthTextViewParent.setOnClickListener(this);
    }

    private void initViews(){


        emailSignUpParent = this.findViewById(R.id.email_address_editText_parent);
        passwordSignUpParent = this.findViewById(R.id.password_editText_parent);
        signUpButtonParent = this.findViewById(R.id.signup_button_parent);
        progressBarParent = this.findViewById(R.id.progress_bar_parent);
        confirmPasswordSignUpParent = this.findViewById(R.id.confirm_password_editText_parent);
        phoneNumberSignUpParent = this.findViewById(R.id.phone_number_editText_parent);
        userNameSignUpParent = this.findViewById(R.id.user_name_editText_parent);
        instNameSignUpParent = this.findViewById(R.id.institute_name_editText_parent);
        spinnerParent = this.findViewById(R.id.select_exam_spinner_sign_up_parent);
        examNameTextViewParent = this.findViewById(R.id.exam_name_editText_parent);
        dateOfBirthTextViewParent = this.findViewById(R.id.dob_signup_textView_parent);

    }

    //listener for Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        examName = parent.getItemAtPosition(position).toString();

        //selectExamTextView.setText(exam);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent){}


    private void setDate(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int age = calendar.get(Calendar.MILLISECOND);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ParentSignUpActivity.this,
                android.R.style.Theme_Holo_Light_Dialog
                ,dateSetListenerParent,year,month,day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.setCanceledOnTouchOutside(false);
        datePickerDialog.show();


        //initialising object of dateSetListener:
        dateSetListenerParent = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"onDateSetListener :"+dayOfMonth+"/"+month+"/"+year );
                dob = dayOfMonth+"/"+month+"/"+year;
                dateOfBirthTextViewParent.setText(dob);
            }
        };

    }


    private void registerUser(){
        email = emailSignUpParent.getText().toString().trim();
        password = passwordSignUpParent.getText().toString().trim();
        confirmPassword = confirmPasswordSignUpParent.getText().toString().trim();
        phoneNumber = phoneNumberSignUpParent.getText().toString().trim();
        userName = userNameSignUpParent.getText().toString().trim();
        instituteName = instNameSignUpParent.getText().toString().trim();

        if(validate(email,password,confirmPassword,phoneNumber,userName,dob,instituteName)){
            progressBarParent.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        //here we will store the custom fields in firebase database and start the profile activity
                        Parent parent = new Parent(userName,email,phoneNumber,instituteName,examName,dob);
                        FirebaseDatabase.getInstance().getReference("users")
                                .child("parent").child(FirebaseAuth.getInstance()
                                .getCurrentUser().getUid()).setValue(parent)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            progressBarParent.setVisibility(View.GONE);
                                            Toast.makeText(ParentSignUpActivity.this,"user Information stored in db",Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(ParentSignUpActivity.this,"error",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });



                        startActivity(new Intent(getApplicationContext(),ParentProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                        emailSignUpParent.setText("");
                        passwordSignUpParent.setText("");
                        ParentSignUpActivity.this.finish();
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

    private boolean validate(String email, String password,String confirmPassword,String phNumber,String userName,String dob,String instName){
        if(email.isEmpty()){
            emailSignUpParent.setError("Email id field is required");
            emailSignUpParent.requestFocus();
            return false;
        }else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailSignUpParent.setError("Email id field is invalid");
            emailSignUpParent.requestFocus();
            return false;
        }
        else if (password.isEmpty() || confirmPassword.isEmpty() || phNumber.isEmpty() || userName.isEmpty() || dob.isEmpty() || instName.isEmpty()) {
            Toast.makeText(getApplicationContext(),"none of the fields can be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!password.equals(confirmPassword)) {
            passwordSignUpParent.setError("password and confirm password fields must match");
            passwordSignUpParent.requestFocus();
            return false;
        }
        else if(password.length() <6){
            passwordSignUpParent.setError("minimum length of password should be 6");
            passwordSignUpParent.requestFocus();
            return false;
        }
        else{
            return true;
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.signup_button_parent:
                registerUser();
                break;
            case R.id.dob_signup_textView_parent:
                setDate();
                break;
        }
    }

}
