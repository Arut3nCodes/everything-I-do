package dataStructures;

public class HuffmanTree {

    private Node root;

    public HuffmanTree(){
        root = null;
    }

    public HuffmanTree(Node root){
        setRoot(root);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

}
