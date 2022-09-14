package graphs.impl;

import graphs.Dijkstra;
import graphs.Graph;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphImpl extends Graph {

    public GraphImpl() {
    }

    public void checkedReset() {
        setNodeSet(getNodeSet().stream().peek((item) -> item.setChecked(false)).collect(Collectors.toSet()));
    }

    public void resetDistanceAndPaths() {
        setNodeSet(getNodeSet().stream().peek((item) -> {
            item.setDistance(Integer.MAX_VALUE);
            item.setShortestPath(new LinkedHashSet<>());
        }).collect(Collectors.toSet()));
    }

    private Integer calculateDistance(Set<Path> paths) {
        return paths.stream().map((Path::getWeight)).reduce(0, Integer::sum);
    }

    public void showGraph(Node start) {
        Node minDistanceNode = new Node();
        for (Path path : start.getShortestPath()) {
            System.out.println(path.getFrom().getName() + "->" + path.getTo().getName());
        }

        for (Path path : start.getPaths()) {
            if (path.getWeight() < minDistanceNode.getDistance() && path.getTo().isNotChecked()) {
                minDistanceNode = path.getTo();
            }
        }
        start.setChecked(true);
        if (minDistanceNode.getName() != null) {
            showGraph(minDistanceNode);
        } else {
            checkedReset();
        }
    }

    @Override
    public Map<String, Map<String, Integer>> calculateAllShortagePaths() {
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        Dijkstra dijkstra = new DijkstraImpl();
        for(Node node : this.getNodeSet()) {
            Map<String, Integer> subResultMap = new HashMap<>();
            dijkstra.findShortagePath(node);
            this.getNodeSet().forEach((item) -> {
                subResultMap.put(item.getName(), item.getDistance());
            });
            resultMap.put(node.getName(), subResultMap);
            this.checkedReset();
            this.resetDistanceAndPaths();
        }
        return resultMap;
    }
}