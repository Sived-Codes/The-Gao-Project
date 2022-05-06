package com.prashant.thegao.PublicUser;

public class ReadWriteUserDetails {
    public String  A_FullName,B_Email,C_Mobile,D_Password,E_Village;
    public ReadWriteUserDetails(){};
    public ReadWriteUserDetails(String textFullname, String textEmail, String textPhone, String textPwd, String textSpinner){
        this.A_FullName=textFullname;
        this.B_Email=textEmail;
        this.C_Mobile=textPhone;
        this.D_Password=textPwd;
        this.E_Village=textSpinner;
    }
}
