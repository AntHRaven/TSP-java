package graphs.impl;

import graphs.Dijkstra;

import java.util.LinkedHashSet;

public class DijkstraImpl extends GraphImpl implements Dijkstra {

    public Node findShortagePath(Node current) {
        for (Path path : current.getPaths()) {
            if (path.getWeight() + current.getDistance() < path.getTo().getDistance()) {
                if (current.getDistance() == Integer.MAX_VALUE) {
                    current.setDistance(0);
                }
                path.getTo().setDistance(path.getWeight() + current.getDistance());
                LinkedHashSet<Path> tmp = new LinkedHashSet<>(current.getShortestPath());
                tmp.add(path);
                path.getTo().setShortestPath(tmp);
            }
        }

        Node minNodePath = findMinimalDistanceUncheckedNode(current);
        current.setChecked(true);
        if (minNodePath.getName() != null) {
            return findShortagePath(minNodePath);
        }
        return current;
    }

    private Node findMinimalDistanceUncheckedNode(Node node) {
        Path minPath = new Path();
        minPath.setWeight(Integer.MAX_VALUE);
        minPath.setTo(new Node());
        for (Path path : node.getPaths()) {
            if (path.getTo().isNotChecked() && path.getWeight() < minPath.getWeight()) {
                minPath = path;
            }
        }
        return minPath.getTo();
    }


}