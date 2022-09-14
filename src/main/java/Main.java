import graphs.Dijkstra;
import graphs.Graph;
import graphs.impl.DijkstraImpl;
import graphs.impl.GraphImpl;
import graphs.impl.Node;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Dijkstra dijkstra = new DijkstraImpl();
        Graph graph = new GraphImpl();
        Node S = new Node("S", graph);

        Node A1 = new Node("A1", graph);
        Node A2 = new Node("A2", graph);
        Node A3 = new Node("A3", graph);
        Node A4 = new Node("A4", graph);
        Node B1 = new Node("B1", graph);
        Node B2 = new Node("B2", graph);
        Node B3 = new Node("B3", graph);
        Node B4 = new Node("B4", graph);

        S.addPath(A1, 1, false);

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

//        dijkstra.findShortagePath(A1);


        for(Node node : graph.getNodeSet()) {
            System.out.println(node.getName());
            dijkstra.findShortagePath(node);
            showShortagePaths(graph);
            graph.checkedReset();
            graph.resetDistanceAndPaths();
        }

//        graph.addNode(S, A1, 1);
//        graph.findShortagePath(A2, D3);
//                 0  1  2  3  4  5  6  7  8  9  10 11
//              0  0, 2, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0,
//              1  2, 0, 3, 1, 0, 0, 5, 0, 0, 0, 0, 0,
//              2  1, 3, 0, 2, 1, 3, 0, 0, 0, 0, 0, 0,
//              3  3, 1, 2, 0, 3, 1, 0, 0, 0, 0, 0, 0,
//              4  0, 0, 1, 3, 0, 2, 0, 0, 0, 0, 0, 0,
//              5  0, 0, 3, 1, 2, 0, 0, 0, 0, 0, 5, 0,
//              6  0, 5, 0, 0, 0, 0, 0, 2, 1, 3, 0, 0,
//              7  0, 0, 0, 0, 0, 0, 2, 0, 3, 1, 0, 0,
//              8  0, 0, 0, 0, 0, 0, 1, 3, 0, 2, 1, 3,
//              9  0, 0, 0, 0, 0, 0, 3, 1, 2, 0, 3, 1,
//             10  0, 0, 0, 0, 0, 5, 0, 0, 1, 3, 0, 2,
//             11  0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 2, 0,

//        Integer[][] graphArray = new Integer[12][12];
//
//        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\n.veko\\IdeaProjects\\Deikstra\\deikstra\\src\\main\\java\\graph.txt"));
//
//        List<String> lines = new ArrayList<>();
//        while (br.ready()) {
//            lines.add(br.readLine());
//        }
//
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                String[] line = lines.get(i).split(" ");
//                graphArray[i][j] = Integer.parseInt(line[j]);
//            }
//        }
//
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                if(graphArray[i][j] != 0) {
//                    graph.addNode(i + "",j + "", graphArray[i][j]);
//                }
//                System.out.print(graphArray[i][j]);
//            }
//            System.out.println();
//        }
//        graph.nodeSet.forEach(i -> {
//            System.out.println(i.getName());
//            for(impl.Path path : i.getPaths()) {
//                System.out.println("\t" + path.getFrom().getName() + "->" + path.getTo().getName());
//            }
//        });
//
//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 12; j++) {
//                System.out.print(graphArray[i][j]);
//            }
//            System.out.println();
//        }
//

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



