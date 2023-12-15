package client.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BoardSystemDB;encrypt=true;trustServerCertificate=true;";
    private static final String DBUSER = "lastalice";
    private static final String DBPASSWORD = "alice0628!!";
    public static boolean login(String userId, String password) {
        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Users WHERE U_ID=?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, userId);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String blockUntil = rs.getString("Block");
                        if (isBlocked(blockUntil)) {
                            // 계정이 차단되어 있으면 로그인 실패
                            return false;
                        }

                        // 계정이 차단되어 있지 않으면 비밀번호 검사
                        query = "SELECT * FROM Users WHERE U_ID=? AND PW=?";
                        try (PreparedStatement loginPst = conn.prepareStatement(query)) {
                            loginPst.setString(1, userId);
                            loginPst.setString(2, password);

                            try (ResultSet loginRs = loginPst.executeQuery()) {
                                if (loginRs.next()) {
                                    return true; // If there is a result, the user exists
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static boolean isBlocked(String blockUntil) {
        if (blockUntil != null && !blockUntil.isEmpty()) {
            // 현재 시간과 비교하여 차단 여부 확인
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDateTime blockTime = LocalDateTime.parse(blockUntil, formatter);

            return now.isBefore(blockTime);
        }
        return false;
    }
    public static user returnuser(String id) {
        user resultUser = null;

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Users WHERE U_ID=?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        // 사용자 정보를 가져와 user 객체 생성
                        String userId = rs.getString("U_ID");
                        String password = rs.getString("PW");
                        String name = rs.getString("U_Name");
                        String division = rs.getString("Division");
                        String blocktime = rs.getString("Block");

                        resultUser = new user(userId, password, name, division, blocktime);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultUser;
    }
    public static List<user> getAllUsers() {
        List<user> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Users";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        user user = new user();
                        user.setId(rs.getString("U_ID"));
                        user.setName(rs.getString("U_Name"));
                        user.setPw(rs.getString("PW"));
                        user.setDivision(rs.getString("Division"));
                        user.setBlock(rs.getString("Block"));
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
    public static void blockUser(String userId, String blockUntil) {
        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "UPDATE Users SET Block = ? WHERE U_ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, blockUntil);
                pst.setString(2, userId);
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean assignAdmin(String userId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            String sql = "UPDATE Users SET Division = 'admin' WHERE U_ID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static List<user> getAllAdmins() {
        List<user> admins = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Users WHERE Division = 'admin'";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        user admin = new user();
                        admin.setId(rs.getString("U_ID"));
                        admin.setName(rs.getString("U_Name"));
                        admin.setPw(rs.getString("PW"));
                        admin.setDivision(rs.getString("Division"));
                        admin.setBlock(rs.getString("Block"));
                        admins.add(admin);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admins;
    }
}