package client.post;

import java.util.Date;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private int P_ID;
    private int B_ID;
    private String U_ID;
    private String Title;
    private String Detail;
    private String P_Date;
    private int Views;
    private int Best;
    private int Worst;
    private String State;
    private String Notice;
    private String Pin;

    @Override
    public String toString() {
        return "Post{" +
                "P_ID=" + P_ID +
                ", B_ID=" + B_ID +
                ", U_ID='" + U_ID + '\'' +
                ", Title='" + Title + '\'' +
                ", Detail='" + Detail + '\'' +
                ", P_Date='" + P_Date + '\'' +
                ", Views=" + Views +
                ", Best=" + Best +
                ", Worst=" + Worst +
                ", State='" + State + '\'' +
                ", Notice='" + Notice + '\'' +
                ", Pin='" + Pin + '\'' +
                '}';
    }
}
