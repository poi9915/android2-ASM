package com.example.asm2.Model;

public class User {
    private String username;
    private String pass;
    private String fullName;

    public User() {
    }

    public User(String username, String pass, String fullName) {
        this.username = username;
        this.pass = pass;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
