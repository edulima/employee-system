package database;

import javax.security.auth.login.LoginException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

/**
 * Created by eduardol on 15/10/2015.
 */
public class CreateDatabase {

    private Connection connection;
    private PreparedStatement stmt;

    public void createDatabase(Connection connection) {
        try {
           this.connection = connection;
           createTableUsers(connection);
           createTableEmployees(connection);
           createProjects(connection);
           createSalesEmployee(connection);
           createBillableEmployee(connection);
           createProjectEmployee(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableUsers(Connection conn) throws SQLException{
        stmt = conn.prepareStatement("drop table Users");
        stmt.executeUpdate();

        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Users (" +
                "username varchar(20) DEFAULT NULL," +
                " password varchar(100) DEFAULT NULL, " +
                " PRIMARY KEY (username));");
        stmt.executeUpdate();

        stmt = conn.prepareStatement("INSERT INTO Users (username, password) VALUES (?, ?)");
        stmt.setString(1, "admin");
        stmt.setString(2, "admin");
        stmt.executeUpdate();
    }

    private void createTableEmployees(Connection conn) throws SQLException{
        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Employee (" +
                "id int(11) NOT NULL AUTO_INCREMENT," +
                "Name varchar(20) DEFAULT NULL," +
                " Address varchar(100) DEFAULT NULL, " +
                " Salary decimal(10,2) DEFAULT NULL," +
                " PRIMARY KEY (id));");
        stmt.executeUpdate();
    }

    private  void createProjectEmployee(Connection conn) throws SQLException {
        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS ProjectEmployee (" +
                "p_id int(11) DEFAULT NULL," +
                "emp_id int(11) DEFAULT NULL," +
                "start_date date DEFAULT NULL," +
                "end_date date DEFAULT NULL," +
                " KEY p_id (p_id), " +
                "KEY emp_id (emp_id), " +
                "CONSTRAINT ProjectEmployee_ibfk_1 FOREIGN KEY (p_id)" +
                        " REFERENCES Projects (p_id)," +
                " CONSTRAINT ProjectEmployee_ibfk_2 FOREIGN KEY (emp_id) " +
                        "REFERENCES Employee (id));");
        stmt.executeUpdate();
    }

    private void createBillableEmployee(Connection conn) throws SQLException{
        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS BillableEmployee (" +
                "emp_id int(11) DEFAULT NULL," +
                "day_rate decimal(8,2) NOT NULL," +
                "KEY emp_id (emp_id)," +
                "CONSTRAINT BillableEmployee_ibfk_1 FOREIGN KEY (emp_id)" +
                        " REFERENCES Employee (id))");
        stmt.executeUpdate();
    }

    private void createProjects(Connection conn) throws SQLException{
        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Projects (" +
                " p_id int(11) NOT NULL AUTO_INCREMENT," +
                " start_date date DEFAULT NULL," +
                " end_date date DEFAULT NULL," +
                "project_name varchar(50) NOT NULL," +
                "PRIMARY KEY (p_id))");
        stmt.executeUpdate();
    }

    private void createSalesEmployee(Connection conn) throws SQLException {
        stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS SalesEmployee (" +
                "emp_id int(11) DEFAULT NULL," +
                "TotalRevenue decimal(10,2) DEFAULT NULL," +
                "Commission decimal(5,2) DEFAULT NULL," +
                " KEY emp_id (emp_id)," +
                " CONSTRAINT SalesEmployee_ibfk_1 FOREIGN KEY (emp_id) " +
                "REFERENCES Employee (id)); ");
        stmt.executeUpdate();
    }

}
