import java.util.Objects;

public class Node {

    int id;
    String nazwaMiasta;
    int distance;
    OneWayListWithHead <Node> shortestPath;
    OneWayListWithHead <NodeWithPath> neighbouringNodes;

    public class NodeWithPath {
        Node node;
        int path;

        NodeWithPath(Node node, int path){
            setNode(node);
            setPath(path);
        }
        public Node getNode() {
            return node;
        }

        public void setNode(Node node) {
            this.node = node;
        }

        public int getPath() {
            return path;
        }

        public void setPath(int path) {
            this.path = path;
        }
    }



    public Node(int id, String nazwaMiasta){
        shortestPath = new OneWayListWithHead<>();
        neighbouringNodes = new OneWayListWithHead<>();
        this.id = id;
        this.nazwaMiasta = nazwaMiasta;
        distance = Integer.MAX_VALUE;
    }

    void addNeighbour(Node node, int path){
        NodeWithPath nwp = new NodeWithPath(node, path);
        neighbouringNodes.add(nwp);
    }

    String toStringWithNeighbours(){
            String s = "";
            s += this.nazwaMiasta + "{ ";
            for(NodeWithPath nwp: neighbouringNodes){
                s += "(" + nwp.getNode().nazwaMiasta + ", " + nwp.getPath() + ") ";
            }
            s += " }";
            return s;
    }

    String toStringWithShortestPath(){
        String s = "";
        s += nazwaMiasta + " " + distance + " { ";
        for(Node n: shortestPath){
            s += n.nazwaMiasta + " ";
        }
        s += "}";
        return s;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id && Objects.equals(nazwaMiasta, node.nazwaMiasta) && Objects.equals(neighbouringNodes, node.neighbouringNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
