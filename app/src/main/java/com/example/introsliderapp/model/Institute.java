package com.example.introsliderapp.model;

import java.util.ArrayList;
import java.util.Comparator;

//model class for the institute object passed to the firebase database:

public class Institute {

    String instituteName, instituteAddress, emailAddress,phoneNumber,instituteType;
    ArrayList<Float> ratingArray=  new ArrayList<>();
    float avg ;
    int studentIn;
    int studentOut;
    float percentage;

    public Institute(){

    }
    public Institute(String instituteName,String instituteAddress,
                     String emailAddress,String phoneNumber,String instituteType,
                     float avg,int studentIn,int studentOut,float percentage){

        this.instituteName = instituteName;
        this.instituteAddress =instituteAddress;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.instituteType = instituteType;
        this.avg = avg;
        this.studentIn = studentIn;
        this.studentOut = studentOut;
        this.percentage = percentage;
    }

    public void setRatingArray(float i) {
       ratingArray.add(i);
        calculateAverage();
    }

    public void calculateAverage(){

        float sum = 0.0f;
        for(int k=0;k<ratingArray.size();k++){
            sum = sum+ratingArray.get(k);
        }
        avg = sum/ratingArray.size();
    }
    public float getAvg(){
        return avg;
    }

    @Override
    public String toString() {
        return instituteType;

    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public void setInstituteAddress(String instituteAddress) {
        this.instituteAddress = instituteAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }


    public String getInstituteName() {
        return instituteName;
    }

    public String getInstituteAddress() {
        return instituteAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }


    public int getStudentIn() {
        return studentIn;
    }

    public void setStudentIn(int studentIn) {
        this.studentIn = studentIn;
    }

    public int getStudentOut() {
        return studentOut;
    }

    public void setStudentOut(int studentOut) {
        this.studentOut = studentOut;
    }
}
