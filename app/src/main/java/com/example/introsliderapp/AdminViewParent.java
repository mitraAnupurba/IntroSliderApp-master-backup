package com.example.introsliderapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.introsliderapp.model.Parent;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminViewParent extends AppCompatActivity {

    private ListView listViewAdminParent;
    private static final String TAG = "my tag";
    ArrayList<Parent> parentList = new ArrayList<>();
    ArrayList<String> parentKey = new ArrayList<>();
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_parent);

        mRef = FirebaseDatabase.getInstance().getReference("users").child("parent");
        listViewAdminParent = this.findViewById(R.id.admin_parent_list_view);

        final ArrayAdapter<Parent> parentArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,parentList);
        listViewAdminParent.setAdapter(parentArrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Parent parent = dataSnapshot.getValue(Parent.class);
                parentList.add(parent);
                parentKey.add(dataSnapshot.getKey());
                parentArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Parent parent = dataSnapshot.getValue(Parent.class);
                String key = dataSnapshot.getKey();
                Log.d(TAG,key);
                int index = parentKey.indexOf(key);
                parentList.set(index,parent);
                parentArrayAdapter.notifyDataSetChanged();
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
