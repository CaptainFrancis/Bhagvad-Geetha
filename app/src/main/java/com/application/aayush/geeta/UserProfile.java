package com.application.aayush.geeta;

/**
 * Created by Aayush on 8/13/2017.
 */

public class UserProfile {
    String name,mobile_no,email_id,address,city;
    int user_id;
    public UserProfile(){

    }

    public UserProfile(int user_id,String name, String mobile_no, String email_id, String address, String city) {
        this.user_id = user_id;
        this.name = name;
        this.mobile_no = mobile_no;
        this.email_id = email_id;
        this.address = address;
        this.city = city;
    }

    public UserProfile(String name, String mobile_no, String email_id, String address, String city) {
        this.name = name;
        this.mobile_no = mobile_no;
        this.email_id = email_id;
        this.address = address;
        this.city = city;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}