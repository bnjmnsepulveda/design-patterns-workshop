package com.witi.factory.implementation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
@ToString
public class PostgresConnectionFactory {

    private String hostname;
    private String database;
    private String user;
    private String pass;
    private int port;

    public Connection getConnection() throws SQLException {
        Connection connection;
        String url = "jdbc:postgresql://" + hostname + "/" + database;
        connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

}
