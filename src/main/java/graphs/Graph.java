package graphs;

import graphs.impl.DijkstraImpl;
import graphs.util.Result;
import graphs.util.ResultParser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    private static final String RESULT_JSON_PATH = "../result.json";
    private final LinkedList<String> shortagePath = new LinkedList<>();
    private Set<Node> nodeSet = new HashSet<>();

    public Graph() {
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

    public Result calculateAllShortagePaths() {
        Result resultMap = new Result();
        Dijkstra dijkstra = new DijkstraImpl();
        for (Node node : this.getNodeSet()) {
            Map<String, Integer> subResultMap = new HashMap<>();
            dijkstra.findShortagePath(node);
            this.getNodeSet().forEach((item) -> {
                subResultMap.put(item.getName(), item.getDistance());
            });
            Map<String, Integer> sortedSubResultMap = subResultMap
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            resultMap.getResultMap().put(node.getName(), sortedSubResultMap);

            this.checkedReset();
            this.resetDistanceAndPaths();
        }
        return resultMap;
    }

    public Node getUncheckedNode(Graph graph) {
        return graph.getNodeSet().stream().filter(i -> i.isNotChecked() && i.getDistance() != Integer.MAX_VALUE).findFirst().orElse(null);
    }

    public LinkedList<String> findShortagePath(List<String> parts, String current) throws IOException {
        Result result = new ResultParser().getMapFromJson(new File(RESULT_JSON_PATH));
        Integer minPath = Integer.MAX_VALUE;
        String currentMinPath = null;
        for (String part : parts) {
            if (result.getResultMap().get(current).get(part) < minPath) {
                minPath = result.getResultMap().get(current).get(part);
                currentMinPath = part;
            }
        }
        shortagePath.add(currentMinPath);
        parts.remove(currentMinPath);
        if (parts.size() != 0) {
            return findShortagePath(parts, currentMinPath);
        }
        return shortagePath;
    }

    public Set<Node> getNodeSet() {
        return nodeSet;
    }

    public void setNodeSet(Set<Node> nodeSet) {
        this.nodeSet = nodeSet;
    }
}