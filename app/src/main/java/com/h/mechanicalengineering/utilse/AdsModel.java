package com.h.mechanicalengineering.utilse;

public class AdsModel {
    private int id;
    private String title;
    private String subtitle;
    private String image;
    private String description;
    private String degree;
    private String date;
    private String phone;
    private String email;
    private String city;
   // private boolean verify;

    public AdsModel(int id, String title, String subtitle, String image, String description, String degree, String date, String phone, String email, String city) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.description = description;
        this.degree = degree;
        this.date = date;
        this.phone = phone;
        this.email = email;
        this.city = city;
      //  this.verify = verify;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

//    public boolean isVerify() {
//        return verify;
//    }
//
//    public void setVerify(boolean verify) {
//        this.verify = verify;
//    }
}
