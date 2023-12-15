package client.login;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.main.Main;
import client.signup.Signup;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JPasswordField pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 395, 316);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 379, 277);
        contentPane.add(panel);
        panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(116, 109, 50, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(116, 153, 50, 15);
		panel.add(lblNewLabel_1);
		
		id = new JTextField();
		id.setBounds(178, 106, 96, 21);
		panel.add(id);
		id.setColumns(10);
		
		pw = new JPasswordField();
		pw.setBounds(178, 150, 96, 21);
		panel.add(pw);
		pw.setColumns(10);
		
		JButton btnNewButton = new JButton("로그인");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userId = id.getText();
                char[] passwordChars = pw.getPassword();
                String password = new String(passwordChars); // char 배열을 String으로 변환

                if (UserDao.login(userId, password)) {
                    JOptionPane.showMessageDialog(Login.this, "로그인 성공!");
                    dispose();
                    Main main = new Main(UserDao.returnuser(id.getText()));
                    main.setVisible(true);
                } else {
                    // 로그인 실패 시 차단 여부에 따라 메시지 출력
                    user blockedUser = UserDao.returnuser(userId);
                    if (blockedUser != null && isBlocked(blockedUser.getBlock())) {
                        JOptionPane.showMessageDialog(Login.this, "계정이 차단되어 있습니다.");
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "로그인 실패!");
                    }
                }

                // 비밀번호 문자열을 사용한 후에는 가능하면 메모리에서 삭제하는 것이 안전
                java.util.Arrays.fill(passwordChars, ' ');
            }
        });
        btnNewButton.setBounds(92, 199, 91, 23);
        panel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("회원가입");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Signup sup = new Signup();
                sup.setVisible(true);
            }
        });
        btnNewButton_1.setBounds(194, 199, 91, 23);
        panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("자격증 취득 정보공유 프로그램");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel_2.setBounds(12, 10, 355, 89);
		panel.add(lblNewLabel_2);
	}
	private boolean isBlocked(String blockUntil) {
        return UserDao.isBlocked(blockUntil);
    }
}