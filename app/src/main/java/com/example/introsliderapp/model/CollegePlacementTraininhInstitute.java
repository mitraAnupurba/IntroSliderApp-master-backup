package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class CollegePlacementTraininhInstitute extends Institute implements Comparator<CollegePlacementTraininhInstitute> {


    public CollegePlacementTraininhInstitute() {
    }

    public CollegePlacementTraininhInstitute(String instituteName, String instituteAddress, String emailAddress, String phoneNumber, String instituteType,float avg) {
        super(instituteName, instituteAddress, emailAddress, phoneNumber, instituteType,avg);
    }


    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(CollegePlacementTraininhInstitute o1, CollegePlacementTraininhInstitute o2) {
        if(o1.getAvg() > o2.getAvg()){
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
