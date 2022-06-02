public class Main {
    public static void main(String[] args){
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

        da.addNode(node1);
        da.addNode(node2);
        da.addNode(node3);
        da.addNode(node4);
        da.addNode(node5);

        da.graph.printOutAllNodesWithNeighbours();
        da.graph.accesibleCities(node1);

        da.ShortestPathFromSource(node1);
        da.graph.printOutAllNodesWithShortestPaths(node1);

    }
}
