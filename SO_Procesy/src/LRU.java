import java.util.ArrayList;
import java.util.Comparator;

public class LRU {

    public LRU(Proces proces, int iloscRamek){
        int iloscBledow = 0;
        ArrayList<Page> physicalMemory = new ArrayList<>();
        int[] odwolania = proces.listaOdwolan;

        for(int i = 0; i < odwolania.length; i++){
            boolean contains = false;
            for(int j = 0; j < physicalMemory.size() && j < iloscRamek; j++){
                if(physicalMemory.get(j).id == odwolania[i]){
                    contains = true;
                    physicalMemory.get(j).notUsed = 0;
                }
                else{
                    physicalMemory.get(j).notUsed++;
                }
            }

            physicalMemory.sort(new notUsedComparator());

            if(!contains){ //contains == false
                iloscBledow++;
                if (physicalMemory.size() == iloscRamek) physicalMemory.remove(0);
                physicalMemory.add(new Page(odwolania[i]));
            }
//            System.out.print(odwolania[i] + " ["); // na tekst
//
//            for(Page s: physicalMemory){
//                System.out.print("( " + s.id + ", " + s.notUsed + "), ");
//                s.notUsed++;
//            }
//
//            System.out.println("]");

        }

        System.out.println("Dostepne ramki: " + iloscRamek + " | Dla procesu o " + proces.roznorodnosc + " wystapilo " + iloscBledow + " bledow");
    }


    class notUsedComparator implements Comparator<Page> {
        @Override
        public int compare(Page s1, Page s2) {
            return Integer.compare(s2.notUsed, s1.notUsed);
        }
    }
}
