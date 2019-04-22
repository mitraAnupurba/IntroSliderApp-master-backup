package com.example.introsliderapp.model;

public class Student {

    private float rating;
    private String review;

    String userNameStudent,emailAddressStudent,
            phoneNumberStudent,instituteNameStudent,examName,dob,
            coachingInstituteName;

    public Student(String userNameStudent, String emailAddressStudent, String phoneNumberStudent, String instituteNameStudent,String examName,String dob,String coachingInstituteName) {
        this.userNameStudent = userNameStudent;
        this.emailAddressStudent = emailAddressStudent;
        this.phoneNumberStudent = phoneNumberStudent;
        this.instituteNameStudent = instituteNameStudent;
        this.examName = examName;
        this.dob = dob;
        this.coachingInstituteName = coachingInstituteName;
    }


    public Student() {
    }

    public String getUserNameStudent() {
        return userNameStudent;
    }

    public String getEmailAddressStudent() {
        return emailAddressStudent;
    }

    public String getPhoneNumberStudent() {
        return phoneNumberStudent;
    }

    public String getInstituteNameStudent() {
        return instituteNameStudent;
    }

    public String getExamName() {
        return examName;
    }

    public String getDobStudent() {
        return dob;
    }

    public String getCoachingInstituteName() {
        return coachingInstituteName;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }

    @Override
    public String toString() {
        return userNameStudent.toUpperCase();
    }
}
