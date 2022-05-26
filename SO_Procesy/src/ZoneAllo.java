public class ZoneAllo {
    public ZoneAllo(Proces[] processes,int availableFrames) {

        for (Proces p : processes) {
            int framePerProcess = 50;
            LRU l = new LRU(p, framePerProcess, 1);
            l.printOut();
        }
    }
}
