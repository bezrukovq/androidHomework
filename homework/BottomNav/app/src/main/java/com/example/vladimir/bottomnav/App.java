package com.example.vladimir.bottomnav;

public class App {

    private String name;
    private int rate;
    private String context;
    private int imgID;

    public App(int imgID, String name, int rate, String context) {
        this.name = name;
        this.imgID = imgID;
        this.rate = rate;
        this.context = context;
    }

    public int getImgID() {
        return imgID;
    }

    public int getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }

    public String getContext() {
        return context;
    }
}
