import BasicForms.*;
import BasicForms.Process;
import Strategies.Strategy1;
import Strategies.Strategy2;
import Strategies.Strategy3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Simulation {
    ArrayList<Processor> processors;
    ArrayList<Process> tasks;
    int N;
    int p;
    int z;
    int r;

    public Simulation(int numberOfProcessors, int maximumThreshold, int limitOfInquiries,int minimumThreshold){
        Scanner sc = new Scanner(System.in);
        this.processors = new ArrayList<>();
        this.tasks = new ArrayList<>();
        N = numberOfProcessors;
        p = maximumThreshold;
        z = limitOfInquiries;
        r = minimumThreshold;

        for (int i = 0; i < N; i++)
            processors.add(new Processor());

        System.out.println("Podaj maksymalna ilosc procesow: ");
        int numOfProcesses = sc.nextInt();
        System.out.println("Podaj maksymalna moc procesu: ");
        int powOfProcesses = sc.nextInt();

        for (int i = 0; i < numOfProcesses; i++)
            tasks.add(new Process(powOfProcesses));
    }

    public void run(){
        ArrayList<Processor> proc1 = copy(processors);
        ArrayList<Process> tasks1 = copyo(tasks);

        Strategy1 str1 = new Strategy1(proc1, tasks1, N, p, z);

        fixFreq();
        ArrayList<Processor> proc2 = copy(processors);
        ArrayList<Process> tasks2 = copyo(tasks);


        Strategy2 str2 = new Strategy2(proc2, tasks2, N, p);

        ArrayList<Processor> proc3 = copy(processors);
        ArrayList<Process> tasks3 = copyo(tasks);

        Strategy3 str3 = new Strategy3(proc3, tasks3, N, p, r);
    }

    public ArrayList<Process> copyo(ArrayList<Process> copied) {
        ArrayList<Process> smth = new ArrayList<>();

        for(Process p: copied){
            smth.add(p);
        }

        return smth;
    }

    public ArrayList<Processor> copy(ArrayList<Processor> copied) {
        ArrayList<Processor> smth = new ArrayList<>();

        for(Processor p: copied){
            smth.add(p);
        }

        return smth;
    }

    public void fixFreq(){
        for(Process p: tasks){
            p.setFrequency();
        }
    }
}
