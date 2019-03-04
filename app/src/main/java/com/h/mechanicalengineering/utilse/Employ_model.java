package com.h.mechanicalengineering.utilse;

import android.widget.ImageView;

public class Employ_model {

    private ImageView img;
    private String EmpTitle;
    private String Empdate;
    private String EmpCity;
    private String EmpSubtitle;

    public Employ_model(ImageView img, String empTitle, String empdate, String empCity, String empSubtitle) {
        this.img = img;
        EmpTitle = empTitle;
        Empdate = empdate;
        EmpCity = empCity;
        EmpSubtitle = empSubtitle;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getEmpTitle() {
        return EmpTitle;
    }

    public void setEmpTitle(String empTitle) {
        EmpTitle = empTitle;
    }

    public String getEmpdate() {
        return Empdate;
    }

    public void setEmpdate(String empdate) {
        Empdate = empdate;
    }

    public String getEmpCity() {
        return EmpCity;
    }

    public void setEmpCity(String empCity) {
        EmpCity = empCity;
    }

    public String getEmpSubtitle() {
        return EmpSubtitle;
    }

    public void setEmpSubtitle(String empSubtitle) {
        EmpSubtitle = empSubtitle;
    }
}
