import java.io.*;
import java.util.Locale;

public class Main {

    public static boolean isGood (String a)
    {
        if (a.length()==1)
        {
            char a1 = a.charAt(0);
            return Character.isLetter(a1) || Character.isDigit(a1);
        }
        return false;
    }

    public static void main(String [] args){
        RBTree rbt = new RBTree();
        int currLine = 1;
        try (BufferedReader br = new BufferedReader(new FileReader("src/aforyzm.txt")))
        {
            String line;
            while((line = br.readLine())!=null)
            {
                String [] splitted = line.split("[.,?!--  ]");
                for (String s : splitted)
                {
                    RBNode node = rbt.searchNode(s);
                    if ((s.length() == 1 && isGood(s) && node == null) || (s.length() > 1 && node == null))
                        rbt.addNode(s, currLine);

                    else if (node != null)
                        node.addAppereance(currLine);

                   // rbt.printOutSideways();
                }
                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        rbt.printOut();
        System.out.println();
        rbt.printOutSideways();
        rbt.deleteNode("rodziny");
        System.out.println();
        rbt.printOut();
    }
}
