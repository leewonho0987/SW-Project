package client.admin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.login.UserDao;
import client.login.user;

public class Usermanage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> dayComboBox;
    private JButton btnAssignAdmin;
    private String ID;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Usermanage frame = new Usermanage("");
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
    public Usermanage(String ID) {
    	this.ID=ID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 371, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 연도 선택
        yearComboBox = new JComboBox<>();
        yearComboBox.setBounds(32, 54, 80, 25);
        for (int year = 2022; year <= 2030; year++) {
            yearComboBox.addItem(Integer.toString(year));
        }
        contentPane.add(yearComboBox);

        // 월 선택
        monthComboBox = new JComboBox<>();
        monthComboBox.setBounds(132, 54, 80, 25);
        for (int month = 1; month <= 12; month++) {
            monthComboBox.addItem(String.format("%02d", month));
        }
        contentPane.add(monthComboBox);

        // 일 선택
        dayComboBox = new JComboBox<>();
        dayComboBox.setBounds(232, 54, 80, 25);
        for (int day = 1; day <= 31; day++) {
            dayComboBox.addItem(String.format("%02d", day));
        }
        contentPane.add(dayComboBox);

        // 차단하기 버튼
        JButton blockButton = new JButton("차단하기");
        blockButton.setBounds(32, 104, 280, 25);
        blockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 차단하기 버튼 클릭 시 실행되는 로직
                blockUser(ID);
            }
        });
        contentPane.add(blockButton);
        btnAssignAdmin = new JButton("관리자 부여");
        btnAssignAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAssignAdminButtonClick();
            }
        });
        btnAssignAdmin.setBounds(79, 157, 150, 30);
        contentPane.add(btnAssignAdmin);
    }
    


    private void blockUser(String userID) {
        // JComboBox에서 선택된 연, 월, 일을 가져옴
        String selectedYear = (String) yearComboBox.getSelectedItem();
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        String selectedDay = (String) dayComboBox.getSelectedItem();

        // 연, 월, 일을 조합하여 차단 기간을 만듦
        String blockUntil = selectedYear + "-" + selectedMonth + "-" + selectedDay;

        // 여기에서 사용자의 Block을 변경하는 메서드 호출
        // 예를 들어, UserDao의 메서드를 호출하여 사용자의 Block을 업데이트할 수 있음
        UserDao.blockUser(userID, blockUntil);

        // 창을 닫음
        dispose();
    }
    private void handleAssignAdminButtonClick() {
        // "부여" 버튼 클릭 시 관리자 부여 동작 처리
        boolean success = UserDao.assignAdmin(ID);

        if (success) {
            JOptionPane.showMessageDialog(this, "관리자 권한이 부여되었습니다.");
            btnAssignAdmin.setEnabled(false); // 부여 후 버튼 비활성화
        } else {
            JOptionPane.showMessageDialog(this, "관리자 부여에 실패했습니다.");
        }
    }
}

