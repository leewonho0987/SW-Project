package client.post;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import client.login.user;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PostBoard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField searchtext;
    private JTable posttable;
    private JLabel BoardName;
    private int boardId;
    private JButton Createpost;
    private user user;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PostBoard frame = new PostBoard(1,new user("", "", "", "admin     ", "")); // Pass the boardId here
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PostBoard(int boardId, user user) {
    	this.user=user;
        this.boardId = boardId;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 609);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JComboBox searchcategory = new JComboBox();
        searchcategory.setModel(new DefaultComboBoxModel(new String[] {"제목", "작성자"}));
        searchcategory.setBounds(54, 485, 69, 23);
        contentPane.add(searchcategory);

        searchtext = new JTextField();
        searchtext.setBounds(135, 486, 317, 21);
        contentPane.add(searchtext);
        searchtext.setColumns(10);

        JButton searchbtn = new JButton("검색");
        searchbtn.setBounds(458, 485, 97, 23);
        contentPane.add(searchbtn);

        posttable = new JTable();
        posttable.setBounds(54, 35, 610, 425);
        contentPane.add(posttable);

        BoardName = new JLabel("게시판 명");
        BoardName.setBounds(12, 10, 660, 15);
        contentPane.add(BoardName);
        
        Createpost = new JButton("글쓰기");
        Createpost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleCreatePost();
            }
        });
        Createpost.setBounds(567, 485, 97, 23);
        contentPane.add(Createpost);
        posttable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블클릭 체크
                    int row = posttable.getSelectedRow();
                    if (row >= 0) {
                        int postID = (int) posttable.getValueAt(row, 0);
                        handlePostDoubleClick(postID,user);
                    }
                }
            }
        });
        // Load and display posts
        loadPosts();
    }

    public void setBoardName(String boardName) {
        BoardName.setText(boardName);
    }

    private void loadPosts() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("글 번호");
        model.addColumn("제목");
        model.addColumn("작성자");
        model.addColumn("작성일");
        model.addColumn("조회수");

        List<Post> posts = PostDAO.getPostsByBoardId(boardId);
        model.addRow(new Object[] {"글 번호","제목","작성자","작성일","조회수"});
        for (Post post : posts) {
            model.addRow(new Object[]{
                post.getP_ID(),
                post.getTitle(),
                post.getU_ID(),
                post.getP_Date(),
                post.getViews()
            });
        }
        posttable.setModel(model);
    }
    private void handlePostDoubleClick(int postID,user user) {
        // 해당 postID에 대한 상세 내용을 표시하는 창을 여기서 열면 됩니다.
        // 예를 들어, PostDetailFrame 클래스를 생성하여 열 수 있습니다.
        PostDetail postDetailFrame = new PostDetail(postID,user);
        postDetailFrame.setVisible(true);
    }
    private void handleCreatePost() {
        // 글쓰기 창을 열어주는 로직을 추가
        CreatePost createPostFrame = new CreatePost(boardId, user);
        createPostFrame.setVisible(true);
        refresh();
    }
    public void refresh() {
    	loadPosts();
    }
}
