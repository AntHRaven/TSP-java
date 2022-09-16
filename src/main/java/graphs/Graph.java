package graphs;

import graphs.impl.Node;
import graphs.impl.Result;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class Graph {

    private Set<Node> nodeSet = new HashSet<>();

    public abstract void checkedReset();

    public abstract void resetDistanceAndPaths();

    public abstract void showGraph(Node start);

    public abstract Result calculateAllShortagePaths();

    public abstract LinkedList<String> findShortagePath(List<String> parts, String current) throws IOException;

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<Node> nodeSet) {
        this.nodeSet = nodeSet;
    }
}
