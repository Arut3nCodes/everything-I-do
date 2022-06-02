import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    HashSet<Node> nodes;

    public Graph(){
        nodes = new HashSet<>();
    }

    void addNode(Node node){
        nodes.add(node);
    }

    void accesibleNodes(Node node, HashSet<Node> accesibleNodes){
        for(Node.NodeWithPath nws: node.neighbouringNodes){
            if(!accesibleNodes.contains(nws.getNode())){
                accesibleNodes.add(nws.getNode());
                accesibleNodes(nws.getNode(), accesibleNodes);
            }
        }
    }

    void accesibleCities(Node startingPoint){
        HashSet<Node> accesible = new HashSet<>();
        accesibleNodes(startingPoint, accesible);
        System.out.println("Osiagalne miasta z miasta " + startingPoint.nazwaMiasta + ": ");
        for(Node node: accesible){
            System.out.println(node.nazwaMiasta);
        }
    }

    void printOutAllNodesWithNeighbours(){
        for(Node node: nodes){
            System.out.println(node.toStringWithNeighbours());
        }
    }

    void printOutAllNodesWithShortestPaths(Node startingPoint){
        System.out.println("Najkrotsza droga z miasta " + startingPoint.nazwaMiasta + " do miast osiagalnych wyglada nastepujaco: ");
        for(Node node: nodes){
            if(node.distance != Integer.MAX_VALUE && node.distance != 0){
                System.out.println(node.toStringWithShortestPath());
            }
        }
    }
}
