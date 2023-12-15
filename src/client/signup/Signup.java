package client.signup;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Signup extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField pw;
	private JTextField pwcheck;
	private JTextField name;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup frame = new Signup();
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
	public Signup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel Signup = new JLabel("회원가입");
		Signup.setFont(new Font("굴림", Font.PLAIN, 20));
		Signup.setBounds(171, 10, 85, 31);
		contentPane.add(Signup);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(82, 62, 50, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("PW");
		lblNewLabel_1.setBounds(82, 87, 50, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("PW확인");
		lblNewLabel_2.setBounds(82, 112, 50, 15);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("이름");
		lblNewLabel_3.setBounds(82, 137, 50, 15);
		contentPane.add(lblNewLabel_3);

		id = new JTextField();
		id.setBounds(160, 59, 96, 21);
		contentPane.add(id);
		id.setColumns(10);

		pw = new JPasswordField();
		pw.setBounds(160, 84, 96, 21);
		contentPane.add(pw);

		pwcheck = new JPasswordField();
		pwcheck.setBounds(160, 109, 96, 21);
		contentPane.add(pwcheck);

		name = new JTextField();
		name.setBounds(160, 134, 96, 21);
		contentPane.add(name);
		name.setColumns(10);

		JButton btnNewButton = new JButton("취소");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(219, 177, 91, 23);
		contentPane.add(btnNewButton);

		JButton btnSignup = new JButton("가입");
		btnSignup.setBounds(98, 177, 91, 23);
		contentPane.add(btnSignup);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = id.getText();
				char[] passwordChars = ((JPasswordField) pw).getPassword();
				String password = new String(passwordChars);
				String userName = name.getText();

				// 패스워드와 패스워드 확인이 일치하는지 확인
				if (!password.equals(new String(((JPasswordField) pwcheck).getPassword()))) {
					JOptionPane.showMessageDialog(Signup.this, "패스워드와 패스워드 확인이 일치하지 않습니다.");
					return; // 일치하지 않으면 가입 처리 중단
				}

				// 회원가입 진행
				if (SignupDAO.registerUser(userId, password, userName)) {
					JOptionPane.showMessageDialog(Signup.this, "회원가입 성공!");
					dispose(); // 회원가입 성공 후 창 닫기
				} else {
					JOptionPane.showMessageDialog(Signup.this, "회원가입 실패!");
				}

				// 비밀번호 문자열을 사용한 후에는 가능하면 메모리에서 삭제하는 것이 안전
				java.util.Arrays.fill(passwordChars, ' ');
			}
		});

		JButton btnCheckDuplicate = new JButton("중복확인");
		btnCheckDuplicate.setBounds(263, 58, 84, 23);
		contentPane.add(btnCheckDuplicate);
		btnCheckDuplicate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = id.getText();
				if (SignupDAO.checkDuplicateID(userId)) {
					JOptionPane.showMessageDialog(Signup.this, "이미 사용 중인 ID입니다.");
				} else {
					JOptionPane.showMessageDialog(Signup.this, "사용 가능한 ID입니다.");
				}
			}
		});
	}

}

