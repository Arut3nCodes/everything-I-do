import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

public class LRU {
    Proces proc;
    int amountOfFrames;
    int amountOfErrors;
    double errorRate;

    public LRU(Proces proces, int iloscRamek, int version){ //0 without updating, 1 with updating
        int iloscBledow = 0;
        int recentNumOfFrames = iloscRamek;
        HashSet<Integer> uniqueCalls = new HashSet();
        this.proc = proces;
        this.amountOfFrames = iloscRamek;
        ArrayList<Page> physicalMemory = new ArrayList<>();
        int[] odwolania = proces.listaOdwolan;

        for(int i = 0; i < odwolania.length; i++){
            if(version == 1) {
                if (i % 50 == 0 && i != 0) {
                    recentNumOfFrames = uniqueCalls.size();
                    System.out.println(recentNumOfFrames);
                    uniqueCalls.clear();
                }
                uniqueCalls.add(odwolania[i]);
            }
            boolean contains = false;
            for(int j = 0; j < physicalMemory.size() && j < recentNumOfFrames; j++){
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
                if (physicalMemory.size() == recentNumOfFrames) physicalMemory.remove(0);
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

        this.amountOfErrors = iloscBledow;
        this.errorRate = iloscBledow * 1.0 / proc.iloscOdwolan();

    }
    public void printOut(){
        System.out.println("Dostepne ramki: " + amountOfFrames + " | Dla procesu o rozmiarze " + proc.roznorodnosc + " wystapilo " + amountOfErrors + " bledow");
    }
    class notUsedComparator implements Comparator<Page> {
        @Override
        public int compare(Page s1, Page s2) {
            return Integer.compare(s2.notUsed, s1.notUsed);
        }
    }
}
