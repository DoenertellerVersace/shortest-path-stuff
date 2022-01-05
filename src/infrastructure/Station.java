package infrastructure;

import lombok.Getter;
import lombok.Setter;
import network.Edge;
import network.Vertex;

import java.util.ArrayList;


public class Station {
    public static final ArrayList<Station> STATIONS = new ArrayList<>();
    public static final int DEFAULT_CONNECTING_LENGTH = 3;
    @Getter
    private final String id;
    @Getter
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    @Getter
    private final ArrayList<Edge> connectingEdges = new ArrayList<>();
    @Getter
    @Setter
    private String name;

    public Station(Vertex initialVertex) {
        this.id = initialVertex.getId().split("#")[0];
        this.name = initialVertex.getPublicName();
        vertices.add(initialVertex);
        STATIONS.add(this);
    }

    public static Station byID(String query) {
        for (Station station : STATIONS) {
            if (station.id.equals(query)) {
                return station;
            }
        }
        return null;
    }

    public void connectVertices() {
        for (Vertex v1 : vertices) {
            for (Vertex v2 : vertices) {
                if (!v2.equals(v1)) {
                    Edge edge = new Edge(v1.getId(), v2.getId());
                    edge.setLength(DEFAULT_CONNECTING_LENGTH);
                    connectingEdges.add(edge);
                }
            }
        }
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    @Override
    public String toString() {
        return "Station{" +
                "id='" + id + '\'' +
                ", vertices=" + vertices +
                ", name='" + name + '\'' +
                '}';
    }
}
