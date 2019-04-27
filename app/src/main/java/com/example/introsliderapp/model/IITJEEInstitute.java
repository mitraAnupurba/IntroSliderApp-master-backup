package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class IITJEEInstitute extends Institute implements Comparator<IITJEEInstitute> {


    public IITJEEInstitute() {
    }

    public IITJEEInstitute(String instituteName, String instituteAddress, String emailAddress, String phoneNumber,
                           String instituteType,float avg,int studentIn,int studentOut,float percentage) {
        super(instituteName, instituteAddress, emailAddress, phoneNumber
                ,instituteType,avg,studentIn,studentOut,percentage);
    }


    public String display(){

        return instituteName.toUpperCase() +
               "\nAverge rating : " + getAvg();

    }



    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(IITJEEInstitute o1, IITJEEInstitute o2) {
        if(o1.getAvg() > o2.getAvg())
            return -1;
        else
            return 1;
    }
}
