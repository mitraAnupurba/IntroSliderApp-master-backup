package com.example.introsliderapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class InstituteLoginActivity extends AppCompatActivity {

    private TextView signUpTextViewInstitute;
    private EditText emailLoginInstitute, passwordLoginInstitute;
    private Button loginButtonInstitute;

    FirebaseAuth mAuth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_login);

        initViews();
        mAuth = FirebaseAuth.getInstance();


        signUpTextViewInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), InstituteSignUpActivity.class));
                InstituteLoginActivity.this.finish();
            }
        });

        loginButtonInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


    }

    private void initViews() {

        emailLoginInstitute = this.findViewById(R.id.institute_login_email_editText);
        passwordLoginInstitute = this.findViewById(R.id.institute_login_password_editText);
        loginButtonInstitute = this.findViewById(R.id.institute_login_button);
        signUpTextViewInstitute = this.findViewById(R.id.institute_sign_up_textView);
    }

    private void userLogin() {

        email = emailLoginInstitute.getText().toString().trim();
        password = passwordLoginInstitute.getText().toString().trim();
        if (validate(email, password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(getApplicationContext(), InstituteProfileActivity.class));
                        Toast.makeText(getApplicationContext(), "user successfully logged in", Toast.LENGTH_SHORT).show();
                        InstituteLoginActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "User Credentials invalid", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {

            startActivity(new Intent(getApplicationContext(), InstituteProfileActivity.class));
        }
    }

    private boolean validate(String email, String password) {
        if (email.isEmpty()) {
            emailLoginInstitute.setError("Email id field is required");
            emailLoginInstitute.requestFocus();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailLoginInstitute.setError("Email id field is invalid");
            emailLoginInstitute.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            passwordLoginInstitute.setError("password field is required");
            passwordLoginInstitute.requestFocus();
            return false;
        } else if (password.length() < 6) {
            passwordLoginInstitute.setError("minimum length of password should be 6");
            passwordLoginInstitute.requestFocus();
            return false;
        } else {
            return true;
        }
    }
    public void loadForgetPassword(View view) {

    }

}

