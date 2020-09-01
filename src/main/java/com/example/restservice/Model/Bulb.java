package com.example.restservice.Model;

public class Bulb extends Tool {

    private boolean on;
    private String colorName;
    private boolean isDimmable;
    private int intensity;
    private boolean isDimmableSensor;

    public Bulb(int id, boolean on, String cn, String location, int xPosition, int yPosition, boolean isDimmable, int intensity, boolean isDimmableSensor) {
        super(id, location, xPosition, yPosition);
        this.on = on;
        colorName = cn;
        this.isDimmable = isDimmable;
        this.intensity = intensity;
        this.isDimmableSensor = isDimmableSensor;
    }

    public static int bulbIntensity(int id, int intensity) {
        return id + intensity;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isOff() {
        return !on;
    }

    public String getcolorName() {
        return colorName;
    }

    public void setColorName(String cn) {
        colorName = cn;
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public boolean isDimmable() {
        return isDimmable;
    }

    public void setDimmable(boolean dimmable) {
        isDimmable = dimmable;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public boolean isDimmableSensor() {
        return isDimmableSensor;
    }

    public void setDimmableSensor(boolean dimmableSensor) {
        isDimmableSensor = dimmableSensor;
    }

    public String toString() {
        String result;
        if (on) {
            result = "The " + getcolorName() + " light is on.";
        } else {
            result = "The " + getcolorName() + " light is off.";
        }

        return result;

    }
}
