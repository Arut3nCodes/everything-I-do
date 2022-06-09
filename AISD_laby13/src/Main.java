import DataStructures.Graph;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Graph gr = new Graph();

        System.out.println("Wybierz wersje testowania programu: ");
        System.out.println("1. Wczytanie z pliku");
        System.out.println("2. Wczytanie z konsoli");
        System.out.print("Twoj wybor: ");
        switch(sc.nextInt()) {
            case 1:{
                System.out.println("\nDANE 1\n");
                gr.loadFromFile("daneTestowe/da1.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                KruskalAlgorithm ka = new KruskalAlgorithm(gr);
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 2\n");
                gr.loadFromFile("daneTestowe/da2.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 3\n");
                gr.loadFromFile("daneTestowe/da3.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 4\n");
                gr.loadFromFile("daneTestowe/da4.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 5\n");
                gr.loadFromFile("daneTestowe/da5.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 6\n");
                gr.loadFromFile("daneTestowe/da6.txt");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\nDANE 7\n");
                gr.loadFromFile("daneTestowe/da7.txt");
                System.out.println("Tablica podobienstwa:\n");
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                System.out.println("\n\nTEST\n");
                System.out.println("Tablica podobienstwa:\n");
                gr.loadFromFile("daneTestowe/test.txt");
                gr.printOutAllNodesWithNeighbours();
                ka.run();
                ka.getConnectionsWithShortestDistance();

                break;
            }
            case 2:{
                gr.loadFromConsole();
                System.out.println("Tablica podobienstwa:\n");
                gr.printOutAllNodesWithNeighbours();
                KruskalAlgorithm ka = new KruskalAlgorithm(gr);
                ka.run();
                ka.getConnectionsWithShortestDistance();
            }
        }
    }
}
