package graphs.impl;

import graphs.Dijkstra;
import graphs.Graph;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GraphImpl extends Graph {

    private static final String RESULT_JSON_PATH = "C:\\Users\\n.veko\\IdeaProjects\\Deikstra\\deikstra\\src\\main\\java\\graphs\\result.json";
    private LinkedList<String> shortagePath = new LinkedList<>();

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
    public Result calculateAllShortagePaths() {
        Result resultMap = new Result();
        Dijkstra dijkstra = new DijkstraImpl();
        for (Node node : this.getNodeSet()) {
            Map<String, Integer> subResultMap = new HashMap<>();
            dijkstra.findShortagePath(node);
            this.getNodeSet().forEach((item) -> {
                subResultMap.put(item.getName(), item.getDistance());
            });
            resultMap.getResultMap().put(node.getName(), subResultMap);
            this.checkedReset();
            this.resetDistanceAndPaths();
        }
        return resultMap;
    }

    @Override
    public LinkedList<String> findShortagePath(List<String> parts, String current) throws IOException {
        ResultParser resultParser = new ResultParser();
        Result result = resultParser.getMapFromJson(new File(RESULT_JSON_PATH));
        Integer minPath = Integer.MAX_VALUE;
        String currentMinPath = null;
        System.out.println("CURRENT:" + current);
        for (String part : parts) {
            if(result.getResultMap().get(current).get(part) < minPath) {
                System.out.println(result.getResultMap().get(current).get(part) + "<" + minPath);
                minPath = result.getResultMap().get(current).get(part);
                currentMinPath = part;
            }
        }
        shortagePath.add(currentMinPath);
        parts.remove(currentMinPath);
        System.out.println("min:" + currentMinPath);
        if(parts.size() != 0) {
            return findShortagePath(parts, currentMinPath);
        }
        return shortagePath;
    }
}