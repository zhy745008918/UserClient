package com.chinadream.www.userclient.bean;


public class AddressInfoBean {
    String linkman,phoneNum,gender,address;

    public AddressInfoBean(String linkman, String phoneNum, String gender, String address) {
        this.linkman = linkman;
        this.phoneNum = phoneNum;
        this.gender = gender;
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
