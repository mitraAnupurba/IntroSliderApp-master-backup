package com.example.introsliderapp;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import static com.example.introsliderapp.MainActivity.institutes;
import static com.example.introsliderapp.MainActivity.upscNames;
import static com.example.introsliderapp.MainActivity.iitjeeNames;
import static com.example.introsliderapp.MainActivity.medicalNames;
import static com.example.introsliderapp.MainActivity.engineeringNames;
import static com.example.introsliderapp.MainActivity.neetPGNames;
import static com.example.introsliderapp.MainActivity.bankNames;
import static com.example.introsliderapp.MainActivity.comerceNames;
import static com.example.introsliderapp.MainActivity.greNames;
import static com.example.introsliderapp.MainActivity.scienceNames;
import static com.example.introsliderapp.MainActivity.placementNames;
import static com.example.introsliderapp.MainActivity.managementNames;


public class ExpandibleListViewAdapter extends BaseExpandableListAdapter {


    public static String[] groupNames = new String[institutes.size()];
    public static String[][] childNames = {bankNames,managementNames,placementNames,comerceNames,
            engineeringNames,greNames,iitjeeNames,scienceNames,
            medicalNames,neetPGNames,upscNames};
    Context context;



    public ExpandibleListViewAdapter(Context context){

        this.context = context;
        institutes.toArray(groupNames);

    }


    @Override
    public int getGroupCount() {
        return groupNames.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childNames[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return greNames[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childNames[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        TextView textView = new TextView(context);
        textView.setText(groupNames[groupPosition].toUpperCase());
        textView.setPadding(100,0,0,0);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(24);
        return textView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {

        TextView textView = new TextView(context);
        textView.setText(childNames[groupPosition][childPosition]);
        textView.setPadding(100,0,0,0);
        textView.setTextSize(24);
        return textView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}
