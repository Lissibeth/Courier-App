package com.example.myapplication;

public class Firm {
    private String address;
    private String name;

    public Firm(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
