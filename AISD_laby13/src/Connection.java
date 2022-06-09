import DataStructures.*;
import java.util.Objects;

public class Connection{
    Node node1;
    Node node2;
    int distance;

    public Connection(Node node1, Node node2, int distance){
        setNode1(node1);
        setNode2(node2);
        setDistance(distance);
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Objects.equals(node1, that.node1) && Objects.equals(node2, that.node2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2);
    }

    public String toString(){
        return getNode1().getId() + " " + getNode2().getId() + " " + getDistance();
    }
}
