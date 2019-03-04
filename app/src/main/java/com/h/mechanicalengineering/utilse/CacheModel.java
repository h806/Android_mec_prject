package com.h.mechanicalengineering.utilse;

public class CacheModel {

        private String name;
        private String imageurl;

        public CacheModel()
        {

        }

        public CacheModel(String name, String imageurl)
        {
            this.name = name;
            this.imageurl = imageurl;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}


