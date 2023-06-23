package com.example.myapplication.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;

public class User {
    private String nickname;
    private String name;
    private String last_name;
    private String email;
    private String password;
    private String role;
    private String telephone;
    private Date birthday;
    private int avatar;
    private ArrayList<EventObject> eventArray;

    public ArrayList<EventObject> getEventArray() {
        return eventArray;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public void setEventArray(ArrayList<EventObject> eventArray) {
        this.eventArray = eventArray;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
