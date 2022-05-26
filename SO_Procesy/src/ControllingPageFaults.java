public class ControllingPageFaults {
    public ControllingPageFaults(Proces[] processes, int availableFrames){
        double upperPageFaultBound = 0.3;
        double bottomPageFaultBound = 0.03;
        for(Proces proc: processes){
            int zajeteRamki = 3;
            int wolneRamki = availableFrames - zajeteRamki;
            LRU lru = new LRU(proc, zajeteRamki, 0);
            while(lru.errorRate < bottomPageFaultBound || lru.errorRate > upperPageFaultBound){
                if(lru.errorRate < bottomPageFaultBound){
                    if(zajeteRamki > 2){
                        zajeteRamki--;
                        wolneRamki++;
                    }

                    else break;
                }

                else{
                    if(zajeteRamki != availableFrames){
                        zajeteRamki++;
                        wolneRamki--;
                    }
                    else break;
                }

                lru = new LRU(proc, zajeteRamki, 0);
            }

            lru.printOut();
        }
    }
}
