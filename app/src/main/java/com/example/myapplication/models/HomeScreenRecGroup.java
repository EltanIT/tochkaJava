package com.example.myapplication.models;

public class HomeScreenRecGroup {
    private String name;
    private String count_participant;
    private int image;

    public HomeScreenRecGroup(String name, String count_participant, int image) {
        this.name = name;
        this.count_participant = count_participant;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount_participant() {
        return count_participant;
    }

    public void setCount_participant(String count_participant) {
        this.count_participant = count_participant;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
