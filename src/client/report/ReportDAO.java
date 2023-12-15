package client.report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.DBUtil;

public class ReportDAO {
    public static List<Report> getAllReports() {
        List<Report> reportList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Reports");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("R_ID");
                int postId = resultSet.getInt("P_ID");
                String userId = resultSet.getString("RU_ID");
                String title = resultSet.getString("R_Title");
                String detail = resultSet.getString("R_Detail");
                String date = resultSet.getString("R_Date");
                String moderatorId = resultSet.getString("M_ID");
                String state = resultSet.getString("R_State");

                Report report = new Report(id, postId, userId, title, detail, date, moderatorId, state);
                reportList.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportList;
    }

    public static void updateReportState(int reportId, String newState) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Reports SET R_State = ? WHERE R_ID = ?")) {

            preparedStatement.setString(1, newState);
            preparedStatement.setInt(2, reportId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean addReport(Report report) {
        String sql = "INSERT INTO Reports (P_ID, RU_ID, R_Title, R_Detail) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setInt(1, report.getPostId());
            preparedStatement.setString(2, report.getUserId());
            preparedStatement.setString(3, report.getTitle());
            preparedStatement.setString(4, report.getDetail());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
