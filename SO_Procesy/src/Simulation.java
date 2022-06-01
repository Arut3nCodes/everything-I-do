public class Simulation {
    public Simulation(int numberOfProcesses) {
        Proces[] processes = new Proces[numberOfProcesses];
        int length = 100000;
        int j = 2;

        System.out.println("Number of processes: " + numberOfProcesses + "\tPage calls' list length: " + length + '\n');
        for (int i = 0; i < processes.length; i++) {
            // System.out.println(j);
            processes[i] = new Proces(length, j);
            j *= 2;
        }

        System.out.println("EQUAL ALLOCATION: ");
        Equal eq = new Equal(processes, 500);
        System.out.println("\nPROPORTIONAL ALLOCATION: ");
        Proportional ppl = new Proportional(processes, 500);
        System.out.println("\nZONE ALLOCATION: ");
        ZoneAllo zl = new ZoneAllo(processes, 500);
        System.out.println("\nCONTROLLING PAGE FAULTS ALLOCATION: ");
        ControllingPageFaults cpf = new ControllingPageFaults(processes, 500);

    }
}
