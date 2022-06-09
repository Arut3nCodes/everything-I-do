package DataStructures;

import DataStructures.Node;

public class NodeWithPath {
    Node node;
    int distance;

    public NodeWithPath(Node elem, int dist){
        this.node = elem;
        this.distance = dist;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
