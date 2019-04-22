package com.example.introsliderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*public class InstituteBranchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_branches);
    }
}*/




import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstituteBranchesActivity extends AppCompatActivity {
    CircleImageView insitiuteImage;
    boolean isImageFitToScreen;
    TextView about;
    public void fullImage(View view){
        insitiuteImage = (CircleImageView) findViewById(R.id.instituteimage);
        Toast.makeText(this, "Institute image is clicked", Toast.LENGTH_SHORT).show();
    }
    public void info(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        // Toast.makeText(this, "Info was clicked", Toast.LENGTH_SHORT).show();
    }

    public void ratings(View view){
        Intent intent = new Intent(this,InstituteRatingsActivity.class);
        startActivity(intent);
        // Toast.makeText(this, "Ratings was clicked", Toast.LENGTH_SHORT).show();
    }

    public void branches(View view){
        //Intent intent = new Intent(this,Branches.class);
        //startActivity(intent);
        //Toast.makeText(this, "Branches was clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_institute_branches);
        final ListView branches = (ListView)findViewById(R.id.institute_profile_branch);
        final ArrayList<String> branch = new ArrayList<>();
        //add all the addresses from firebase down here i do not know firebase so halp ploxxxxxxxxx
        branch.add("Salt Lake Electronics Complex, Gurukul, Y-12 Block EP, Street Number 18, EP Block, Sector V, Bidhannagar, Kolkata, West Bengal 700091");
        branch.add("7A/2 G Road, Anandapuri Barrackpore");
        branch.add("34,, Park Rd, Cantonment, Kolkata, West Bengal 700120");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,branch);
        branches.setAdapter(arrayAdapter);
        branches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String address = "http://maps.google.com/maps?daddr="+branch.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
                startActivity(intent);
                //Toast.makeText(Branches.this, address, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

