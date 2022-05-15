package dataStructures;

public class Node {
    private Node left;
    private Node right;
    private int priority;
    private Character character;

    public Node(){
        this.left = null;
        this.right = null;
        character = null;
    }
    public Node(char character) {
        this.left = null;
        this.right = null;
        this.priority = 1;
        this.character = character;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
