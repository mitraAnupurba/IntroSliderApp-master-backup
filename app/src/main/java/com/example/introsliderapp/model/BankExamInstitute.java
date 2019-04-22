package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

public class BankExamInstitute extends Institute implements Comparator<BankExamInstitute> {



    public BankExamInstitute() {
    }


    public BankExamInstitute(String instituteName, String instituteAddress, String emailAddress, String phoneNumber, String instituteType,float avg) {
        super(instituteName, instituteAddress, emailAddress, phoneNumber, instituteType,avg);
    }


    @Override
    public String toString() {
        return instituteName;
    }

    @Override
    public int compare(BankExamInstitute o1, BankExamInstitute o2) {
        if(o1.getAvg() > o2.getAvg()){
            return -1;
        }
        else{
            return 1;
        }
    }
}
