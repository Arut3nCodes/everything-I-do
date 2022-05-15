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

    public int howManyLeaves(Node node){
        int product = 0;
        if(node.getLeft() != null ||  node.getRight() != null){
            if (node.getLeft() != null) {
                product += howManyLeaves(node.getLeft());
            }
            if (node.getRight() != null) {
                product += howManyLeaves(node.getRight());
            }
        }
        else{
            System.out.print(node.getCharacter());
            product++;
        }
        return product;
    }

    public int howManyNodes(Node node){
        int product = 0;
        if (node.getLeft() != null) {
            product += howManyNodes(node.getLeft());
        }
        if (node.getRight() != null) {
            product += howManyNodes(node.getRight());
        }
        product++;
        return product;
    }


    public StringBoolean getCode(Character c, Node n, StringBoolean sb){
        if(n.getCharacter() != null && n.getCharacter() == c)
            sb.setBool(true);

        if( !sb.isBool() && n.getLeft() != null){
            sb = getCode(c, n.getLeft(), sb);

            if(n.getCharacter() != null){
                System.out.print(c);
            }

            if(sb.isBool()){
                sb.setString("0" + sb.getString());
            }
        }

        if(!sb.isBool() && n.getRight() != null){
            sb = getCode(c, n.getRight(), sb);
            if(sb.isBool()){
                sb.setString("1" + sb.getString());
            }
        }

        return sb;
    }

}
