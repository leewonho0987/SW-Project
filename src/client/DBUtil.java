package client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
 private static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BoardSystemDB;encrypt=true;trustServerCertificate=true;";
 private static final String DBUSER = "lastalice";
 private static final String DBPASSWORD = "alice0628!!";

 public static Connection getConnection() throws SQLException {
     return DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
 }
}
