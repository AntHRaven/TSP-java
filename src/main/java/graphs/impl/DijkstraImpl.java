package graphs.impl;

import graphs.Dijkstra;
import graphs.Graph;
import graphs.Node;
import graphs.Path;

import java.util.LinkedHashSet;

public class DijkstraImpl extends Graph implements Dijkstra {

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
        } else if (getUncheckedNode(current.getGraph()) != null) {
            return findShortagePath(getUncheckedNode(current.getGraph()));
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
