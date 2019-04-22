package com.example.introsliderapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.introsliderapp.model.Student;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AdminViewStudent extends AppCompatActivity {


    private static final String TAG = "my tag";
    ListView listViewAdminStudent;

    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<String> studentKey = new ArrayList<>();
    private DatabaseReference mRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_student);

        mRef = FirebaseDatabase.getInstance().getReference("users")
                .child("student");
        listViewAdminStudent = this.findViewById(R.id.admin_student_listview);
        final ArrayAdapter<Student> studentArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,studentList);
        listViewAdminStudent.setAdapter(studentArrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Student student = dataSnapshot.getValue(Student.class);
                studentList.add(student);
                studentKey.add(dataSnapshot.getKey());
                studentArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Student student = dataSnapshot.getValue(Student.class);
                String key = dataSnapshot.getKey();
                Log.d(TAG,key);
                //find the curent index of the child that has been changed
                int index = studentKey.indexOf(key);
                studentList.set(index,student);
                studentArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
