package com.goit.jdbc.dao;

import com.goit.jdbc.config.DataAccessObject;
import com.goit.jdbc.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LocationDAO extends DataAccessObject<Location> {
    private Connection connection;
    private String INSERT = "INSERT into location(location_id, street_address, postal_code, city, state_province)" +
            " VALUES(?, ?, ?, ?, ?);";

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Location location) {
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, location.getId());
            statement.setString(2, location.getStreetAddress());
            statement.setString(3,location.getPostalCode());
            statement.setString(4, location.getCity());
            statement.setString(5, location.getStateProvince());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Location object) {

    }

    @Override
    public Location getById(int id) {
        return null;
    }

    @Override
    public List<Location> getAll() {
        return null;
    }

    @Override
    public void remove(int id) {

    }
}
