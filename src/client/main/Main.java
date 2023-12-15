package client.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.Board.Board;
import client.Board.BoardDAO;
import client.admin.adminselect;
import client.login.user;
import client.post.PostBoard;

public class Main extends JFrame {

    private JPanel contentPane;
    private DefaultListModel<Board> boardListModel;
    private JList<Board> boardList;
    private JButton adminpagebtn; 
    private user user;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main(new user("", "", "", "admin     ", ""));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main(user user) {
    	this.user = user;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel username = new JLabel("");
        username.setText(user.getName() + "님 반갑습니다.");
        username.setBounds(755, 10, 117, 32);
        contentPane.add(username);

        JButton mypagebtn = new JButton("내 정보");
        mypagebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 내 정보 창으로 이동하는 로직 추가
            }
        });
        mypagebtn.setBounds(606, 10, 137, 32);
        contentPane.add(mypagebtn);

        adminpagebtn = new JButton("관리자 페이지");
        adminpagebtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		adminselect ap = new adminselect();
        		ap.setVisible(true);
        	}
        });
        adminpagebtn.setBounds(470, 10, 124, 32);
        contentPane.add(adminpagebtn);

        // 좌측에 게시판 목록을 표시할 JList
        boardListModel = new DefaultListModel<>();
        boardList = new JList<>(boardListModel);
        boardList.setBounds(12, 77, 256, 447);
        contentPane.add(boardList);

        ImageIcon imageIcon = new ImageIcon("share.jpg");

        JLabel imglabel = new JLabel();
        imglabel.setBounds(298, 78, 555, 446);
        imglabel.setIcon(imageIcon);
        contentPane.add(imglabel);

        // 게시판 목록을 데이터베이스에서 불러와 JList에 추가
        List<Board> boards = BoardDAO.getAllBoards();
        for (Board board : boards) {
            boardListModel.addElement(board);
        }
        // division이 'admin'인 경우에만 adminpagebtn을 보이게 함
        if (user.getDivision().equals("admin     ")) {
            adminpagebtn.setVisible(true);
        } else {
            adminpagebtn.setVisible(false);
        }

        boardList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 체크
                    int index = boardList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        Board selectedBoard = boardListModel.getElementAt(index);
                        handleDoubleClick(selectedBoard);
                    }
                }
            }
        });
    }

    private void handleDoubleClick(Board selectedBoard) {
        // 더블 클릭한 게시판의 정보를 가져와서 PostBoard에 전달
        EventQueue.invokeLater(() -> {
            PostBoard postBoard = new PostBoard(selectedBoard.getId(),user);
            postBoard.setBoardName(selectedBoard.getName());
            postBoard.setVisible(true);
        });
    }
}

