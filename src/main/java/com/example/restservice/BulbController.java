package com.example.restservice;

import com.example.restservice.Model.Bulb;
import com.example.restservice.DAL.PostgreSQLJDBC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BulbController {

    @GetMapping("/bulbs")
    public ArrayList<Bulb> bulps() {
        // Get all the bulbs from the database
        PostgreSQLJDBC db = new PostgreSQLJDBC();
        ArrayList<Bulb> bulbs = db.getBulbs();
        return bulbs;
    }

    @GetMapping("/bulbIntensity")
    public Bulb bulbintensity(int bulbId, int intensity) {
        // get the bulb from the database
        PostgreSQLJDBC db = new PostgreSQLJDBC();
        Bulb bulb = db.getBulb(bulbId);

        // Save the new intensity in the database
        db.updateBulb(bulb.getId(), bulb.isOn(), intensity);
        return bulb;
    }

    @GetMapping("/Bulb")
    public Bulb bulb(int bulbId) {
        PostgreSQLJDBC db = new PostgreSQLJDBC();
        Bulb bulb = db.getBulb(bulbId);
        return bulb;
    }

    @GetMapping("/setBulb")
    public Bulb toggleBulb(int bulbId) {
        // get the bulb from the database
        PostgreSQLJDBC db = new PostgreSQLJDBC();
        Bulb bulb = db.getBulb(bulbId);
        if (bulb.isOff())
            bulb.turnOn();
        else
            bulb.turnOff();

        // Save the new toggle in the database
        db.updateBulb(bulb.getId(), bulb.isOn(), bulb.getIntensity());
        return bulb;
    }

    @GetMapping("/setBulbTime")
    public Bulb timeBulb(int bulbId, int timestamp) {
        // get the bulb from the database
        PostgreSQLJDBC db = new PostgreSQLJDBC();
        Bulb bulb = db.getBulb(bulbId);
        if (bulb.isOff())
            bulb.turnOn();
        else
            bulb.turnOff();

        // Save the new toggle in the database
        db.updateBulb(bulb.getId(), bulb.isOn(), timestamp);
        return bulb;
    }

}