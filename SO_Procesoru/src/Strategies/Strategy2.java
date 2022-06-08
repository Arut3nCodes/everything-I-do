package Strategies;

import BasicForms.Process;
import BasicForms.Processor;

import java.util.ArrayList;
import java.util.Random;

public class Strategy2 {
    public Strategy2(ArrayList<Processor> processors, ArrayList<Process> tasks, int numberOfProcessors, int maximumThreshold){
        int size = tasks.size();
        int inqueries = 0;
        int migrations = 0;
        double averageLoad = 0;
        double finalDeviation = 0;
        ArrayList<Integer> average = new ArrayList<>();
        ArrayList<Integer> deviation = new ArrayList<>();

        while (!tasks.isEmpty())
        {
            Random generator = new Random();
            int los = generator.nextInt(numberOfProcessors);
            Processor pr = processors.get(los);
            Process akt = tasks.get(0);
            int t = 0;

            if (pr.load() <= maximumThreshold)
            {
                if (pr.load() + akt.getLoad() > 100)
                    akt.increase(100 - pr.load());

                pr.addProcess(akt);
            }
            else
            {
                int los2 = generator.nextInt(numberOfProcessors);
                Processor pr2 = processors.get(los2);
                inqueries++;

                while (pr2.load() > maximumThreshold)
                {
                    los2 = generator.nextInt(numberOfProcessors);
                    pr2 = processors.get(los2);
                    inqueries++;
                    t++;
                }

                migrations++;
                if (pr2.load() + akt.getLoad() > 100)
                    akt.increase(100 - pr2.load());
                    pr2.addProcess(akt);
            }
            tasks.remove(0);

            for (int i = 0; i < numberOfProcessors; i++)
            {
                Processor pro = processors.get(i);
                if (pro.ongoingProcesses.size() != 0)
                {
                    for (Process pom : pro.ongoingProcesses)
                    {
                        pom.setFrequency(pom.getFrequency() - t);
                        pom.doTask();
                    }
                    pro.ongoingProcesses.removeIf(zadanie -> zadanie.getFrequency() <= 0);
                }
            }

            if (tasks.size() % (size/5) == 0)
            {
                int suma = 0, sr, odchyl = 0;
                for (Processor processor : processors)
                    suma += processor.load();

                sr = suma / numberOfProcessors;
                average.add(sr);

                for (Processor processor : processors)
                    odchyl += Math.abs(processor.load() - sr);

                odchyl = odchyl / numberOfProcessors;
                deviation.add(odchyl);
            }
        }

        for (Integer e : average)
            averageLoad += e;

        averageLoad = averageLoad/average.size();

        for (Integer e : deviation)
            finalDeviation += e;

        finalDeviation = finalDeviation / deviation.size();
        System.out.println("Strategy 2:");
        System.out.println("Average load: " + averageLoad);
        System.out.println("Deviation: " + finalDeviation + "%");
        System.out.println("Inqueries: " + inqueries);
        System.out.println("Migrations: " + migrations + "\n");
    }
}
