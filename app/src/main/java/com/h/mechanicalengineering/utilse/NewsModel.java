package com.h.mechanicalengineering.utilse;

public class NewsModel {
    private int id;
    private String title;
    private String subtitle;
    private String image;
    private String video;
    private String description;


    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NewsModel(int id, String title, String subtitle, String image , String description, String video) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.image = image;
        this.video = video;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}