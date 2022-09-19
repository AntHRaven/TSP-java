package graphs;

import java.util.*;

public final class Node {

    private String name;
    private boolean checked = false;
    private List<Path> paths = new ArrayList<>();
    private LinkedHashSet<Path> shortestPath = new LinkedHashSet<>();
    private Integer distance = Integer.MAX_VALUE;
    private Graph graph;

    public Node() {
    }

    public Node(String name, Graph graph) {
        this.name = name;
        addToGraph(graph);
    }

    public Node(String name, boolean checked, LinkedHashSet<Path> shortestPath, Integer distance) {
        this.name = name;
        this.checked = checked;
        this.shortestPath = shortestPath;
        this.distance = distance;
    }

    private void addToGraph(Graph graph) {
        setGraph(graph);
        graph.getNodeSet().add(this);
    }

    public void addPath(Node to, Integer weight, boolean directed) {

        Path path = new Path(this, to, weight);
        List<Path> pathsFrom = this.getPaths();
        pathsFrom.add(path);
        this.setPaths(pathsFrom);

        if (!directed) {
            Path tmp = new Path(to, this, weight);
            List<Path> pathsTo = to.getPaths();
            pathsTo.add(tmp);
            to.setPaths(pathsTo);
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isNotChecked() {
        return !checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedHashSet<Path> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedHashSet<Path> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }
}