public class Equal {
    public Equal(Proces[] processes,int availableFrames){
        int framePerProcess = availableFrames/processes.length;

        for(Proces p: processes){
            LRU l = new LRU(p, framePerProcess);
        }
    }
}
