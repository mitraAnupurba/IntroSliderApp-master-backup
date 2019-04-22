package com.example.introsliderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.introsliderapp.model.Institute;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InstituteProfileActivity extends AppCompatActivity {

    private static final String TAG ="My tag" ;
    private Button logoutButtonInstitute;
    FirebaseAuth mAuth;
    FirebaseUser user;

    //private variables for the text views:
        private TextView instituteEmailTextView, institutePhNumberTextView,
            institutenameTextView,instituteTypeTextView,instituteAddressTextView;

    //variables to hold the initial values of the institute user data:
        private String emailInstitute,phNumberInstitute,
                    nameInstitute,typeInstitute,addressInstitute;

    //variable for toolbar:
        private Toolbar toolbarInstitute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_profile);
        initViews();
        mAuth = FirebaseAuth.getInstance();
        //user = mAuth.getCurrentUser();
        //Log.d(TAG,user.getUid());
        //Log.d(TAG,user.getDisplayName());
        //Log.d(TAG,user.getEmail());

        fetchData();

        logoutButtonInstitute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),InstituteLoginActivity.class));
                InstituteProfileActivity.this.finish();
            }
        });

    }

    private void initViews(){

        instituteEmailTextView = this.findViewById(R.id.email_address_textView_institute);
        institutePhNumberTextView = this.findViewById(R.id.phone_number_textView_institute);
        institutenameTextView = this.findViewById(R.id.institute_name_textView_institute);
        instituteTypeTextView = this.findViewById(R.id.institute_type_textView_institute);
        instituteAddressTextView = this.findViewById(R.id.institute_address_textView_institute);
        logoutButtonInstitute = this.findViewById(R.id.logout_button_institute);
        toolbarInstitute = this.findViewById(R.id.toolbar_institute_profile);
    }

    private  void fetchData(){
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("COMING SOON");
        alertDialog.setMessage("The institute profile page is under built");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.create();
        alertDialog.setCancelable(false);
        alertDialog.show();


    }


}
