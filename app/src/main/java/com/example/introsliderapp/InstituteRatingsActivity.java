package com.example.introsliderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*public class InstituteRatingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_ratings);
    }
}
*/


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.introsliderapp.model.Guest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstituteRatingsActivity extends AppCompatActivity {
    CircleImageView insitiuteImage;
    boolean isImageFitToScreen;
    TextView about;
    int ratingByGuest ;
    String reviewByGuest;
    private String instituteName;
    private String instituteType;
    private EditText reviewByGuestEdittext;

    public void fullImage(View view){
        insitiuteImage = (CircleImageView) findViewById(R.id.instituteimage);
        Toast.makeText(this, "Institute image is clicked", Toast.LENGTH_SHORT).show();
    }
    public void info(View view){
        Intent intent = new Intent(this,InstituteProformaActivity.class);
        startActivity(intent);
        // Toast.makeText(this, "Info was clicked", Toast.LENGTH_SHORT).show();
    }

    public void ratings(View view){
        // Intent intent = new Intent(this,Ratings.class);
        //  startActivity(intent);
        // Toast.makeText(this, "Ratings was clicked", Toast.LENGTH_SHORT).show();
    }

    public void branches(View view){
        Intent intent = new Intent(this,InstituteBranchesActivity.class);
        startActivity(intent);
        // Toast.makeText(this, "Branches was clicked", Toast.LENGTH_SHORT).show();
    }
    TextView dummyText;
    SmileRating smileRating;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_ratings);

        //getting intent from the previous activity:
        Intent intent = getIntent();
        instituteName = intent.getStringExtra("Institute Name");
        instituteType = intent.getStringExtra("Institute Type");

        //init view for the edittext for review :
        reviewByGuestEdittext = InstituteRatingsActivity.this.findViewById(R.id.review_by_guest_edittext);

        //dummyText = findViewById(R.id.text_dummy_text);
        AlertDialog.Builder rating = new AlertDialog.Builder(this);
        // View v = getLayoutInflater().inflate(R.layout.activity_ratings,null);

        //rating.setView()
        //mistake, since it is an inner class, we have to write v.findView.Id, that is why the app was
        //crashing on clicking the show buttoon on main activity, based on null poiter exception        smileRating = v.findViewById(R.id.smile_rating);
        Button doneButton = (Button)findViewById(R.id.rate_button);
        Button cancelButton = (Button)findViewById(R.id.cancel_button);
        smileRating = (SmileRating)findViewById(R.id.smile_rating);
        alertDialog = rating.create();
        alertDialog.setCanceledOnTouchOutside(false);

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.

                switch (smiley) {
                    case SmileRating.BAD:

                        Toast.makeText(InstituteRatingsActivity.this ,"bad" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:

                        Toast.makeText(InstituteRatingsActivity.this ,"good" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:

                        Toast.makeText(InstituteRatingsActivity.this ,"great" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:

                        Toast.makeText(InstituteRatingsActivity.this ,"okay" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:

                        Toast.makeText(InstituteRatingsActivity.this ,"terrible", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String message = Integer.toString(smileRating.getRating());
                //Toast.makeText(Ratings.this, message, Toast.LENGTH_SHORT).show();
                //dummyText.setText(message);
                LinearLayout rating = (LinearLayout)findViewById(R.id.rating);
                rating.setVisibility(View.GONE);


                ratingByGuest = smileRating.getRating();
                reviewByGuest = reviewByGuestEdittext.getText().toString();
                DatabaseReference mRef
                        = FirebaseDatabase.getInstance()
                        .getReference("users").child("guest users")
                        .child(instituteType).child(instituteName);


                //setting values using push id:
                String key = mRef.push().getKey();
                Guest guest = new Guest(ratingByGuest,reviewByGuest);

                mRef.child(key).setValue(guest);

                Toast.makeText(InstituteRatingsActivity.this,
                            "You have syccessfully rated this institute",
                                Toast.LENGTH_SHORT).show();

                //alertDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout rating = (LinearLayout)findViewById(R.id.rating);
                rating.setVisibility(View.GONE);
                //String message = Integer.toString(smileRating.getRating());
                //dummyText.setText(message);
                //alertDialog.dismiss();
            }
        });

        //smiley rating:
        //alertDialog.show();
    }
   /* public void showDialog(View view) {

        final AlertDialog.Builder rating = new AlertDialog.Builder(Ratings.this);
        View v = getLayoutInflater().inflate(R.layout.activity_ratings,null);


        //mistake, since it is an inner class, we have to write v.findView.Id, that is why the app was
        //crashing on clicking the show buttoon on main activity, based on null poiter exception        smileRating = v.findViewById(R.id.smile_rating);
        Button doneButton = v.findViewById(R.id.rate_button);
        Button cancelButton = v.findViewById(R.id.cancel_button);
        smileRating = v.findViewById(R.id.smile_rating);
        rating.setView(v);
        alertDialog = rating.create();
        alertDialog.setCanceledOnTouchOutside(false);



        //smiley rating:
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.

                switch (smiley) {
                    case SmileRating.BAD:

                        Toast.makeText(Ratings.this ,"bad" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:

                        Toast.makeText(Ratings.this ,"good" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:

                        Toast.makeText(Ratings.this ,"great" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:

                        Toast.makeText(Ratings.this ,"okay" , Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.TERRIBLE:

                        Toast.makeText(Ratings.this ,"terrible", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = Integer.toString(smileRating.getRating());
                dummyText.setText(message);
                alertDialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = Integer.toString(smileRating.getRating());
                dummyText.setText(message);
                alertDialog.dismiss();
            }
        });



        alertDialog.show();

    }*/
}

