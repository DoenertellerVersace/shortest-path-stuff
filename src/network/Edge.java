package network;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;

public class Edge {
    public static final ArrayList<Edge> EDGES = new ArrayList<>();
    public final String id;
    public final DefaultWeightedEdge edge;
    @Getter
    private final Vertex start;
    @Getter
    private final Vertex end;
    @Getter
    private double length;
    @Getter
    private boolean isFootpath = false;
    @Getter
    private final ArrayList<String> routes = new ArrayList<>();

    public Edge(String v1, String v2, String route) {
        this.id = v1 + v2;
        this.routes.add(route);
        this.start = Vertex.byID(v1);
        this.end = Vertex.byID(v2);
        this.edge = Network.GRAPH.addEdge(v1, v2);
        EDGES.add(this);
    }

    public Edge(String v1, String v2) {
        this.id = v1 + v2;
        this.isFootpath = true;
        this.start = Vertex.byID(v1);
        this.end = Vertex.byID(v2);
        this.edge = Network.GRAPH.addEdge(v1, v2);
        EDGES.add(this);
    }

    public void addRoute(String route) {
        this.routes.add(route);
    }

    @Contract(pure = true)
    public static @Nullable Edge byEdge(DefaultWeightedEdge edge) {
        for (Edge e : EDGES) {
            if (e.edge.equals(edge)) {
                return e;
            }
        }
        return null;
    }

    public void setLength(double length) {
        this.length = length;
        Network.GRAPH.setEdgeWeight(edge, this.length);
    }
}
