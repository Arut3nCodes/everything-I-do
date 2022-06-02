import java.util.HashSet;

public class DijkstraAlgorithm {
        public Graph ShortestPathFromSource(Graph graph, Node source) {
            source.distance = 0;

            HashSet<Node> setNodes = new HashSet<>();
            HashSet<Node> unsetNodes = new HashSet<>();

            unsetNodes.add(source);

            while (!unsetNodes.isEmpty()) {
                Node currNode = getLowestDistanceNode(unsetNodes);
                unsetNodes.remove(currNode);
                for (Node.NodeWithPath nwp : currNode.neighbouringNodes) {
                    Node neighbourNode = nwp.getNode();
                    int pathLength = nwp.getPath();
                    if (!setNodes.contains(neighbourNode)) {
                        calcMinDist(currNode, neighbourNode, pathLength);
                        unsetNodes.add(neighbourNode);
                    }
                }
                setNodes.add(currNode);
            }
            return graph;
        }

    private Node getLowestDistanceNode(HashSet<Node> unsetNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node: unsetNodes) {
            int nodeDistance = node.distance;
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void calcMinDist(Node sourceNode, Node evaluatedNode, int pathLength) {
        int sourceDistance = sourceNode.distance;
        if (sourceDistance + pathLength < evaluatedNode.distance) {
            evaluatedNode.distance = sourceDistance + pathLength;
            OneWayListWithHead<Node> shortestPath = new OneWayListWithHead<>(sourceNode.shortestPath);
            shortestPath.add(sourceNode);
            evaluatedNode.shortestPath = shortestPath;
        }
    }
}
