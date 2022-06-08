package Strategies;

import BasicForms.Process;
import BasicForms.Processor;

import java.util.ArrayList;

public class Strategy3 {
    public Strategy3(ArrayList<Processor> processors, ArrayList<Process> tasks, int numberOfProcessors, int maximumThreshold, int minimumThreshold){
//        int rozmiar = zad3.size();
//        int zapytania = 0, migracje = 0;
//        double sr_obciazenie = 0, odchylenie = 0;
//        ArrayList<Integer> srednia = new ArrayList<>();
//        ArrayList<Integer> odchyle = new ArrayList<>();
//
//        while (!zad3.isEmpty())
//        {
//            Random gen = new Random();
//            int los = gen.nextInt(N);
//            Procesor pr = proc3.get(los);
//            Zadanie akt = zad3.get(0);
//            int t = 0;
//
//            if (pr.obciazenie <= p)
//            {
//                if (pr.obciazenie + akt.udzial > 100)
//                    akt.increase(100 - pr.obciazenie);
//
//                pr.procesy.add(akt);
//                pr.obciazenie = pr.obciazenie+akt.udzial;
//                //zapytania++;
//            }
//            else
//            {
//                int los2 = gen.nextInt(N);
//                Procesor pr2 = proc3.get(los2);
//                zapytania++;
//
//                while (pr2.obciazenie > p)
//                {
//                    los2 = gen.nextInt(N);
//                    pr2 = proc3.get(los2);
//                    zapytania++;
//                    t++;
//                }
//
//                if (pr2.obciazenie + akt.udzial > 100)
//                {
//                    akt.increase(100 - pr2.obciazenie);
//                }
//                pr2.procesy.add(akt);
//                pr2.obciazenie = pr2.obciazenie+akt.udzial;
//                migracje++;
//            }
//            zad3.remove(0);
//            if (zad3.size()%(rozmiar/2000) == 0)
//            {
//                for (int i = 0; i < N; i++)
//                {
//                    zapytania++;
//                    Procesor pro = proc3.get(i);
//                    if (pro.obciazenie < r)
//                    {
//                        int los3 = gen.nextInt(N);
//                        Procesor pro2 = proc3.get(los3);
//                        if (pro2.obciazenie > p)
//                        {
//                            for (int j = 0; j < pro2.procesy.size()/3; j++)
//                            {
//                                Zadanie przen = pro2.procesy.get(j);
//                                if (pro.obciazenie<100)
//                                {
//                                    pro2.obciazenie = pro2.obciazenie - przen.udzial;
//
//                                    if (pro.obciazenie + przen.udzial > 100)
//                                        przen.increase(100 - pro.obciazenie);
//
//                                    pro2.procesy.remove(przen);
//                                    pro.procesy.add(przen);
//                                    pro.obciazenie = pro.obciazenie + przen.udzial;
//                                    migracje++;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            for (int i = 0; i < N; i++)
//            {
//                Procesor pro = proc3.get(i);
//                if (pro.procesy.size() != 0)
//                {
//                    for (Zadanie pom : pro.procesy)
//                    {
//                        pom.czas_wykonania = pom.czas_wykonania-t;
//                        pom.doTask();
//                        if (pom.isDone())
//                            pro.obciazenie -= pom.udzial;
//                    }
//                    pro.procesy.removeIf(zadanie -> zadanie.czas_wykonania <= 0);
//                }
//            }
//
//            if (zad3.size()%(rozmiar/5) == 0)
//            {
//                int suma = 0, sr, odchyl = 0;
//
//                for (Procesor procesor : proc3)
//                    suma += procesor.obciazenie;
//
//                sr = suma/N;
//                srednia.add(sr);
//
//                for (Procesor procesor : proc3)
//                    odchyl += Math.abs(procesor.obciazenie-sr);
//
//                odchyl = odchyl/N;
//                odchyle.add(odchyl);
//            }
//        }
//
//        for (Integer e : srednia)
//        {
//            sr_obciazenie += e;
//            //System.out.println(e);
//        }
//
//        sr_obciazenie = sr_obciazenie/srednia.size();
//
//        for (Integer e : odchyle)
//            odchylenie += e;
//
//        odchylenie = odchylenie/odchyle.size();
//        System.out.println("Algorytm 3:");
//        System.out.println("Srednie obciążenie: " + df2.format(sr_obciazenie) + "%");
//        System.out.println("Odchylenie: " + df2.format(odchylenie) + "%");
//        System.out.println("Zapytania: " + zapytania);
//        System.out.println("Migracje: " + migracje + "\n");
    }
}
