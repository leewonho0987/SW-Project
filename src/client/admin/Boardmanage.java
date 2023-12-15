package client.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.Board.BoardDAO;

public class Boardmanage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField modifyname;
	private JTextField modifyexplain;
	private int boardId;
	private adminselect adminSelect;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Boardmanage frame = new Boardmanage(1,new adminselect());
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
	public Boardmanage(int boardid,adminselect adminSelect) {
		this.boardId = boardid;
		this.adminSelect = adminSelect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 427, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modifyname = new JTextField();
		modifyname.setBounds(12, 50, 381, 21);
		contentPane.add(modifyname);
		modifyname.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("수정할 게시판 이름");
		lblNewLabel.setBounds(12, 25, 211, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("수정할 게시판 설명");
		lblNewLabel_1.setBounds(12, 81, 412, 15);
		contentPane.add(lblNewLabel_1);
		
		modifyexplain = new JTextField();
		modifyexplain.setBounds(12, 101, 381, 247);
		contentPane.add(modifyexplain);
		modifyexplain.setColumns(10);
		
		JButton modifybtn = new JButton("수정하기");
        modifybtn.setBounds(154, 379, 97, 23);
        contentPane.add(modifybtn);

        // 수정 버튼 클릭 이벤트 처리
        modifybtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBoardInfo(); // 수정 정보 전달 및 업데이트 메서드 호출
                adminSelect.loadBoardList(); // adminselect 창 갱신
                dispose(); // Boardmanage 창 닫기
            }
        });

	}
	private void updateBoardInfo() {
        String newBoardName = modifyname.getText();
        String newBoardExplanation = modifyexplain.getText();

        BoardDAO.updateBoardInfo(boardId, newBoardName, newBoardExplanation);
        // 수정 후에는 필요에 따라 화면 갱신 등을 할 수 있음
    }
}
