package client.report;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Reportpage extends JFrame {

    private JPanel contentPane;
    private JList<Report> reportList;
    private JLabel lblNewLabel_1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Reportpage frame = new Reportpage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Reportpage() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 726, 530);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("신고 관리 페이지");
        lblNewLabel.setBounds(12, 10, 686, 15);
        contentPane.add(lblNewLabel);

        reportList = new JList<>();
        reportList.setBounds(22, 52, 664, 417);
        contentPane.add(reportList);
        
        lblNewLabel_1 = new JLabel("신고ID / 신고자 / 신고 제목 / 신고 내용 / 신고 날짜 / 처리 상태");
        lblNewLabel_1.setBounds(22, 35, 664, 15);
        contentPane.add(lblNewLabel_1);

        // Report 목록 불러오기 및 출력
        loadReportList();

        // 더블 클릭 이벤트 처리
        reportList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                handleReportDoubleClick();
            }
        });
    }

    private void loadReportList() {
        List<Report> reports = ReportDAO.getAllReports();
        DefaultListModel<Report> model = new DefaultListModel<>();
        for (Report report : reports) {
            model.addElement(report);
        }
        reportList.setModel(model);
    }




    private void handleReportDoubleClick() {
        Report selectedReport = reportList.getSelectedValue();
        if (selectedReport != null && selectedReport.getState().equals("미처리    ")) {
            System.out.println("Selected report: " + selectedReport.getId());
            int option = JOptionPane.showConfirmDialog(this, "정말 처리 상태로 변경하시겠습니까?", "처리 상태 변경 확인", JOptionPane.YES_NO_OPTION);
            
            if (option == JOptionPane.YES_OPTION) {
                System.out.println("User confirmed the change.");

                // 사용자가 확인을 선택한 경우
                int reportId = selectedReport.getId();
                ReportDAO.updateReportState(reportId, "처리");

                // 변경된 상태를 반영하여 목록을 다시 불러옴
                loadReportList();
            }
        }
    }


}
