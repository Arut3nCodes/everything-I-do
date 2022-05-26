public class RBNode {
    RBNode up;
    RBNode left;
    RBNode right;
    String key;
    OneWayListWithHead<Integer> appearances;
    char color;

    RBNode(String word, int line){
        setUp(null);
        setRight(null);
        setLeft(null);
        setKey(word);
        this.appearances = new OneWayListWithHead<>();
        appearances.add(line);
    }

    public void addAppereance(int line){
        appearances.add(line);
    }

    public RBNode getUp() {
        return up;
    }

    public void setUp(RBNode up) {
        this.up = up;
    }

    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void printOut(){
        System.out.printf("%-30s", key);
        for(Integer i: appearances){
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
