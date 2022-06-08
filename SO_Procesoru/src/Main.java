public class Main {
    public static void main(String[] args){
        int numOfProc = 10; //ilosc procesorow - N
        int limOfInq = 4; //ilosc zapytan - z
        int maxThreshold = 70; // gorna granica uzycia - p
        int minThreshold = 20; // dolna granica uzycia - r

        Simulation sim = new Simulation(numOfProc, maxThreshold, limOfInq, minThreshold);
        int hello = 0;
        sim.run();
    }
}
