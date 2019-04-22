package com.example.introsliderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introsliderapp.model.Parent;
import com.example.introsliderapp.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ParentProfileActivity extends AppCompatActivity {

    private static final String TAG ="my tag" ;
    private FirebaseAuth mAuth;
    private String uid;

    private Toolbar toolbar;

    //edittext's for update dialog
    private EditText phoneNumberParentUpdate
            ,userNameParentUpdate,
            instNameParentUpdate,examNameParentUpdate;

    private TextView emailAddressParent,phoneNumberParent,userNameParent,
            dobParent,instNameParent,examNameParent;

    private String email,phNumber,userName,dob,instName,examName;

    //strings for storing the updated value:
    private String phNumberUpdatedValue,userNameUpdatedValue
            ,instNameUpdatedValue,examNameUpdatedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        initViews();
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        //fetch data:
        fetchData();



    }

    private void initViews(){

        emailAddressParent = this.findViewById(R.id.email_address_textView_parent);
        phoneNumberParent = this.findViewById(R.id.phone_number_textView_parent);
        userNameParent = this.findViewById(R.id.user_name_textView_parent);
        dobParent = this.findViewById(R.id.dob_signup_textView_parent);
        instNameParent = this.findViewById(R.id.institute_name_textView_parent);
        examNameParent = this.findViewById(R.id.exam_name_textView_parent);
        toolbar = this.findViewById(R.id.toolbar_parent_profile);
    }

    private void setViews(){
        emailAddressParent.setText("Email Address : "+email);
        phoneNumberParent.setText("Phone NUmber : "+phNumber);
        userNameParent.setText("User Name : "+userName);
        dobParent.setText("Date Of Birth"+dob);
        instNameParent.setText("Institute Name : "+instName);
        examNameParent.setText("Exam Preparing For : "+examName);
        toolbar.setTitle(userName);
        toolbar.setSubtitle("welcome");
    }

    //function to map the menu_main layout to out layout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    //function to make a functional toolbar:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.update:
                //showUpdateDialog();
                Toast.makeText(getApplicationContext(),"Update Clicked",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                showDeleteDialog();
                break;
            case R.id.logout:
                showLogout();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteDialog() {

        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(ParentProfileActivity.this);
        deleteDialog.setMessage("Are you sure you want to delete this account?");
        deleteDialog.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseReference mRef = FirebaseDatabase.getInstance()
                        .getReference("users").child("parent").child(uid);

                //firebase current user to be deleted:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                mRef.removeValue();
                mAuth.signOut();
                user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ParentProfileActivity.this,"User Deleted",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),ParentLoginActivity.class));
                            ParentProfileActivity.this.finish();

                        }
                    }
                });
                Toast.makeText(ParentProfileActivity.this,"Account Deleted",Toast.LENGTH_SHORT).show();

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

    private void showLogout() {

        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(),ParentLoginActivity.class));
        ParentProfileActivity.this.finish();

    }

   /* private void showUpdateDialog() {

        //variable to get Alert dialog:
        AlertDialog.Builder updateDialogParent = new AlertDialog.Builder(ParentProfileActivity.this);
        View v = getLayoutInflater().inflate(R.layout.update_dialog,null);

        //getting the views:
        phoneNumberParentUpdate = v.findViewById(R.id.phone_number_edittext_update);
        userNameParentUpdate = v.findViewById(R.id.user_name_edittext_update);
        instNameParentUpdate = v.findViewById(R.id.institute_name_edittext_update);
        examNameParentUpdate = v.findViewById(R.id.exam_name_edittext_update);

        //seting default values to the view items:
        phoneNumberParentUpdate.setText(phNumber);
        userNameParentUpdate.setText(userName);
        instNameParentUpdate.setText(instName);
        examNameParentUpdate.setText(examName);

        updateDialogParent.setView(v);
        updateDialogParent.setTitle("UPDATE VALUES: Email and D.O.B are NON Editable");
        updateDialogParent.create();
        updateDialogParent.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phNumberUpdatedValue = phoneNumberParentUpdate.getText().toString().trim();
                userNameUpdatedValue = userNameParentUpdate.getText().toString().trim();
                instNameUpdatedValue = instNameParentUpdate.getText().toString().trim();
                examNameUpdatedValue = examNameParentUpdate.getText().toString().trim();

                if(phNumberUpdatedValue.isEmpty()){
                    phoneNumberParentUpdate.setError("field is empty");
                    phoneNumberParentUpdate.requestFocus();
                    return;
                }
                else if(userNameUpdatedValue.isEmpty()){
                    userNameParentUpdate.setError("field is empty");
                    userNameParentUpdate.requestFocus();
                    return;
                }
                else if(instNameUpdatedValue.isEmpty()){
                    instNameParentUpdate.setError("field is empty");
                    instNameParentUpdate.requestFocus();
                    return;
                }
                else if(examNameUpdatedValue.isEmpty()){
                    examNameParentUpdate.setError("field is empty");
                    examNameParentUpdate.requestFocus();
                    return;
                }
                else{
                    updateData(phNumberUpdatedValue,userNameUpdatedValue
                            ,instNameUpdatedValue,examNameUpdatedValue);

                    phoneNumberParent.setText(phNumberUpdatedValue);
                    userNameParent.setText(userNameUpdatedValue);
                    instNameParent.setText(instNameUpdatedValue);
                    examNameParent.setText(examNameUpdatedValue);
                }


            }
        });
        updateDialogParent.setNegativeButton("BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        updateDialogParent.setCancelable(false);
        updateDialogParent.show();

        Toast.makeText(this,"update clicked",Toast.LENGTH_SHORT).show();
    }

    private void updateData(String phNumberUpdatedValue, String userNameUpdatedValue, String instNameUpdatedValue, String examNameUpdatedValue) {

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("parent").child(uid);
        Parent updatedParent = new Parent(userNameUpdatedValue,email,phNumberUpdatedValue
                ,instNameUpdatedValue,examNameUpdatedValue,dob);
        mRef.setValue(updatedParent).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ParentProfileActivity.this,"data updated successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    */



    //function to fetch data from firebase realtime database:
    private void fetchData(){

        FirebaseDatabase.getInstance().getReference("users").
                child("parent").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Parent parent = dataSnapshot.getValue(Parent.class);

                //get values:
                email = parent.getEmailAddressParent();
                phNumber = parent.getPhoneNumberParent();
                userName = parent.getUserNameParent();
                dob = parent.getDobParent();
                instName = parent.getInstituteNameParent();
                examName = parent.getExamNameParent();

                setViews();

                //logs display for testing:
                Log.d(TAG,"onDataChanged : Parent Information");
                Log.d(TAG,"Email Address : "+email);
                Log.d(TAG,"User Name : "+userName);
                Log.d(TAG,"DOB : "+dob);
                Log.d(TAG,"Phone Number : " + phNumber);
                Log.d(TAG,"Institute Name : "+instName);
                Log.d(TAG,"Exam Preparing For : " + examName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ParentProfileActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
