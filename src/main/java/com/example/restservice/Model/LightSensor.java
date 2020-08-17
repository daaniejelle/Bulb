package com.example.restservice.Model;

import java.time.LocalTime;

public class LightSensor extends Bulb {
    int hours;
    int minutes;

    public LightSensor(int id, boolean on, String cn, String location, int xPosition, int yPosition, boolean isDimmable, int intensity, int hours, int minutes) {
        super(id, on, cn, location, xPosition, yPosition, isDimmable, intensity);
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


    public int timeOfTheDay(int id, int hours, int minutes, int intensity) {

        if (LocalTime.of(hours, minutes).equals(LocalTime.of(6, 00)))
            return bulbIntensity(9, 20);
        if (LocalTime.of(hours, minutes).isAfter(LocalTime.of(8, 00)))
            return bulbIntensity(9, 0);
        if (LocalTime.of(hours, minutes).equals(LocalTime.of(19, 30)))
            return bulbIntensity(9, 40);
        if (LocalTime.of(hours, minutes).equals(LocalTime.of(21, 00)))
            return bulbIntensity(9, 80);
        if (LocalTime.of(hours, minutes).equals(LocalTime.of(22, 00)))
            return bulbIntensity(9, 100);

        else {
            return bulbIntensity(7, 0);
        }
    }
}