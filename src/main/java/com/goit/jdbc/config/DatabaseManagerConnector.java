package com.goit.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManagerConnector {
    private final String url;
    private Properties properties;

    public DatabaseManagerConnector(String hostname, int port, String database, String username, String password) {
        url = String.format("jdbc:postgresql://%s:%d/%s", hostname, port, database);
        properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, properties);
    };
}
