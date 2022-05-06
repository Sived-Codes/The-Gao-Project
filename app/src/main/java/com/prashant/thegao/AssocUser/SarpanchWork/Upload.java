package com.prashant.thegao.AssocUser.SarpanchWork;

public class Upload {
    private String mNotice;
    private String mImageUri;

    public Upload(){}
    public Upload (String notice,String imageuri){

        if(notice.trim().equals("")){
            notice = "Sarpanch Notice Image";
        }
        mNotice = notice;
        mImageUri = imageuri;
    }

   public String getmNotice() {
        return mNotice;
    }

    public void setmNotice(String mNotice) {
        this.mNotice = mNotice;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }
}
