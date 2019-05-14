package com.book.domain;

import java.io.Serializable;

public class Publish implements Serializable {
    private int publishId;
    private String publishName;
    private String phone;
    private String contacter;
    private String email;
    private String address;

    public void setPublishId(int publishId){
        this.publishId = publishId;
    }

    public void setPublishName(String publishName){
        this.publishName = publishName;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setContacter(String contacter){
        this.contacter = contacter;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getPublishId() {
        return publishId;
    }

    public String getPublishName() {
        return publishName;
    }

    public String getContacter() {
        return contacter;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
