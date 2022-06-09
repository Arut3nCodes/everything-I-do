import DataStructures.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class KruskalAlgorithm {
    ArrayList<Connection> connections;
    Graph graph;

    public KruskalAlgorithm(Graph graph) {
        this.graph = graph;
        connections = new ArrayList<>();
    }

    public void run(){
        connections.clear();
        ArrayList<Connection> connectionsToWorkOn = new ArrayList<>();
        ArrayList<Pair> connectionChecker = new ArrayList<>();

        for(Node n: graph.nodes){
            for(NodeWithPath nws: n.neighbouringNodes){
                boolean check = false;
                for(Pair para: connectionChecker){
                    check = para.equalsPair(n, nws.getNode());
                    if(check){
                        break;
                    }
                }
                if(!check){
                    connectionsToWorkOn.add(new Connection(n, nws.getNode(), nws.getDistance()));
                    connectionChecker.add(new Pair(n, nws.getNode()));
                }
            }
        }
        connectionsToWorkOn.sort(new ConnectionsDistanceComparator());

        ArrayList<HashSet<Node>> treeBuilder = new ArrayList<>();

        for(Node n: graph.nodes){
            HashSet<Node> ar = new HashSet<>();
            ar.add(n);
            treeBuilder.add(ar);
        }

        while(!connectionsToWorkOn.isEmpty()){
            Connection c = connectionsToWorkOn.get(0);

            int indexContainingNode1 = 0;
            int indexContainingNode2 = 0;

            for(HashSet<Node> hs: treeBuilder){
                if(hs.contains(c.getNode1())){
                    indexContainingNode1 = treeBuilder.indexOf(hs);
                }

                if(hs.contains(c.getNode2())){
                    indexContainingNode2 = treeBuilder.indexOf(hs);
                }
            }

            if(indexContainingNode1 != indexContainingNode2){
                connections.add(c);
                //merge process
                treeBuilder.get(indexContainingNode1).addAll(treeBuilder.get(indexContainingNode2));
                treeBuilder.remove(indexContainingNode2);
            }
            connectionsToWorkOn.remove(0);
        }
    }

    public void getConnectionsWithShortestDistance(){
        System.out.println("\nPolaczenia ostateczne:\n");
        int sumDist = 0;

        if(connections.isEmpty()){
            System.out.println("BRAK");
        }

        for(Connection c: connections){
            System.out.println(c.toString());
            sumDist += c.getDistance();
        }

        System.out.println("\nLaczna koncowa dlugosc kabla wynosi: " + sumDist + " cm");

    }

    private class ConnectionsDistanceComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection con1, Connection con2) {
            return Integer.compare(con1.getDistance(), con2.getDistance());
        }
    }

    private class Pair{
        Node node1;
        Node node2;

        public Pair(Node node1, Node node2){
            node1 = node1;
            node2 = node2;
        }

        public boolean equalsPair(Node node1, Node node2){
            if(this.node1 == node1 && this.node2 == node2 || this.node1 == node2 && this.node2 == node1){
                return true;
            }
            else return false;
        }
    }



}
