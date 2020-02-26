package com.goit.jdbc;

import com.goit.jdbc.config.DatabaseManagerConnector;
import com.goit.jdbc.dao.LocationDAO;
import com.goit.jdbc.model.Location;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseManagerConnector db = new DatabaseManagerConnector("localhost", 5547,
                "go_it", "postgres", "Sam@64hd!+4");
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery("SELECT * from employees");

        Location location = new Location();
        location.setId(450);
        location.setStreetAddress("Mechnikova");
        location.setPostalCode("01111");
        location.setCity("Kyiv");
        location.setStateProvince("Kyiv region");

        LocationDAO dao = new LocationDAO(connection);
        dao.create(location);
    }
}
