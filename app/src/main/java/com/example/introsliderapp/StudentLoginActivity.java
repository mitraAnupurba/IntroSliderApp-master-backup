package com.example.introsliderapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StudentLoginActivity extends AppCompatActivity {


    private TextView signUpTextView;
    private EditText emailLogin,passwordLogin;
    private Button loginButton;

    FirebaseAuth mAuth;
    private String email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        initViews();
        mAuth = FirebaseAuth.getInstance();


        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),StudentSignUpActivity.class));
                StudentLoginActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });



    }

    private void initViews(){

        emailLogin = this.findViewById(R.id.student_login_email_editText);
        passwordLogin = this.findViewById(R.id.student_login_password_editText);
        loginButton = this.findViewById(R.id.login_button);
        signUpTextView = this.findViewById(R.id.sign_up_textView);
    }

    private void userLogin(){

        email = emailLogin.getText().toString().trim();
        password = passwordLogin.getText().toString().trim();
        if(validate(email,password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        startActivity(new Intent(getApplicationContext(), StudentProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"user successfully logged in",Toast.LENGTH_SHORT).show();
                        StudentLoginActivity.this.finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        emailLogin.setText("");
                        passwordLogin.setText("");
                    }
                }
            });
        }
        else{
            Toast.makeText(getApplicationContext(),"User Credentials invalid",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){

            startActivity(new Intent(getApplicationContext(),StudentProfileActivity.class));
        }
    }

    private boolean validate(String email, String password){
        if(email.isEmpty()){
            emailLogin.setError("Email id field is required");
            emailLogin.requestFocus();
            return false;
        }else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailLogin.setError("Email id field is invalid");
            emailLogin.requestFocus();
            return false;
        }
        else if (password.isEmpty()) {
            passwordLogin.setError("password field is required");
            passwordLogin.requestFocus();
            return false;
        }
        else if(password.length() <6){
            passwordLogin.setError("minimum length of password should be 6");
            passwordLogin.requestFocus();
            return false;
        }
        else{
            return true;
        }

    }


    public void loadForgetPassword(View view) {
    }



}
