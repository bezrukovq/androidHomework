package com.example.vladimir.musicplayer;

public class Track {
    private int id;
    private String name;
    private String singer;

    public Track(String name, int id, String singer) {
        this.name = name;
        this.id = id;
        this.singer = singer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
