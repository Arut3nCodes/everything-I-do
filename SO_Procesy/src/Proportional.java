public class Proportional {
    public Proportional(Proces[] processes, int availableFrames) {
    int overallSize = 0;
        for (Proces p : processes) {
            overallSize += p.roznorodnosc;
        }

        for (Proces p : processes) {
            int framePerProcess = (int)(((p.roznorodnosc * 1.0) / overallSize) * availableFrames);
            if(framePerProcess != 0){
                LRU l = new LRU(p, framePerProcess);
            }
            else
                System.out.println("Dostepne ramki: " + framePerProcess + " | Procesowi o rozmiarze " + p.roznorodnosc + " nie przydzielono żadnych ramek");
        }
    }
}
