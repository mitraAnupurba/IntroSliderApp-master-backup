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

public class ParentLoginActivity extends AppCompatActivity {


    private TextView signUpTextViewParent;
    private EditText emailLoginParent,passwordLoginParent;
    private Button loginButtonParent;

    FirebaseAuth mAuth;
    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_login);

        initViews();
        mAuth = FirebaseAuth.getInstance();


        signUpTextViewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ParentSignUpActivity.class));
                ParentLoginActivity.this.finish();
            }
        });

        loginButtonParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void initViews(){

        emailLoginParent = this.findViewById(R.id.parent_login_email_editText);
        passwordLoginParent = this.findViewById(R.id.parent_login_password_editText);
        loginButtonParent = this.findViewById(R.id.parent_login_button);
        signUpTextViewParent = this.findViewById(R.id.parent_sign_up_textView);
    }

    private void userLogin(){

        email = emailLoginParent.getText().toString().trim();
        password = passwordLoginParent.getText().toString().trim();
        if(validate(email,password)) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        startActivity(new Intent(getApplicationContext(), ParentProfileActivity.class));
                        Toast.makeText(getApplicationContext(),"user successfully logged in",Toast.LENGTH_SHORT).show();
                        ParentLoginActivity.this.finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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

            startActivity(new Intent(getApplicationContext(),ParentProfileActivity.class));
        }
    }

    private boolean validate(String email, String password){
        if(email.isEmpty()){
            emailLoginParent.setError("Email id field is required");
            emailLoginParent.requestFocus();
            return false;
        }else if( !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailLoginParent.setError("Email id field is invalid");
            emailLoginParent.requestFocus();
            return false;
        }
        else if (password.isEmpty()) {
            passwordLoginParent.setError("password field is required");
            passwordLoginParent.requestFocus();
            return false;
        }
        else if(password.length() <6){
            passwordLoginParent.setError("minimum length of password should be 6");
            passwordLoginParent.requestFocus();
            return false;
        }
        else{
            return true;
        }

    }




    public void loadForgetPassword(View view) {
    }


}
