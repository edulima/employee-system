package database;

/**
 * Created by eduardol on 15/10/2015.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DBConnection {

    private Connection conn;
    private String dbUsername;
    private String dbPassword;
    private Statement stmt;

    public DBConnection() {
        connect();
    }

    public Connection connect () {

        //DataBase Credentials
        dbUsername = "root";
        dbPassword = "";

        String url = "jdbc:mysql://localhost/?user="+dbUsername+"&password="+dbPassword;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url);

            stmt = conn.createStatement();
            stmt.executeUpdate("USE EmployeeDB");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS EmployeeDB");

        }catch(SQLException se) {
            se.printStackTrace();
            System.err.print("could not connect" + se.getMessage());
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
