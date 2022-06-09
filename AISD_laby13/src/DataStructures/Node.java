package DataStructures;

public class Node {
    private int id;
    public OneWayListWithHead<NodeWithPath> neighbouringNodes;

    public Node(int id){
        this.id = id;
        neighbouringNodes = new OneWayListWithHead<>();
    }

    public void addConnection(Node node, int distance){
        NodeWithPath nws = new NodeWithPath(node, distance);
        if(!neighbouringNodes.contains(nws)){
            neighbouringNodes.add(nws);
        }
    }

    public boolean hasConnection(Node node){
        for(NodeWithPath nws: neighbouringNodes){
            if(nws.getNode() == node){
                return true;
            }
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String toStringWithNeighbours(){
        String s = "";
        s += this.id + "{ ";
        for(NodeWithPath nwp: neighbouringNodes){
            s += "(" + nwp.getNode().id + ", " + nwp.getDistance() + ") ";
        }
        s += " }";
        return s;
    }
}
