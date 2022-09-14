package graphs;

import graphs.impl.Node;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Graph {

    private Set<Node> nodeSet = new HashSet<>();

    public abstract void checkedReset();

    public abstract void resetDistanceAndPaths();

    public abstract void showGraph(Node start);

    public abstract Map<String, Map<String, Integer>> calculateAllShortagePaths();

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<Node> nodeSet) {
        this.nodeSet = nodeSet;
    }
}
