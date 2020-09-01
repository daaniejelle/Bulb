package com.example.restservice.Model;

public class CheckLight {

    private boolean isLit;
    private String location;

    public CheckLight(boolean isLit, String location) {
        this.isLit = isLit;
        this.location = location;
    }

    public boolean isLit() {
        return isLit;
    }

    public void setLit(boolean lit) {
        isLit = lit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public CheckLight(boolean light) {
        isLit = light;
    }

    public void turnOn() {
        isLit = true;
    }

    public void turnOff() {
        isLit = false;
    }

    public String getLight() {
        if (isLit) {
            return "On";

        } else {
            return "Off";
        }
    }
}

