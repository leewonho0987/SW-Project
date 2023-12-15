package client.admin;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import client.Board.Board;
import client.Board.BoardDAO;
import client.login.UserDao;
import client.login.user;
import client.report.Reportpage;

public class adminselect extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton usermanage;
    private JButton boardManage;
    private JTable userTable;
    private JTable boardTable;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    adminselect frame = new adminselect();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public adminselect() {
        setTitle("관리자페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 745, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        usermanage = new JButton("회원관리");
        usermanage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadUserList(); // 회원 목록을 불러오는 메서드 호출
            }
        });
        contentPane.add(usermanage);

        boardManage = new JButton("게시판 관리");
        boardManage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadBoardList(); // 게시판 목록을 불러오는 메서드 호출
            }
        });
        contentPane.add(boardManage);

        JButton declaremanage = new JButton("신고관리");
        declaremanage.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Reportpage rp = new Reportpage();
        		rp.setVisible(true);
        	}
        });
        contentPane.add(declaremanage);

        // JTable 생성
        userTable = new JTable();
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 체크
                    int row = userTable.getSelectedRow();
                    if (row >= 0) {
                        // 더블클릭한 회원 정보를 가져옴
                        String userId = (String) userTable.getValueAt(row, 0);
                        handleUserDoubleClick(userId);
                    }
                }
            }
        });
        contentPane.add(userTable);
        boardTable = new JTable();
        boardTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 체크
                    int row = boardTable.getSelectedRow();
                    if (row >= 0) {
                        // 더블클릭한 게시판 정보를 가져옴
                        int boardId = (int) boardTable.getValueAt(row, 0);
                        handleBoardDoubleClick(boardId);
                    }
                }
            }
        });
        contentPane.add(boardTable);
    }
    
    

    private void loadUserList() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Division");
        model.addColumn("Blocked Until");

        List<user> users = UserDao.getAllUsers();
        for (user user : users) {
            model.addRow(new Object[]{user.getId(), user.getName(), user.getDivision(), user.getBlock()});
        }
        userTable.setModel(model);
        
    }

    private void handleUserDoubleClick(String userId) {
        // 더블 클릭한 회원의 차단 기간을 수정하는 창을 열어주는 로직 추가
        // 예를 들어, UserBlockFrame 클래스를 생성하여 열 수 있습니다.
        EventQueue.invokeLater(() -> {
            Usermanage userBlock = new Usermanage(userId);
            userBlock.setVisible(true);
        });
    }
    void loadBoardList() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Explanation");
        model.addColumn("User ID");
        model.addColumn("Date");

        List<Board> boards = BoardDAO.getAllBoards();
        for (Board board : boards) {
            model.addRow(new Object[]{board.getId(), board.getName(), board.getExplanation(), board.getUserId(), board.getDate()});
        }
        boardTable.setModel(model);
    }

    private void handleBoardDoubleClick(int boardId) {
        // 더블 클릭한 게시판의 정보를 수정하는 창을 열어주는 로직 추가
        // 예를 들어, Boardmanage 클래스를 생성하여 열 수 있습니다.
        EventQueue.invokeLater(() -> {
            Boardmanage boardManage = new Boardmanage(boardId, this); // this를 전달하여 현재 클래스의 참조를 넘김
            boardManage.setVisible(true);
        });
    }
}

