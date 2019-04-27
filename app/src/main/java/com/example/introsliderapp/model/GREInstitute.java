package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class GREInstitute extends Institute implements Comparator<GREInstitute> {


    public GREInstitute() {
    }

    public GREInstitute(String instituteName, String instituteAddress, String emailAddress, String phoneNumber,
                        String instituteType,float avg,int studentIn,int studentOut,float percentage) {
        super(instituteName, instituteAddress, emailAddress, phoneNumber,
                instituteType,avg,studentIn,studentOut,percentage);
    }



    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(GREInstitute o1, GREInstitute o2) {
        if(o1.getAvg() > o2.getAvg()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
