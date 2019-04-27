package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class NeetPGInstitute extends Institute implements Comparator<NeetPGInstitute> {


    public NeetPGInstitute() {
    }

    public NeetPGInstitute(String instituteName, String instituteAddress,
                           String emailAddress, String phoneNumber,
                           String instituteType,float avg,int studentIn,
                           int studentOut,float percentage) {
        super(instituteName, instituteAddress, emailAddress,
                phoneNumber, instituteType,avg,studentIn,studentOut,percentage);
    }



    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(NeetPGInstitute o1, NeetPGInstitute o2) {
        if(o1.getAvg() > o2.getAvg()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
