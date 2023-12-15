package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("컴활 게시판");
		lblNewLabel.setBounds(12, 10, 71, 15);
		contentPane.add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(40, 70, 400, 1);
		contentPane.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(38, 108, 400, 1);
		contentPane.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(42, 144, 400, 1);
		contentPane.add(list_2);
		
		JList list_1_1 = new JList();
		list_1_1.setBounds(40, 182, 400, 1);
		contentPane.add(list_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("1");
		lblNewLabel_1.setBounds(12, 83, 18, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("2");
		lblNewLabel_1_1.setBounds(12, 119, 18, 15);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("공지");
		lblNewLabel_2.setBounds(40, 83, 29, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("제목");
		lblNewLabel_2_1.setBounds(85, 45, 29, 15);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_4 = new JLabel("작성자");
		lblNewLabel_4.setBounds(165, 45, 50, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("MZK");
		lblNewLabel_5.setBounds(165, 83, 36, 15);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("KTH");
		lblNewLabel_5_1.setBounds(165, 119, 36, 15);
		contentPane.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("작성일");
		lblNewLabel_5_2.setBounds(227, 45, 43, 15);
		contentPane.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_6 = new JLabel("2023.10.30");
		lblNewLabel_6.setBounds(227, 83, 61, 15);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("2023.11.25");
		lblNewLabel_6_1.setBounds(227, 119, 61, 15);
		contentPane.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_7 = new JLabel("추천수");
		lblNewLabel_7.setBounds(297, 45, 43, 15);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("조회수");
		lblNewLabel_7_1.setBounds(352, 45, 43, 15);
		contentPane.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("3");
		lblNewLabel_2_2.setBounds(305, 81, 18, 15);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("3");
		lblNewLabel_2_2_1.setBounds(361, 83, 18, 15);
		contentPane.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("1");
		lblNewLabel_2_2_2.setBounds(305, 119, 18, 15);
		contentPane.add(lblNewLabel_2_2_2);
		
		JLabel lblNewLabel_2_2_3 = new JLabel("2");
		lblNewLabel_2_2_3.setBounds(361, 119, 18, 15);
		contentPane.add(lblNewLabel_2_2_3);
		
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(391, 75, 63, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(391, 112, 63, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("글쓰기");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(369, 205, 71, 23);
		contentPane.add(btnNewButton_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"전체", "제목", "작성자", "댓글 내용", "댓글 작성자"}));
		comboBox.setBounds(69, 248, 110, 23);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setText("검색어를 입력해주세요.");
		textField.setBounds(178, 249, 168, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("검색");
		btnNewButton_3.setBounds(347, 248, 61, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_8 = new JLabel("1");
		lblNewLabel_8.setBounds(224, 190, 18, 15);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_4 = new JButton("닫기");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setBounds(377, 6, 63, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_1_2 = new JButton("사용방법");
		btnNewButton_1_2.setBounds(69, 75, 84, 23);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("컴활2급");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_2_1.setBounds(69, 113, 84, 23);
		contentPane.add(btnNewButton_1_2_1);
	}

}
