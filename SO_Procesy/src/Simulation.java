public class Simulation {
    public Simulation(int numberOfProcesses) {
        Proces[] processes = new Proces[10];
        int length = 100000;
        int j = 2;

        for (int i = 0; i < processes.length; i++) {
            System.out.println(j);
            processes[i] = new Proces(length, j);
            j *= 2;
        }

        Equal eq = new Equal(processes, 1000);
        Proportional ppl = new Proportional(processes, 1000);
    }
}
