public class Main {
    public static void main(String[] args) throws Exception {
            FileEncoder fe = new FileEncoder();
            fe.readFromFile();
            fe.countCharacters();
            fe.createHuffmanTree();
            //System.out.println(fe.getHtree().howManyLeaves(fe.getHtree().getRoot()));
            fe.printOutInformation();
            System.out.println(fe.encodeText());
    }
}
