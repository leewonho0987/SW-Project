package client.post;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import client.login.user;

public class CreatePost extends JFrame {

    private JPanel contentPane;
    private JTextField titleField;
    private JTextField contentField;
    private int boardId;
    private user user;

    public CreatePost(int boardId, user user) {
        this.boardId = boardId;
        this.user = user;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("제목");
        titleLabel.setBounds(47, 35, 57, 15);
        contentPane.add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(116, 32, 280, 21);
        contentPane.add(titleField);
        titleField.setColumns(10);

        JLabel contentLabel = new JLabel("내용");
        contentLabel.setBounds(47, 82, 57, 15);
        contentPane.add(contentLabel);

        contentField = new JTextField();
        contentField.setBounds(116, 79, 280, 123);
        contentPane.add(contentField);
        contentField.setColumns(10);

        JButton submitButton = new JButton("작성 완료");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmitButton();
            }
        });
        submitButton.setBounds(300, 225, 96, 23);
        contentPane.add(submitButton);
    }

    private void handleSubmitButton() {
        // 글 작성 완료 버튼이 눌렸을 때의 로직을 추가
        String title = titleField.getText();
        String content = contentField.getText();

        // title, content, boardId, user 정보를 이용하여 데이터베이스에 글을 추가하는 메서드 호출
        PostDAO.createPost(title, content, boardId, user);
        
        dispose();
    }
}
