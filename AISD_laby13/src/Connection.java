import java.util.Objects;

public class Connection{
    int node1;
    int node2;
    int distance;

    public Connection(int node1, int node2, int distance){
        setNode1(node1);
        setNode2(node2);
        setDistance(distance);
    }

    public int getNode1() {
        return node1;
    }

    public void setNode1(int node1) {
        this.node1 = node1;
    }

    public int getNode2() {
        return node2;
    }

    public void setNode2(int node2) {
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
        return node1 == that.node1 && node2 == that.node2 && distance == that.distance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node1, node2, distance);
    }
}
