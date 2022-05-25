import java.util.ArrayList;
import java.util.Random;

public class Proces {
    int[] listaOdwolan;
    int roznorodnosc;

    Proces(int dlugosc, int roznorodnoscStron){
        Random rand = new Random();
        this.roznorodnosc = roznorodnoscStron;
        this.listaOdwolan = new int[dlugosc];
        for(int i = 0; i < dlugosc; i++){
            listaOdwolan[i] = rand.nextInt(roznorodnoscStron);
        }
    }

    int iloscOdwolan(){
        return listaOdwolan.length;
    }



}
