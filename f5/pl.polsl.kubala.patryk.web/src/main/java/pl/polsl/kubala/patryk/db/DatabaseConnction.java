/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.kubala.patryk.db;

import java.sql.*;

/**
 * Connecting to the database 
 *
 * @author Patryk Kubala
 * @version 5.0
 */
public class DatabaseConnction {

    /**
     * Connection data after establishing connection. connects only one time on app start
     */
    private Connection connection;

    /**
     *
     * @param username - user name
     * @param password - user password
     * @param dburl - database url
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DatabaseConnction(String username, String password, String dburl) throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.derby.jdbc.ClientDriver");

        this.connection = DriverManager.getConnection(dburl, username, password);
    }

    /**
     * Method for connection to the database
     * @return connection data for further use
     */
    public Connection getConnection() {
        return this.connection;
    }
}
