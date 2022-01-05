package pathfinder;

import lombok.Getter;
import network.Edge;

import java.util.ArrayList;

public class Leg {
    private final ArrayList<Edge> edges = new ArrayList<>();
    @Getter
    private final ArrayList<String> routes;
    @Getter
    private final boolean isFootpath;
    @Getter
    private double length;

    public Leg(Edge initialEdge) {
        edges.add(initialEdge);
        length = initialEdge.getLength();
        routes = initialEdge.getRoutes();
        isFootpath = initialEdge.isFootpath();
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
        length += edge.getLength();
    }

    @Override
    public String toString() {
        if (isFootpath) {
            return "Fußweg " + length + " Minuten";
        }
        return edges.size() + " Stationen, " +
                length + " Minuten mit Linie(n) " + routes;
    }
}
