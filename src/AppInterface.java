import database.DBConnection;
import database.DBStatements;
import java.sql.Connection;

/**
 * Created by eduardol on 15/10/2015.
 */
public class AppInterface {

    private Connection connection;

    public AppInterface () {

        DBConnection con = new DBConnection();
        connection = con.connect();

        DBStatements createDB = new DBStatements();
        createDB.createDatabase(connection);

    }


}
