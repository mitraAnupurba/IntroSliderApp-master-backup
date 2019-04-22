package com.example.introsliderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
    }

    public void loadViewInstActivity(View view) {
        startActivity(new Intent(getApplicationContext(),AdminViewInstitute.class));
    }

    public void loadViewStudentActivity(View view) {
        startActivity(new Intent(getApplicationContext(),AdminViewStudent.class));
    }

    public void loadViewParentActvity(View view) {
        startActivity(new Intent(getApplicationContext(),AdminViewParent.class));
    }

    public void logOutAdmin(){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        this.finish();
    }
}
