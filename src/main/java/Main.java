import graphs.Dijkstra;
import graphs.Graph;
import graphs.impl.DijkstraImpl;
import graphs.impl.GraphImpl;
import graphs.Node;
import graphs.ResultParser;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra = new DijkstraImpl();
        Graph graph = new GraphImpl();
//        Node S = new Node("S", graph);

        Node A1 = new Node("A1", graph);
        Node A2 = new Node("A2", graph);
        Node A3 = new Node("A3", graph);
        Node A4 = new Node("A4", graph);

        Node B1 = new Node("B1", graph);
        Node B2 = new Node("B2", graph);
        Node B3 = new Node("B3", graph);
        Node B4 = new Node("B4", graph);

        Node C1 = new Node("C1", graph);
        Node C2 = new Node("C2", graph);
        Node C3 = new Node("C3", graph);
        Node C4 = new Node("C4", graph);

        Node D1 = new Node("D1", graph);
        Node D2 = new Node("D2", graph);
        Node D3 = new Node("D3", graph);
        Node D4 = new Node("D4", graph);

//        S.addPath(A1, 1, false);

        A1.addPath(A2, 1, false);
        A1.addPath(B1, 2, false);
        A1.addPath(B2, 3, false);

        A2.addPath(A3, 1, false);
        A2.addPath(B2, 2, false);
        A2.addPath(B1, 3, false);
        A2.addPath(B3, 3, false);

        A3.addPath(A4, 1, false);
        A3.addPath(B3, 2, false);
        A3.addPath(B2, 3, false);
        A3.addPath(B4, 3, false);

        A4.addPath(B3, 3, false);
        A4.addPath(B4, 2, false);

        B1.addPath(B2, 1, false);
        B2.addPath(B3, 1, false);
        B3.addPath(B4, 1, false);


        B1.addPath(C1, 5, false);
        B4.addPath(C4, 5, false);


        C1.addPath(C2, 1, false);
        C1.addPath(D1, 2, false);
        C1.addPath(D2, 3, false);

        C2.addPath(C3, 1, false);
        C2.addPath(D2, 2, false);
        C2.addPath(D1, 3, false);
        C2.addPath(D3, 3, false);

        C3.addPath(C4, 1, false);
        C3.addPath(D3, 2, false);
        C3.addPath(D2, 3, false);
        C3.addPath(D4, 3, false);

        C4.addPath(D3, 3, false);
        C4.addPath(D4, 2, false);

        D1.addPath(D2, 1, false);
        D2.addPath(D3, 1, false);
        D3.addPath(D4, 1, false);

        ResultParser resultParser = new ResultParser();
        resultParser.saveResultToJson(graph.calculateAllShortagePaths(), new File("C:\\Users\\n.veko\\IdeaProjects\\Deikstra\\deikstra\\src\\main\\java\\graphs\\result.json"));

    }

    static private void  showShortagePaths(Graph graph) {
        graph.getNodeSet().forEach((item) -> {
            System.out.println("\tITEM: " + item.getName() + "/" + item.getDistance());
            item.getShortestPath().forEach(i -> {
                System.out.println("\t\t" + i.getFrom().getName() + "->" + i.getTo().getName());
            });
        });
    }
}


