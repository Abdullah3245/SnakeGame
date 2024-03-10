public class playerInfo {
    private String name;
    private int score;

    public playerInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public void setScore(int s) {
        this.score = s;
    }

    public void setName(String n) {
        this.name = n;
    }
   
}
