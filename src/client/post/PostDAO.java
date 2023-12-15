package client.post;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.DBUtil;
import client.login.user;

public class PostDAO {

    private static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BoardSystemDB;encrypt=true;trustServerCertificate=true;";
    private static final String DBUSER = "lastalice";
    private static final String DBPASSWORD = "alice0628!!";

    public static List<Post> getPostsByBoardId(int boardId) {
        List<Post> posts = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Posts WHERE B_ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, boardId);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Post post = new Post();
                        post.setP_ID(rs.getInt("P_ID"));
                        post.setB_ID(rs.getInt("B_ID"));
                        post.setU_ID(rs.getString("U_ID"));
                        post.setTitle(rs.getString("Title"));
                        post.setDetail(rs.getString("Detail"));
                        post.setP_Date(rs.getString("P_Date"));
                        post.setViews(rs.getInt("Views"));
                        post.setBest(rs.getInt("Best"));
                        post.setWorst(rs.getInt("Worst"));
                        post.setState(rs.getString("State"));
                        post.setNotice(rs.getString("Notice"));
                        post.setPin(rs.getString("Pin"));
                        posts.add(post);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public static Post getPostById(int postId) {
        Post post = null;

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Posts WHERE P_ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, postId);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        post = new Post();
                        post.setP_ID(rs.getInt("P_ID"));
                        post.setB_ID(rs.getInt("B_ID"));
                        post.setU_ID(rs.getString("U_ID"));
                        post.setTitle(rs.getString("Title"));
                        post.setDetail(rs.getString("Detail"));
                        post.setP_Date(rs.getString("P_Date"));
                        post.setViews(rs.getInt("Views"));
                        post.setBest(rs.getInt("Best"));
                        post.setWorst(rs.getInt("Worst"));
                        post.setState(rs.getString("State"));
                        post.setNotice(rs.getString("Notice"));
                        post.setPin(rs.getString("Pin"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }
    public static void createPost(String title, String content, int boardId, user user) {
        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "INSERT INTO Posts (B_ID, U_ID, Title, Detail, P_Date) VALUES (?, ?, ?, ?, GETDATE())";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, boardId);
                pst.setString(2, user.getId());
                pst.setString(3, title);
                pst.setString(4, content);

                pst.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean deletePost(int postId) {
        boolean success = false;

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            // 게시글 삭제 SQL 쿼리
            String deleteQuery = "DELETE FROM Posts WHERE P_ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(deleteQuery)) {
                pst.setInt(1, postId);

                // executeUpdate 메서드는 DELETE, UPDATE, INSERT 등의 DML 쿼리를 실행할 때 사용
                int rowsAffected = pst.executeUpdate();

                // rowsAffected가 1이면 삭제 성공
                success = rowsAffected == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
    public static void incrementBest(int postId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Posts SET Best = Best + 1 WHERE P_ID = ?")) {

            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void incrementWorst(int postId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Posts SET Worst = Worst + 1 WHERE P_ID = ?")) {

            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
