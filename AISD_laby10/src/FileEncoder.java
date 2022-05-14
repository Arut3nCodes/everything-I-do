import dataStructures.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileEncoder {
    private String text;
    private HuffmanTree htree;

    public FileEncoder(){
        htree = new HuffmanTree();
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

    public void countCharacters(){

    }
}
