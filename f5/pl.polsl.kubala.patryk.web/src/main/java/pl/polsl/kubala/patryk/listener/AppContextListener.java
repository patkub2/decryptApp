/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.kubala.patryk.listener;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import pl.polsl.kubala.patryk.db.DatabaseConnction;

/**
 * Class that runs after app start creates database table and gets data for loggin and db url from web.xml
 *
 * @author Patryk Kubala
 * @version 5.0
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    /**
     * overrides method for initializing context gets username password and url and passes it to databaseconnection method 
     * @param sce ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       ServletContext context = sce.getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        String dburl = context.getInitParameter("dburl");

        try {
            DatabaseConnction dbManager = new DatabaseConnction(username, password, dburl);
            Connection connection = dbManager.getConnection();
            Statement statement = connection.createStatement();
            // make a connection to DBs
            if (tableExists(connection, "Data")) {
                statement.executeUpdate("CREATE TABLE Data "
                        + "(id INTEGER, text VARCHAR(50),decodedtext VARCHAR(50), "
                        + "operation VARCHAR(50), seed INTEGER )");
                System.out.println("Table created");
            } else {
                System.out.println("Table exists");
            }
            context.setAttribute("dbconnection", connection);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AppContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * overrides method for initializing context gets username password and url and passes it to databaseconnection method 
     * @param connection passes database connection to check if table exists
     * @param tableName check if table exist of that name
     * @throws SQLException
     * @return boolean, if table exists return false
     */
    public boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }
}
