public class Main {
    public static void main(String[] args){
        Graph graf = new Graph();
        DijkstraAlgorithm da = new DijkstraAlgorithm();
        Node node1 = new Node(0,"Wroclaw");
        Node node2 = new Node(1,"Olawa");
        Node node3 = new Node(2,"Brzeg");
        Node node4 = new Node(3,"Nysa");
        Node node5 = new Node(4,"Opole");

        node1.addNeighbour(node2, 10);
        node1.addNeighbour(node4, 30);
        node1.addNeighbour(node5, 100);
        node2.addNeighbour(node3, 50);
        node3.addNeighbour(node5, 10);
        node4.addNeighbour(node3, 20);
        node4.addNeighbour(node5, 60);

        graf.addNode(node1);
        graf.addNode(node2);
        graf.addNode(node3);
        graf.addNode(node4);
        graf.addNode(node5);

        graf.printOutAllNodesWithNeighbours();
        graf.accesibleCities(node1);

        graf = da. ShortestPathFromSource(graf, node1);
        graf.printOutAllNodesWithShortestPaths(node1);

    }
}
