package baza;

import algorithms.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Simulation{
    ArrayList<Integer> data;

    public Simulation(){
        data = new ArrayList<>();
        generujCiagLiczb(2); //0 dla losowych | 1 dla posortowanych rosnąco | 2 dla posortowanych malejąco
        int[] arr1 = data.stream().mapToInt(i -> i).toArray();
        int[] arr2 = data.stream().mapToInt(i -> i).toArray();
        int[] arr3 = data.stream().mapToInt(i -> i).toArray();
        int[] arr4 = data.stream().mapToInt(i -> i).toArray();
        int[] arr5 = data.stream().mapToInt(i -> i).toArray();
        int[] arr6 = data.stream().mapToInt(i -> i).toArray();

        BubbleSort bs = new BubbleSort(arr1);
        InsertSort is = new InsertSort(arr2);
        SelectSort ss = new SelectSort(arr3);
        MergeSort ms = new MergeSort(arr4);
        QuickSort qs = new QuickSort(arr5);
        HeapSort hs = new HeapSort(arr6);

}
    public void generujCiagLiczb(int choice){
         Scanner sc = new Scanner(System.in);
         Random ran = new Random();
         System.out.println("Podaj ilosc probek");
         int ilosc = sc.nextInt();
        if(choice == 0){
            System.out.println("Podaj gorny limit probek");
            int gornyLimit = sc.nextInt();
            for (int i = 0; i < ilosc; i++) {
                data.add(ran.nextInt(gornyLimit + 1));
            }
            if (ilosc <= 100) System.out.println(data.toString());
            System.out.println("Wygenerowano ciag");
        }

        else if(choice == 1){
            for(int i = 0; i < ilosc; i++){
                data.add(i);
            }
        }

        else if(choice == 2){
            for(int i = ilosc; i > 0; i--){
                data.add(i);
            }
        }
    }
}
