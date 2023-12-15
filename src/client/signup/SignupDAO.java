package client.signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupDAO {
    private static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BoardSystemDB;encrypt=true;trustServerCertificate=true;";
    private static final String DBUSER = "lastalice";
    private static final String DBPASSWORD = "alice0628!!";

    public static boolean checkDuplicateID(String userId) {
        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Users WHERE U_ID=?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, userId);

                try (ResultSet rs = pst.executeQuery()) {
                    return rs.next(); // If there is a result, the ID already exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registerUser(String userId, String password, String userName) {
        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "INSERT INTO Users (U_ID, PW, U_Name) VALUES (?, ?, ?)";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, userId);
                pst.setString(2, password);
                pst.setString(3, userName);

                int result = pst.executeUpdate();
                return result > 0; // If rows are affected, registration is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
