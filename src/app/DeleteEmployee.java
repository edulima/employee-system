package app;

import database.DBConnection;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eduardol on 17/10/2015.
 */
public class DeleteEmployee extends Employee {

    private DBConnection createConnection;
    private Connection connection;
    private PreparedStatement stmt;

    public DeleteEmployee () {

        createConnection = new DBConnection();
        connection = createConnection.connect();
    }

    public DeleteEmployee(String name, String address, double salary, String tableName, int id) {
        super(name, address, salary);
        createConnection = new DBConnection();
        connection = createConnection.connect();

    }

    public void deleteEmployee(String tableName, int id) {
      try {
           stmt = connection.prepareStatement("DELETE * FROM Employee WHERE id = ?");
           stmt.setInt(1, id);
           stmt.executeUpdate();
           System.out.println("Employee deleted...");
      } catch (SQLException e) {
            e.printStackTrace();
      }

    }

    public void getListOfEmployeeByNames(String name, String tableName) {
        try {
            stmt = connection.prepareStatement("SELECT id, Name FROM " + tableName + " WHERE Name =?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("-----------------------------------------");
                System.out.println("Employee number: " + rs.getInt(1) + " Employee Name: " + rs.getString(2));
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
