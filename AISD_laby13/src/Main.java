import DataStructures.Graph;

public class Main {
    public static void main(String[] args){
        Graph gr = new Graph();
        System.out.println("DANE 1\n");
        gr.loadFromFile("daneTestowe/da1.txt");
        //gr.printOutAllNodesWithNeighbours();
        KruskalAlgorithm ka = new KruskalAlgorithm(gr);
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 2\n");
        gr.loadFromFile("daneTestowe/da2.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 3\n");
        gr.loadFromFile("daneTestowe/da3.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 4\n");
        gr.loadFromFile("daneTestowe/da4.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 5\n");
        gr.loadFromFile("daneTestowe/da5.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 6\n");
        gr.loadFromFile("daneTestowe/da6.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();

        System.out.println("\nDANE 7\n");
        gr.loadFromFile("daneTestowe/da7.txt");
        //gr.printOutAllNodesWithNeighbours();
        ka.run();
        ka.getConnectionsWithShortestDistance();
    }
}
