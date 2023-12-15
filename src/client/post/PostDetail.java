package client.post;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.comment.Comment;
import client.comment.CommentDAO;
import client.login.user;
import client.report.Report;
import client.report.ReportDAO;

public class PostDetail extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField commentField;
    private int postId;
    private JLabel creater;
    private JLabel createdate;
    private JLabel postdetail;
    private JLabel bestlabel;
    private DefaultListModel<Comment> commentListModel;
    private user user;
    private JButton delbtn;
    private JButton annobtn;
    private JButton lockbtn;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PostDetail frame = new PostDetail(1,new user("", "", "", "admin     ", ""));
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
    public PostDetail(int postId,user user) {
    	this.user=user;
        this.postId = postId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 608);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        delbtn = new JButton("삭제하기");
        delbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (handleDeleteButtonClick()) {
                    dispose(); // 창 닫기
                };
            }
        });
        delbtn.setBounds(410, 22, 97, 23);
        contentPane.add(delbtn);

        annobtn = new JButton("공지등록");
        annobtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        annobtn.setBounds(516, 22, 97, 23);
        contentPane.add(annobtn);

        lockbtn = new JButton("고정하기");
        lockbtn.setBounds(625, 22, 97, 23);
        contentPane.add(lockbtn);

        JButton bestbtn = new JButton("추천");
        bestbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRecommendButtonClick(true);
            }
        });
        bestbtn.setBounds(410, 55, 97, 23);
        contentPane.add(bestbtn);

        JButton worstbtn = new JButton("비추천");
        worstbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleRecommendButtonClick(false);
            }
        });
        worstbtn.setBounds(516, 55, 97, 23);
        contentPane.add(worstbtn);

        JButton declarebtn = new JButton("신고하기");
     // PostDetail 클래스 내부에서 신고 버튼에 대한 리스너를 추가
        declarebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Report report = showReportDialog();
                if (report != null) {
                    boolean success = ReportDAO.addReport(report);
                    if (success) {
                        JOptionPane.showMessageDialog(PostDetail.this, "신고가 정상적으로 등록되었습니다.");
                    } else {
                        JOptionPane.showMessageDialog(PostDetail.this, "신고 등록에 실패했습니다.");
                    }
                }
            }
        });
        declarebtn.setBounds(625, 55, 97, 23);
        contentPane.add(declarebtn);

        creater = new JLabel("작성자명");
        creater.setBounds(47, 64, 136, 15);
        contentPane.add(creater);

        createdate = new JLabel("작성날짜");
        createdate.setBounds(47, 95, 228, 15);
        contentPane.add(createdate);

        postdetail = new JLabel("게시글 내용");
        postdetail.setVerticalAlignment(SwingConstants.TOP);
        postdetail.setHorizontalAlignment(SwingConstants.LEFT);
        postdetail.setBounds(47, 120, 676, 265);
        contentPane.add(postdetail);

        JList commentList = new JList();
        commentList.setBounds(46, 405, 676, 100);
        contentPane.add(commentList);
        
        commentListModel = new DefaultListModel<>();
        commentList.setModel(commentListModel);
        
        commentField = new JTextField();
        commentField.setBounds(47, 515, 566, 44);
        contentPane.add(commentField);
        commentField.setColumns(10);

        JButton commentbtn = new JButton("댓글 쓰기");
        commentbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCommentButtonClick();
            }
        });
        commentbtn.setBounds(625, 525, 97, 23);
        contentPane.add(commentbtn);

        bestlabel = new JLabel("추천수");
        bestlabel.setFont(new Font("굴림", Font.PLAIN, 20));
        bestlabel.setBounds(287, 55, 111, 51);
        contentPane.add(bestlabel);

        loadPostDetails(); // 게시글 내용을 가져와 UI에 표시
        
    }

    private void loadPostDetails() {
        // postId를 이용하여 해당 게시글의 정보를 가져오는 메서드 호출
        Post post = PostDAO.getPostById(postId);
        creater.setText("작성자: " + post.getU_ID()); // 작성자명
        createdate.setText("작성날짜: " + post.getP_Date()); // 작성날짜
        postdetail.setText("게시글 내용: " + post.getDetail()); // 게시글 내용
        bestlabel.setText("추천수: " + (post.getBest() - post.getWorst())); // 추천수

        // 삭제 버튼 보이기/숨기기 로직 추가
        if (user.getId().equals(post.getU_ID()) || user.getDivision().trim().equals("admin")) {
            delbtn.setVisible(true);
        } else {
            delbtn.setVisible(false);
        }
        if (user.getDivision().trim().equals("admin")) {
            annobtn.setVisible(true);
            lockbtn.setVisible(true);
        } else {
            annobtn.setVisible(false);
            lockbtn.setVisible(false);
        }

        loadComments(post.getP_ID());
    }
    private void loadComments(int postId) {
        List<Comment> comments = CommentDAO.getCommentsByPostId(postId);

        commentListModel.clear(); // 기존 목록 비우기

        for (Comment comment : comments) {
            commentListModel.addElement(comment);
        }
    }
    private boolean handleDeleteButtonClick() {
        // 정말로 삭제할건지 확인 후 게시글 삭제
        int result = JOptionPane.showConfirmDialog(this, "게시글을 정말로 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // 삭제 처리
            boolean success = PostDAO.deletePost(postId);
            if (success) {
                JOptionPane.showMessageDialog(this, "게시글이 삭제되었습니다.");
                // 게시글이 삭제되면 현재 창 닫기 등의 로직을 추가할 수 있습니다.
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "게시글 삭제에 실패했습니다.");
            }
        }

        return false;
    }
    private Report showReportDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField titleField = new JTextField();
        JTextArea detailArea = new JTextArea();

        panel.add(new JLabel("신고 제목:"));
        panel.add(titleField);
        panel.add(new JLabel("신고 내용:"));
        panel.add(detailArea);

        int result = JOptionPane.showConfirmDialog(this, panel, "신고 작성", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String detail = detailArea.getText();
            return new Report(0, postId, user.getId(), title, detail, null, null, "미처리");
        }
        return null;
    }
    private void handleRecommendButtonClick(boolean recommend) {
        if (recommend) {
            // 추천 버튼 클릭 시
            PostDAO.incrementBest(postId);
        } else {
            // 비추천 버튼 클릭 시
            PostDAO.incrementWorst(postId);
        }
        loadPostDetails(); // 변경된 추천/비추천 수를 반영하여 UI 갱신
    }
    private void handleCommentButtonClick() {
        String commentContent = commentField.getText().trim();

        if (!commentContent.isEmpty()) {
            Comment newComment = new Comment(0, postId, user.getId(), commentContent, null);
            boolean success = CommentDAO.addComment(newComment);

            if (success) {
                JOptionPane.showMessageDialog(this, "댓글이 성공적으로 등록되었습니다.");
                commentField.setText(""); // 댓글 입력 필드 비우기
                loadComments(postId); // 변경된 댓글 목록을 다시 로드하여 UI 갱신
            } else {
                JOptionPane.showMessageDialog(this, "댓글 등록에 실패했습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "댓글 내용을 입력하세요.");
        }
    }
}
