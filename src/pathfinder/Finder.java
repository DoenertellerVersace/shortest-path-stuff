package pathfinder;

import network.Edge;
import network.Network;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.IOException;
import java.util.List;

class Finder {
    public static final Graph<String, DefaultWeightedEdge> GRAPH = Network.GRAPH;

    public static void main(String[] args) throws IOException {
        Network.readLine("/Users/jakob/IdeaProjects/BVG/resources/lines/U1.json");
        Network.readLine("/Users/jakob/IdeaProjects/BVG/resources/lines/U2.json");
        Network.readLine("/Users/jakob/IdeaProjects/BVG/resources/lines/U3.json");
        Network.connect();
        System.out.println(getTrip("SEN", "WAR#U1.3"));
    }

    public static Trip getTrip(String start, String destination) {
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(GRAPH);
        ShortestPathAlgorithm.SingleSourcePaths<String, DefaultWeightedEdge> iPaths = dijkstraAlg.getPaths(start);
        List<DefaultWeightedEdge> route = iPaths.getPath(destination).getEdgeList();
        Trip trip = null;
        if (route.size() >= 1) {
            trip = new Trip(Edge.byEdge(route.get(0)));
            for (int i = 1; i < route.size(); i++) {
                trip.addEdge(Edge.byEdge(route.get(i)));
            }
        }
        return trip;
    }
}
