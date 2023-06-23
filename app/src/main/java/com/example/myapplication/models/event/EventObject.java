package com.example.myapplication.models.event;

import java.util.ArrayList;
import java.util.Locale;

public class EventObject {
    private String name;
    private String startDate;
    private String startTime;
    private String status;
    private String discription;
    private ArrayList<СategoryObject> categoriesArray;

    public EventObject(String name, String startDate, String startTime, String status, String discription, ArrayList<СategoryObject> categoriesArray) {
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.status = status;
        this.discription = discription;
        this.categoriesArray = categoriesArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public ArrayList<СategoryObject> getCategoriesArray() {
        return categoriesArray;
    }

    public void setCategoriesArray(ArrayList<СategoryObject> categoriesArray) {
        this.categoriesArray = categoriesArray;
    }
}
