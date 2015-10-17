package app;

import database.AddNewEmployee;
import database.DBConnection;
import database.CreateDatabase;
import login.Login;
import java.sql.Connection;
import java.util.Scanner;

/**
 * Created by eduardol on 15/10/2015.
 */
public class AppInterface {

    private Connection connection;
    private DBConnection creteConnection;
    private CreateDatabase createDatabase;
    private Login login;
    private Scanner sc;

    public AppInterface () {

        sc = new Scanner(System.in);
        creteConnection = new DBConnection();
        connection = creteConnection.connect();
        login = new Login();

        createDatabase = new CreateDatabase();
        createDatabase.createDatabase(connection);

        sc = new Scanner(System.in);

        displayAppInterface();
    }

    private void displayAppInterface() {

        System.out.println("Welcome");

        System.out.println("Please enter username:");
        String username = sc.nextLine();

        System.out.println("Please enter password:");
        String password = sc.nextLine();

        if(tryLogin(connection, username, password)) {

            switch (adminMenu()) {
                case "a":
                    addEmployee();
                case "u":
                case "d":
                default:
            }
        }
    }

    private void addEmployee () {

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter employee address:");
        String address = sc.nextLine();

        System.out.print("Enter employee salary:");

        double salary = salaryFormatter(sc.nextLine());

        new AddNewEmployee(name, address, salary, "Employee");
    }

    private String adminMenu() {

        String choice;
        System.out.println("What would you like to do?");
        System.out.println("Menu options:");
        System.out.println("a - Add new employee");
        System.out.println("u - Update employee details");
        System.out.println("d - Remove employee");

        choice = sc.nextLine();
        return choice;
    }

    private boolean tryLogin(Connection con, String username, String password) {

        if (!login.loginAttempt(con, username, password)) {
            System.out.println("Unauthorized Access for user " + username);
            return false;
        } else {
            System.out.println("Welcome admin:" + username);
            return true;
        }
    }

    private double salaryFormatter (String salary) {
        String temp = String.valueOf(salary);
        temp = temp.replace(',', '.');
        return Double.parseDouble(temp);
    }
}
