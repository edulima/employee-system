package database;

import app.Employee;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by eduardol on 17/10/2015.
 */
public class AddNewEmployee extends Employee {

    private DBConnection createConnection;
    private Connection connection;
    private PreparedStatement stmt;

    public AddNewEmployee(String name, String address, double salary, String tableName) {
      super(name, address, salary);
      createConnection = new DBConnection();
      connection = createConnection.connect();
      insertNewEmployee(connection, tableName);
    }

    private void insertNewEmployee(Connection connection, String tableName) {

      try {
          stmt = connection.prepareStatement("INSERT INTO " + tableName + " (Name, Address, Salary) VALUES (?,?,?)");
          stmt.setString(1, super.getName());
          stmt.setString(2, super.getAddress());
          stmt.setDouble(3, super.getSalary());
          stmt.executeUpdate();

      } catch (SQLException e) {
          System.out.println("Error adding employee:" + e.getMessage());
      }
  }

}
