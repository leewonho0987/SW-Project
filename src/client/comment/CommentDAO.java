package client.comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.DBUtil;

public class CommentDAO {

    private static final String DBURL = "jdbc:sqlserver://localhost:1433;databaseName=BoardSystemDB;encrypt=true;trustServerCertificate=true;";
    private static final String DBUSER = "lastalice";
    private static final String DBPASSWORD = "alice0628!!";

    public static List<Comment> getCommentsByPostId(int postId) {
        List<Comment> comments = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD)) {
            String query = "SELECT * FROM Comments WHERE P_ID = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, postId);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        Comment comment = new Comment();
                        comment.setC_ID(rs.getInt("C_ID"));
                        comment.setP_ID(rs.getInt("P_ID"));
                        comment.setC_U_ID(rs.getString("C_U_ID"));
                        comment.setC_Detail(rs.getString("C_Detail"));
                        comment.setC_Date(rs.getString("C_Date"));
                        comments.add(comment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comments;
    }
    public static boolean addComment(Comment comment) {
        String sql = "INSERT INTO Comments (P_ID, C_U_ID, C_Detail) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, comment.getP_ID());
            preparedStatement.setString(2, comment.getC_U_ID());
            preparedStatement.setString(3, comment.getC_Detail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
