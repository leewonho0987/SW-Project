package client.Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.DBUtil;

public class BoardDAO {
 public static List<Board> getAllBoards() {
     List<Board> boardList = new ArrayList<>();

     try (Connection connection = DBUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Boards");
          ResultSet resultSet = preparedStatement.executeQuery()) {

         while (resultSet.next()) {
             int id = resultSet.getInt("B_ID");
             String name = resultSet.getString("B_Name");
             String explanation = resultSet.getString("B_Explain");
             String userId = resultSet.getString("U_ID");
             String date = resultSet.getString("B_Date");

             Board board = new Board(id, name, explanation, userId, date);
             boardList.add(board);
         }

     } catch (SQLException e) {
         e.printStackTrace();
     }

     return boardList;
 }
 public static void updateBoardInfo(int boardId, String newBoardName, String newBoardExplanation) {
     try (Connection connection = DBUtil.getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Boards SET B_Name = ?, B_Explain = ? WHERE B_ID = ?");
     ) {
         preparedStatement.setString(1, newBoardName);
         preparedStatement.setString(2, newBoardExplanation);
         preparedStatement.setInt(3, boardId);

         preparedStatement.executeUpdate();
         // 수정 후에는 필요에 따라 화면 갱신 등을 할 수 있음
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
}
