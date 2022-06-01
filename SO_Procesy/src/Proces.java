import java.util.ArrayList;
import java.util.Random;

public class Proces {
    int[] listaOdwolan;
    int roznorodnosc;

    Proces(int dlugosc, int roznorodnoscStron){
        Random rand = new Random();
        this.roznorodnosc = roznorodnoscStron;
        this.listaOdwolan = new int[dlugosc];
        int odwolanie;
        int ostatnieOdwolanie = 0;
        int promienSasiedztwa = 6;

        //zachowujac zasade lokalnosci odwolan

        for(int i = 0; i < dlugosc; i++){
            if( i == 0 ) {
                odwolanie = rand.nextInt(roznorodnoscStron);
            }
            else{
                odwolanie = ostatnieOdwolanie - promienSasiedztwa + rand.nextInt(1 + promienSasiedztwa * 2);
            }
            listaOdwolan[i] = Math.max(0, Math.min(roznorodnosc - 1, odwolanie));
            ostatnieOdwolanie = listaOdwolan[i];
        }

        if(listaOdwolan.length <= 100){
            for(int i = 0; i < listaOdwolan.length; i++){
                System.out.print(listaOdwolan[i] + " ");
            }
            System.out.println();
        }

    }



    int iloscOdwolan(){
        return listaOdwolan.length;
    }



}
