import java.util.HashSet;

public class Graph {
    HashSet<Connection> connections;

    public Graph(){
        connections = new HashSet<>();
    }

    public void addConnection(Connection connection){
        connections.add(connection);
    };

    public void loadConnectionsFromFile(){

    }
}
