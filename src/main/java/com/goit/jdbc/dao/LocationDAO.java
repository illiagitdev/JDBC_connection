package com.goit.jdbc.dao;

import com.goit.jdbc.config.DataAccessObject;
import com.goit.jdbc.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LocationDAO extends DataAccessObject<Location> {
    private Connection connection;
    private String INSERT = "INSERT into locations(location_id, street_address, postal_code, city, state_province)" +
            " VALUES(?, ?, ?, ?, ?);";
    private String UPDATE = "UPDATE locations SET street_address = ?, postal_code = ?, city = ?, state_province = ? " +
            "WHERE location_id = ?";
    private String GET_BY_ID = "SELECT * FROM locations where location_id = ?;";
    private String GET_ALL = "SELECT * FROM locations;";
    private String DELETE = "DELETE FROM locations where location_id = ?;";

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Location location) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, location.getId());
            statement.setString(2, location.getStreetAddress());
            statement.setString(3, location.getPostalCode());
            statement.setString(4, location.getCity());
            statement.setString(5, location.getStateProvince());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Location location) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)){
            statement.setString(1, location.getStreetAddress());
            statement.setString(2, location.getPostalCode());
            statement.setString(3, location.getCity());
            statement.setString(4, location.getStateProvince());
            statement.setInt(5, location.getId());
            int n = statement.executeUpdate();
            System.out.println("Affected rows = " + n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Location getById(int id) {
        Location location = new Location();
        try (PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                location.setId(resultSet.getInt(1));
                location.setStreetAddress(resultSet.getString(2));
                location.setPostalCode(resultSet.getString(3));
                location.setCity(resultSet.getString(4));
                location.setStateProvince(resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            Location location;
            while (resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt(1));
                location.setStreetAddress(resultSet.getString(2));
                location.setPostalCode(resultSet.getString(3));
                location.setCity(resultSet.getString(4));
                location.setStateProvince(resultSet.getString(5));
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)){
            statement.setInt(1, id);
            int n = statement.executeUpdate();
            System.out.println("Affected rows = " + n);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
