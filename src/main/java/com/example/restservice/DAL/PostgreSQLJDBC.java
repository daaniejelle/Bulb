package com.example.restservice.DAL;

import com.example.restservice.Model.Bulb;

import java.sql.*;
import java.util.ArrayList;

public class PostgreSQLJDBC {
    private static final String url = "jdbc:postgresql://localhost:5432/Bulbs";
    private static final String user = "postgres";
    private static final String password = "banaan007";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    // Set the status and intensity for given bulbId
    public void updateBulb(int id, boolean on, int intensity) {
        try {
            Connection conn = connect();
            String SQL = "UPDATE lightbulbs SET \"on\"=?, intensity=? WHERE id = ?;";
            PreparedStatement preparedStmt = conn.prepareStatement(SQL);

            // inject the parameters into the SQL statement
            preparedStmt.setInt(3, id);
            preparedStmt.setBoolean(1, on);
            preparedStmt.setInt(2, intensity);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException ex) {
            return;
        }
    }

    // Get specific bulb from the database
    public Bulb getBulb(int id) {
        try {
            Connection conn = connect();
            String SQL = "SELECT * FROM lightbulbs where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(SQL);

            // inject the parameters into the SQL statement
            preparedStmt.setInt(1, id);

            ResultSet rs = preparedStmt.executeQuery();
            ArrayList<Bulb> bulbs = addBulbs(rs);
            if (!bulbs.isEmpty()) {
                return bulbs.get(0);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // When there was an error or the list was empty, return null
        return null;
    }

    // Get all bulbs from the database
    public ArrayList<Bulb> getBulbs() {
        String SQL = "SELECT * FROM lightbulbs";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            return addBulbs(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        // When there was an error, return an empty list
        return new ArrayList<Bulb>();
    }

    // create an ArrayList of bulbs from the resultset
    private ArrayList<Bulb> addBulbs(ResultSet rs) throws SQLException {
        ArrayList<Bulb> listOfBulbs = new ArrayList<Bulb>();

        while (rs.next()) {
            int id = rs.getInt("id");
            boolean on = rs.getBoolean("on");
            String cn = rs.getString("cn");
            String location = rs.getString("location");
            int xPosition = rs.getInt("xPosition");
            int yPosition = rs.getInt("yPosition");
            boolean isDimmable = rs.getBoolean("isDimmable");
            int intensity = rs.getInt("intensity");
            double timestamp = rs.getDouble("timestamp");

            Bulb bulb = new Bulb(id, on, cn, location, xPosition, yPosition, isDimmable, intensity, timestamp);
            listOfBulbs.add(bulb);
        }

        return listOfBulbs;
    }

    public void addBulb(int id, String location, int xPosition, int yPosition, boolean isDimmable) {
        try {
            Connection conn = connect();
            String SQL = "INSERT INTO lightbulbs(id, location,xPosition, xPosition, isDimmable ) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(SQL);

            // inject the parameters into the SQL statement
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, location);
            preparedStmt.setInt(3, xPosition);
            preparedStmt.setInt(3, yPosition);
            preparedStmt.setBoolean(3, isDimmable);


            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (SQLException ex) {
            return;
        }}

        // Set the status and intensity for given bulbId
        public void updateBulbTime(int id, int timestamp) {
            try {
                Connection conn = connect();
                String SQL = "UPDATE lightbulbs SET \"on\"=?, TIMESTAMP=? WHERE id = ?;";
                PreparedStatement preparedStmt = conn.prepareStatement(SQL);

                // inject the parameters into the SQL statement
                preparedStmt.setInt(3, id);
                preparedStmt.setInt(2, timestamp);

                // execute the preparedstatement
                preparedStmt.execute();

                conn.close();
            } catch (SQLException ex) {
                return;
            }
        }
    }



