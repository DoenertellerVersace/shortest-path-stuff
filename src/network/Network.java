package network;

import com.fasterxml.jackson.databind.ObjectMapper;
import infrastructure.Station;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Network {

    public static final Graph<String, DefaultWeightedEdge> GRAPH = new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);
    private static final Network OBJ = new Network();

    private Network() {
        System.out.println("Network instanziiert!");
    }

    public static Network getInstance() {
        return OBJ;
    }

    public static void connect() {
        for (Station station : Station.STATIONS) {
            station.connectVertices();
        }
    }

    public static void readLine(String file) throws IOException {
        ObjectMapper oma = new ObjectMapper();
        Line line = oma.readValue(new File(file), Line.class);
        ArrayList<Vertex> vertexList = new ArrayList<>();
        String lineN = line.getName();
        for (Map<String, Object> stop : line.getRoute()) {
            String code = (String) stop.get("code");
            String name = (String) stop.get("name");
            Double position = (Double) stop.get("position");
            if (!GRAPH.containsVertex(code)) {
                Vertex e = new Vertex(code, name);
                vertexList.add(e);
                e.addPosition(lineN, position);
            } else {
                Vertex e = Vertex.byID(code);
                assert e != null;
                vertexList.add(e);
                e.addPosition(lineN, position);
            }
        }
        for (int i = 1; i < vertexList.size(); i++) {
            Vertex lastV = vertexList.get(i - 1);
            Vertex thisV = vertexList.get(i);
            checkForEdgeAndAdd(lineN, lastV, thisV);
        }
        for (int i = vertexList.size() - 2; i >= 0; i--) {
            Vertex lastV = vertexList.get(i + 1);
            Vertex thisV = vertexList.get(i);
            checkForEdgeAndAdd(lineN, lastV, thisV);
        }
    }

    private static void checkForEdgeAndAdd(String lineN, Vertex lastV, Vertex thisV) {
        double length = Math.abs(lastV.getPositions().get(lineN) - thisV.getPositions().get(lineN));
        if (!GRAPH.containsEdge(lastV.getId(), thisV.getId())) {
            new Edge(lastV.getId(), thisV.getId(), lineN).setLength(length);
        } else {
            Edge l = Edge.byEdge(GRAPH.getEdge(lastV.getId(), thisV.getId()));
            assert l != null;
            l.addRoute(lineN);
        }
    }
}
