package com.witi.factory.implementation;


import com.witi.factory.pattern.ConnectionFactory;
import lombok.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostgresConnectionFactory implements ConnectionFactory {

    private String hostname;
    private String database;
    private String user;
    private String pass;

    @Override
    public Connection getConnection() throws SQLException {
        Connection connection;
        String url = "jdbc:postgresql://" + hostname + "/" + database;
        connection = DriverManager.getConnection(url, user, pass);
        return connection;
    }

}
