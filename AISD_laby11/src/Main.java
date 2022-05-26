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
        try (BufferedReader br = new BufferedReader(new FileReader("src/doOdczytu.txt")))
        {
            String line;
            while((line = br.readLine())!=null)
            {
                System.out.println(line);
                String [] splitted = line.split("[.,?!--  ]");
                for (String s : splitted)
                {
                    RBNode node = rbt.searchNode(s.toLowerCase(Locale.ROOT));
                    System.out.println(s.toLowerCase(Locale.ROOT));
                    if ((s.length() == 1 && isGood(s) && node == null) || (s.length() > 1 && node == null))
                        rbt.addNode(s.toLowerCase(Locale.ROOT), currLine);

                    else if (node != null)
                        node.addAppereance(currLine);
                }
                currLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       rbt.printOut();
    }
}
