package DataStructures;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    public ArrayList<Node> nodes;

    public Graph(){
        nodes = new ArrayList<Node>();
    }

    public void addNodes(Node node){
        nodes.add(node);
    }

    public Node getNode(int id) {
        for(Node n: nodes){
            if(n.getId() == id){
                return n;
            }
        }
        return null;
    }

    public void loadFromFile(String name){
        nodes.clear();
        Path filePath = Paths.get(name);
        try {
            Scanner scanner = new Scanner(filePath);
            int numberOfNodes = scanner.nextInt();
            int numberOfConnections = scanner.nextInt();

            for(int i = 1; i <= numberOfNodes; i++){
                addNodes(new Node(i));
            }

            for(int i = 0; i < numberOfConnections; i++){
                int nodeId1 = scanner.nextInt();
                int nodeId2 = scanner.nextInt();
                int distance = scanner.nextInt();

                Node node1 = getNode(nodeId1);
                Node node2 = getNode(nodeId2);

                if(!node1.hasConnection(node2)){
                    node1.addConnection(node2, distance);
                }

                if(!node2.hasConnection(node1)){
                    node2.addConnection(node1, distance);
                }
            }

        } catch(IOException ioe) {System.out.println("No file with specified path was found");}
    }

    public void printOutAllNodesWithNeighbours(){
        for(Node node: nodes){
            System.out.println(node.toStringWithNeighbours());
        }
    }
}
