package com.example.introsliderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.introsliderapp.model.BankExamInstitute;

import java.util.Collections;

import static com.example.introsliderapp.MainActivity.bankExam;

public class BankExamInstituteView extends AppCompatActivity {

    private Toolbar toolBar;
    ListView listViewBank;
    private final String TAG = "My tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_exam_institute_view);

        toolBar = this.findViewById(R.id.bank_toolbar);
        listViewBank = this.findViewById(R.id.bank_listView);


        toolBar.setTitle("BANK EXAM COACHING INSTITUTE'S : ");
        toolBar.setSubtitle("WELCOME");
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setSubtitleTextColor(Color.WHITE);

        Log.d(TAG,"before Sorting");
        Log.d(TAG,bankExam.toString());
        BankExamInstitute institute = new BankExamInstitute();
        Collections.sort(bankExam,institute);
        Log.d(TAG,"after Sorting");
        Log.d(TAG,bankExam.toString());

        ArrayAdapter<BankExamInstitute> bankAdapter =
                new ArrayAdapter<>(this,R.layout.user_info_layout,
                                    R.id.tv,bankExam);
        listViewBank.setAdapter(bankAdapter);
        listViewBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BankExamInstitute institute =
                        (BankExamInstitute) listViewBank.getItemAtPosition(position);
                String name = institute.toString();
                String type = institute.getInstituteType();

                Intent intent = new Intent(BankExamInstituteView.this,InstituteProformaActivity.class);
                intent.putExtra("Institute Name",name);
                intent.putExtra("Institute Type",type);

                startActivity(intent);
            }
        });
    }
}
