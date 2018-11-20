package com.example.vladimir.bottomnav;

public class App {

    private String name;
    private int id;
    private String context;

    public void setName(String name) {
        this.name = name;
    }

    private int imgID;

    public App(int imgID, String name, int id, String context) {
        this.name = name;
        this.imgID = imgID;
        this.id = id;
        this.context = context;
    }

    public int getImgID() {
        return imgID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }
}
