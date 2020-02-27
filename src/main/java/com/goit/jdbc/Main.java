package com.goit.jdbc;

import com.goit.jdbc.config.DatabaseManagerConnector;
import com.goit.jdbc.dao.LocationDAO;
import com.goit.jdbc.model.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseManagerConnector db = new DatabaseManagerConnector("localhost", 5547,
                "go_it", "postgres", "Sam@64hd!+4");
        Connection connection = db.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from employees");

        LocationDAO dao = new LocationDAO(connection);
        Location location = new Location();
        location.setId(450);
        location.setStreetAddress("Mechnikova");
        location.setPostalCode("01111");
        location.setCity("Kyiv");
        location.setStateProvince("Kyiv region");

//          add record to table
        dao.create(location);

        // get record from table
        Location extractLocation = dao.getById(450);
        System.out.println(extractLocation.toString());

        //delete
        dao.remove(450);

        List<Location> list = dao.getAll();

        for (Location l: list) {
            System.out.println(l.toString());
        }


        //update

        dao.create(location);
        location.setStateProvince("Kyiv");
        dao.update(location);
        list = dao.getAll();
        for (Location l: list) {
            System.out.println(l.toString());
        }
    }
}
