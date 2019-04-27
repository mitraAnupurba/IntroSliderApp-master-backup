package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class ManagementExamInstitute extends Institute
                            implements Comparator<ManagementExamInstitute> {


    public ManagementExamInstitute() {
    }

    public ManagementExamInstitute(String instituteName, String instituteAddress, String emailAddress, String phoneNumber
            , String instituteType,float avg,int studentIn,int studentOut,float percentage) {
        super(instituteName, instituteAddress, emailAddress, phoneNumber,
                instituteType,avg,studentIn,studentOut,percentage);
    }


    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(ManagementExamInstitute o1, ManagementExamInstitute o2) {
        if(o1.getAvg() > o2.getAvg()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
