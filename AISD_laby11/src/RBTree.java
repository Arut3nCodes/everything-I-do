public class RBTree {
    RBNode root;
    public RBTree(){
        root = null;
    }

    public RBNode getRoot() {
        return root;
    }

    public void setRoot(RBNode root) {
        root.setColor('B');
        this.root = root;
    }

    private void rotateRight(RBNode node){
        RBNode parent = node.getUp();
        RBNode leftChild = node.getLeft();

        node.setLeft(leftChild.getRight());

        if(leftChild.getRight() != null){
            leftChild.getRight().setUp(node);
        }

        leftChild.setRight(node);
        node.setUp(leftChild);

        if (parent == null) {
            setRoot(leftChild);
        }
        else if (parent.getLeft() == node) {
            parent.setLeft(leftChild);
        }
        else if (parent.getRight() == node) {
            parent.setRight(leftChild);
        }
        else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (leftChild != null) {
            leftChild.setUp(parent);
        }
    }

    private void rotateLeft(RBNode node){
        RBNode parent = node.getUp();
        RBNode rightChild = node.getRight();
        node.setRight(rightChild.getLeft());

        if(rightChild.getLeft() != null){
            rightChild.getLeft().setUp(node);
        }

        rightChild.setLeft(node);
        node.setUp(rightChild);

        if (parent == null) {
            setRoot(rightChild);
        }
        else if (parent.getLeft() == node) {
            parent.setLeft(rightChild);
        }
        else if (parent.getRight() == node) {
            parent.setRight(rightChild);
        }
        else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (rightChild != null) {
            rightChild.setUp(parent);
        }
    }

    public RBNode searchNode(String key){
        RBNode node = getRoot();
        while (node != null) {
            if (key.equals(node.getKey()))
                return node;

            else if (key.compareTo(node.getKey()) < 0) // key < node.getKey()
                node = node.left;

            else
                node = node.right;
        }
        return null;
    }

    public void addNode(String wyraz, int linia){
        RBNode node = getRoot();
        RBNode parent = null;

        while(node != null){
            parent = node;
            if(wyraz.compareTo(node.getKey()) < 0)
                node = node.getLeft();
            else if(wyraz.compareTo(node.getKey()) > 0)
                node = node.getRight();
            else {
                node.addAppereance(linia);
                throw new IllegalArgumentException("BST already contains a node with key " + node.getKey());
            }
        }

        RBNode nNode= new RBNode(wyraz, linia);
        nNode.setColor('R');

        if(parent == null){
            setRoot(nNode);
        }
        else if(wyraz.compareTo(parent.getKey()) < 0){
            parent.setLeft(nNode);
        }
        else{
            parent.setRight(nNode);
        }
        nNode.setUp(parent);

        AddRBTreeFix(nNode);
    }

    private void AddRBTreeFix(RBNode node){
        RBNode parent = node.getUp();
        if(parent == null) { //rodzic jest null -> koniec rekurencji
                return;
        }

        if(parent.getColor() == 'B'){ //rodzic jest czarny -> koniec rekurencji
            return;
        }

        RBNode grandparent = parent.getUp();
        if(grandparent == null){
            parent.setColor('B');
            return;
        }

        RBNode uncle;
        RBNode grandparent1 = parent.getUp();

        if (grandparent1.getLeft() == parent)
            uncle = grandparent1.getRight();

        else if (grandparent1.getRight() == parent)
            uncle = grandparent1.getLeft();

        else
            throw new IllegalStateException("Parent is not a child of its grandparent");

        if(uncle != null && uncle.getColor() == 'R'){
            parent.setColor('B');
            parent.setColor('R');
            parent.setColor('B');

            AddRBTreeFix(grandparent);
        }

        else if (parent == grandparent.getLeft()){
            if(node == parent.getRight()){
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);

            parent.setColor('B');
            parent.setColor('R');
        }

        else{

            if(node == parent.getLeft()){
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);

            parent.setColor('B');
            parent.setColor('R');
        }


    }

    private void inorder(RBNode node)
    {
        if (node.getLeft() != null)
        {
            inorder(node.getLeft());
        }

        node.printOut();

        if (node.getRight() != null)
        {
            inorder(node.getRight());
        }
    }

    public void printOut(){
        inorder(getRoot());
    }


}
