package com.example.introsliderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*public class InstituteProformaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_proforma);
    }
}*/



import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introsliderapp.model.BankExamInstitute;
import com.example.introsliderapp.model.CollegePlacementTraininhInstitute;
import com.example.introsliderapp.model.ComerceExamInstitute;
import com.example.introsliderapp.model.EngineeringExamInstitute;
import com.example.introsliderapp.model.GREInstitute;
import com.example.introsliderapp.model.IITJEEInstitute;
import com.example.introsliderapp.model.ManagementExamInstitute;
import com.example.introsliderapp.model.MedicalEntranceInstitute;
import com.example.introsliderapp.model.NeetPGInstitute;
import com.example.introsliderapp.model.ScienceExamInstitute;
import com.example.introsliderapp.model.UpscInstitute;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstituteProformaActivity extends AppCompatActivity {
    private static final String TAG ="My tag" ;
    CircleImageView insitiuteImage;
    boolean isImageFitToScreen;

    //text views in the information tab:
    TextView about;
    TextView phoneNumber;
    TextView mailid;
    TextView addr;

    //variables for getting the extra values from the intent:
    private String instituteName,instituteType;

    //variables for fetching data from firebase :
    private String address,phNumber,email;

    //click event for image.
    public void fullImage(View view){
        insitiuteImage = (CircleImageView) findViewById(R.id.instituteimage);
        Toast.makeText(this, "Institute image is clicked", Toast.LENGTH_SHORT).show();
    }
    //click event for image ends here.


    //click event for the information layout tab:(NOT USED)
    public void info(View view){

        //startActivity(new Intent(InstituteProformaActivity.this));
        //Toast.makeText(this, "Info was clicked", Toast.LENGTH_SHORT).show();
    }
    //click event for the information layout tab ends here.


    //click event for the ratings activity tab.
    public void ratings(View view){
        Intent intent = new Intent(this,InstituteRatingsActivity.class);
        startActivity(intent);
        // Toast.makeText(this, "Ratings was clicked", Toast.LENGTH_SHORT).show();
    }
    //click event for the rating avctivity tab ends here.


    //click event for the branches activity  tab here.
    public void branches(View view){
        Intent intent = new Intent(this,InstituteBranchesActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Branches was clicked", Toast.LENGTH_SHORT).show();
    }
    //click event for the branches tab ends here.

    //
    public void contactInstitute(View view){

        //Toast.makeText(this, view.getId()+"", Toast.LENGTH_SHORT).show();
        if(view.getId()==R.id.mail) {
               //Toast.makeText(this, "Mail was clicked", Toast.LENGTH_SHORT).show();
            String subject = "";
            String body = "";
            String mail = mailid.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:"+mail+"?subject=" + subject + "&body=" + body);
            intent.setData(data);
            startActivity(intent);
        }
        else if(view.getId()==R.id.phone) {
            String number = phoneNumber.getText().toString();
               //Toast.makeText(this, "Phone is clicked", Toast.LENGTH_SHORT).show();
            if(ContextCompat.checkSelfPermission(InstituteProformaActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(InstituteProformaActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Phone", number);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(this, "Since you have not granted permissions, the number is copied to your clipboard", Toast.LENGTH_LONG).show();
            }else {
                dialContactPhone(number);
            }
        }
    } public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                phoneNumber = (TextView)findViewById(R.id.institute_profile_phoneNumber);
                String number = phoneNumber.getText().toString();
                dialContactPhone(number);
            }
        }
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_proforma);
        String ipsem="er accumsan dignissim placerat. Praesent molestie, nunc gravida finibus gravida, nibh magna egestas massa, a imperdiet turpis mi porta lacus. Pellentesque fringilla mi ut elit porttitor tristique. Duis sed ligula id arcu mattis commodo. Suspendisse elementum accumsan leo, non consequat ipsum iaculis a. Vivamus convallis turpis augue, vel blandit lorem semper consequat. Nam quis risus vel sem pellentesque cursus. Praesent nec posuere tortor. Duis nec mi interdum, lobortis nunc sed, ultrices quam. Etiam et lectus risus.Praesent venenatis dui quis egestas feugiat. Nunc ornare et risus sit amet varius. Donec lacus erat, pharetra a lorem congue, pellentesque aliquet nisi. Cras elementum at arcu quis pulvinar. Vivamus nec tortor id nibh egestas scelerisque. Nunc aliquam consequat ante non vehicula. Aliquam tempus magna mi, sit amet efficitur est tincidunt sit amet. Pellentesque id ex non diam pharetra pharetra. Morbi tincidunt in purus eu hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus sodales, augue at semper maximus, quam leo hendrerit felis, consectetur consequat nisi orci ut est. Nulla tellus nisl, varius eget ex non, malesuada tristique odio.Praesent venenatis dui quis egestas feugiat. Nunc ornare et risus sit amet varius. Donec lacus erat, pharetra a lorem congue, pellentesque aliquet nisi. Cras elementum at arcu quis pulvinar. Vivamus nec tortor id nibh egestas scelerisque. Nunc aliquam consequat ante non vehicula. Aliquam tempus magna mi, sit amet efficitur est tincidunt sit amet. Pellentesque id ex non diam pharetra pharetra. Morbi tincidunt in purus eu hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus sodales, augue at semper maximus, quam leo hendrerit felis, consectetur consequat nisi orci ut est. Nulla tellus nisl, varius eget ex non, malesuada tristique odio.Praesent venenatis dui quis egestas feugiat. Nunc ornare et risus sit amet varius. Donec lacus erat, pharetra a lorem congue, pellentesque aliquet nisi. Cras elementum at arcu quis pulvinar. Vivamus nec tortor id nibh egestas scelerisque. Nunc aliquam consequat ante non vehicula. Aliquam tempus magna mi, sit amet efficitur est tincidunt sit amet. Pellentesque id ex non diam pharetra pharetra. Morbi tincidunt in purus eu hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus sodales, augue at semper maximus, quam leo hendrerit felis, consectetur consequat nisi orci ut est. Nulla tellus nisl, varius eget ex non, malesuada tristique odio.";

        //instantiating the views for contact institute:
        about = (TextView)findViewById(R.id.institute_profile_aboutUs);
        addr =  this.findViewById(R.id.institute_profile_address);
        mailid = (TextView)findViewById(R.id.institute_profile_mailid);
        phoneNumber = (TextView)findViewById(R.id.institute_profile_phoneNumber);

        //setting view of about us:
        about.setText(ipsem);

        Intent intent = getIntent();
        instituteName = intent.getStringExtra("Institute Name");
        instituteType = intent.getStringExtra("Institute Type");

        Log.d(TAG,instituteName +"  "+ instituteType);

        //calling function to fetch data from firebase:
        fetchData();




    }

    private void fetchData(){

        switch(instituteType){
            case  "IIT-JEE" :
                iitjeeFetch();
                break;
            case "Medical Entrance Exams":
                medicalFetch();
                break;
            case "GATE-IES-ESE":
                engineeringFetch();
                break;
            case "NEET-PG":
                medicalPGFetch();
                break;
            case "Comerce":
                comerceFetch();
                break;
            case "JRF-NET":
                scienceFetch();
                break;
            case "UPSC-ICS":
                upscFetch();
                break;
            case "BANK-SBI-PO":
                bankFetch();
                break;
            case "College Placements":
                placementsFetch();
                break;
            case "GRE-IELTS":
                greFetch();
                break;
            case "CAT-MAT":
                managementFetch();
                break;
        }
    }

    //calling function to set views:
    private void setViews(){

        //setting the views after data fetch from firebase database:
        addr.setText(address);
        mailid.setText(email);
        phoneNumber.setText(phNumber);
    }

    //calling function for iitjee institutes fetch:
    private void iitjeeFetch() {

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("IIT-JEE").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                IITJEEInstitute institute = dataSnapshot.getValue(IITJEEInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //calling function for iitjee fetch data ends here.

    // calling function for medical fetch starts here:
    private void medicalFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("Medical Entrance Exams").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MedicalEntranceInstitute institute =
                        dataSnapshot.getValue(MedicalEntranceInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    // calling function for medical fetch ends here:

    //calling function for engineering institutes data fetch starts here:
    private void engineeringFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("GATE-IES-ESE").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                EngineeringExamInstitute institute =
                        dataSnapshot.getValue(EngineeringExamInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for engineering institutes data fetch ends here:

    //calling function for medical PG exams data fetch starts here:
    private void medicalPGFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("NEET-PG").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NeetPGInstitute institute =
                        dataSnapshot.getValue(NeetPGInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for medical PG exams data fetch ends here:

    //calling function for comerce institute data fetch starts here:
    private void comerceFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("Comerce").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ComerceExamInstitute institute =
                        dataSnapshot.getValue(ComerceExamInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //calling function for comerce institute data fetch ends here:

    //calling function for JRF NET exam data fetch starts here:
    private void scienceFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("JRF-NET").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ScienceExamInstitute institute =
                        dataSnapshot.getValue(ScienceExamInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for JRF NET exam data fetch ends here:

    //calling function for upsc institutes data fetch starts here;
    private void upscFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("UPSC-ICS").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UpscInstitute institute =
                        dataSnapshot.getValue(UpscInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for upsc institutes data fetch ends here;

    //calling function for bank exams fetch starts here:
    private void bankFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("BANK-SBI-PO").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                BankExamInstitute institute =
                        dataSnapshot.getValue(BankExamInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //calling function for bank exams fetch ends here:

    //calling function for placement training institute data fetch starts here:
    private void placementsFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("College Placements").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                CollegePlacementTraininhInstitute institute =
                        dataSnapshot.getValue(CollegePlacementTraininhInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    //calling function for placement training institute data fetch ends here:

    //calling function for gre institutes data fetch starts here:
    private void greFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("GRE-IELTS").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GREInstitute institute =
                        dataSnapshot.getValue(GREInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for gre institutes data fetch ends here:

    //calling function for management institutes data fetch starts here :
    private void managementFetch(){

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("users").child("institute")
                .child("CAT-MAT").child(instituteName);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ManagementExamInstitute institute =
                        dataSnapshot.getValue(ManagementExamInstitute.class);
                address = institute.getInstituteAddress();
                phNumber = institute.getPhoneNumber();
                email = institute.getEmailAddress();

                Log.d(TAG,address+"  "+phNumber+"  "+email);
                setViews();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    //calling function for management institutes data fetch ends here :

}


