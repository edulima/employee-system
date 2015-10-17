package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eduardol on 15/10/2015.
 */
public class Login {

    private PreparedStatement stmt;

    public boolean loginAttempt(Connection conn, String username, String password) {
        try
        {
            stmt = conn.prepareStatement("SELECT * FROM Users WHERE username=? AND password=?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs  = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.err.print("Problem connecting to db" + e.getMessage());
        }
        return false;
    }
}
