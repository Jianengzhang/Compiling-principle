package grammatical;

public class Grammatical {
    private int left;
    private String right;


    public Grammatical init(int left,String right){
        this.left = left;
        this.right = right;
        return this;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}
