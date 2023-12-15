package client.Board;

public class Board {
    private int id;
    private String name;
    private String explanation;
    private String userId;
    private String date;

    public Board(int id, String name, String explanation, String userId, String date) {
        this.id = id;
        this.name = name;
        this.explanation = explanation;
        this.userId = userId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }
    public String toString() {
        return getName(); // 혹은 다른 필요한 필드를 반환하도록 수정
    }
}