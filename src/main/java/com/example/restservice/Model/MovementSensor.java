package com.example.restservice.Model;

public class MovementSensor extends Tool {

    private String movement;

    public MovementSensor(int id, String location, int xPosition, int yPosition) {
        super(id, location, xPosition, yPosition);
        this.movement = movement;
    }

    public String getMovement() {
        return movement;
    }

    public void setMovement(String movement) {
        this.movement = movement;
    }

}
