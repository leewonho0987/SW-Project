package client.comment;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int c_ID;
    private int p_ID;
    private String c_U_ID;
    private String c_Detail;
    private String c_Date;

    // 생성자, 게터, 세터 등 필요한 메서드를 추가할 수 있습니다.

    
	public int getC_ID() {
        return c_ID;
    }

    public void setC_ID(int c_ID) {
        this.c_ID = c_ID;
    }

    public int getP_ID() {
        return p_ID;
    }

    public void setP_ID(int p_ID) {
        this.p_ID = p_ID;
    }

    public String getC_U_ID() {
        return c_U_ID;
    }

    public void setC_U_ID(String c_U_ID) {
        this.c_U_ID = c_U_ID;
    }

    public String getC_Detail() {
        return c_Detail;
    }

    public void setC_Detail(String c_Detail) {
        this.c_Detail = c_Detail;
    }

    public String getC_Date() {
        return c_Date;
    }

    public void setC_Date(String c_Date) {
        this.c_Date = c_Date;
    }
    public String toString() {
        return getC_U_ID()+"/"+getC_Date()+"/"+getC_Detail(); // 혹은 다른 필요한 필드를 반환하도록 수정
    }
}
