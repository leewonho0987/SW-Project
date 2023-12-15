package client.report;
import lombok.*;
@Getter
@Setter
public class Report {
    private int id;
    private int postId;
    private String userId;
    private String title;
    private String detail;
    private String date;
    private String moderatorId;
    private String state;

    public Report(int id, int postId, String userId, String title, String detail, String date, String moderatorId, String state) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.moderatorId = moderatorId;
        this.state = state;
    }
    @Override
    public String toString() {
        return userId+"/"+title+"/"+detail+"/"+date+"/"+state;
    }
    // Getter and Setter methods
    
}
