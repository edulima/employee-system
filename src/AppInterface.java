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
    private DBConnection con;
    private Scanner sc;
    private CreateDatabase createDatabase;
    private Login login;

    public AppInterface () {

        con = new DBConnection();
        login = new Login();
        connection = con.connect();

        createDatabase = new CreateDatabase();
        createDatabase.createDatabase(connection);

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
    }

    public void tryLogin(Connection con, String username, String password) {

        if (!login.loginAttempt(con, username, password)) {
            System.out.println("Unauthorized Access for user " + username);
        } else {
            System.out.println("Welcome admin:" + username);
        }
    }
}
