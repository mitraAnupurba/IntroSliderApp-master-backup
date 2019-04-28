package com.example.introsliderapp.model;

public class Parent {

    String userProfilePicture,userNameParent,emailAddressParent,
            phoneNumberParent,instituteNameParent,examNameParent,dobParent;

    public Parent(String userProfilePicture,String userNameStudent, String emailAddressStudent, String phoneNumberStudent, String instituteNameStudent,String examName,String dob) {
        this.userProfilePicture = userProfilePicture;
        this.userNameParent = userNameStudent;
        this.emailAddressParent = emailAddressStudent;
        this.phoneNumberParent = phoneNumberStudent;
        this.instituteNameParent = instituteNameStudent;
        this.examNameParent = examName;
        this.dobParent = dob;
    }

    public Parent() {
    }
    public String getUserProfilePictureStudent(){return  userProfilePicture; }
    public String getUserNameParent() {
        return userNameParent;
    }

    public String getEmailAddressParent() {
        return emailAddressParent;
    }

    public String getPhoneNumberParent() {
        return phoneNumberParent;
    }

    public String getInstituteNameParent() {
        return instituteNameParent;
    }

    public String getExamNameParent() {
        return examNameParent;
    }

    public String getDobParent() {
        return dobParent;
    }

    @Override
    public String toString() {
        return userNameParent.toUpperCase();
    }
}
