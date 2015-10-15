import database.DBConnection;
import database.DBStatements;

import javax.security.auth.login.LoginException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by eduardol on 15/10/2015.
 */
public class AppInterface {

    private Connection connection;
    private Scanner sc;
    private DBStatements dbStatements;

    public AppInterface () {

        DBConnection con = new DBConnection();
        connection = con.connect();

        dbStatements = new DBStatements();
        dbStatements.createDatabase(connection);

        sc = new Scanner(System.in);

        displayAppInterface();
    }

    public void displayAppInterface() {

        System.out.println("Welcome");

        System.out.println("Please enter username:");
        String username = sc.nextLine();

        System.out.println("Please enter password:");
        String password = sc.nextLine();

        tryLogin(connection, username, password);

        System.out.print(username);


    }

    public void tryLogin(Connection con, String username, String password) {
        try {
           dbStatements.loginAttempt(con, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }


}
