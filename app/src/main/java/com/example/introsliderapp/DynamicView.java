package com.example.introsliderapp;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicView {

    Context context;

    public DynamicView(Context context){
        this.context = context;
    }

    public TextView addTextView(Context context, String text){

        ViewGroup.LayoutParams layoutParams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);
        TextView textView = new TextView(context);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(24);
        textView.setText(" "+text+" ");
        textView.setMaxEms(8);
        return textView;
    }


}
