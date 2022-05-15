import dataStructures.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileEncoder {
    private String text;
    private ArrayList<Node> chList;
    private HuffmanTree htree;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Node> getChList() {
        return chList;
    }

    public void setChList(ArrayList<Node> chList) {
        this.chList = chList;
    }

    public HuffmanTree getHtree() {
        return htree;
    }

    public void setHtree(HuffmanTree htree) {
        this.htree = htree;
    }

    public FileEncoder(){
        htree = new HuffmanTree();
        chList = new ArrayList<>();
        text = "";
    }

    public void readFromFile() throws Exception{
        File file = new File("src/loadText.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String read;
        while ((read = br.readLine()) != null)
            text += read;
        System.out.println(text);

    }

    public void countCharacters() {
        for (int i = 0; i < text.length(); i++) {
            boolean check = false;
            for (Node n : chList) {
                if (n.getCharacter() == text.charAt(i)) {
                    n.setPriority(n.getPriority() + 1);
                    check = true;
                    break;
                }
            }
            if (check == false) {
                chList.add(new Node(text.charAt(i)));
            }
        }
    }

    public void createHuffmanTree(){
        PriorityQueue pq = new PriorityQueue();
        for(Node n: chList)
            pq.enqueue(n);
        // pq = printOutPQInformation(pq);

        while(pq.size() > 1){
            Node n = new Node();
            n.setLeft(pq.dequeue());
            n.setRight(pq.dequeue());
            n.setPriority(n.getLeft().getPriority() + n.getRight().getPriority());
            pq.enqueue(n);

            pq = printOutPQInformation(pq);
        }

        htree.setRoot(pq.dequeue());
    }

    String code(char c){
        StringBoolean sb = htree.getCode(c, htree.getRoot(), new StringBoolean("", false));
        return sb.getString();
    }

    public void printOutInformation(){
        for(Node node: chList){

            System.out.println(node.getCharacter() + " | "  + node.getPriority() + " | " + code(node.getCharacter()));
        }
    }

    public PriorityQueue printOutPQInformation(PriorityQueue pq){ //method to check if PQ works properly
        PriorityQueue pqs = new PriorityQueue();
        while(!pq.isEmpty()) {
            Node n = pq.dequeue();
            System.out.println(n.getPriority());
            pqs.enqueue(n);
        }
        System.out.println();
        return pqs;
    }

    public String encodeText(){
        String encoded = "";
        int length = getText().length();
        String txt = getText();
        for(int i = 0; i < length; i++){
            encoded += code(txt.charAt(i));
        }

        return encoded;
    }

}
