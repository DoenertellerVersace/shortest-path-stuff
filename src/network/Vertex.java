package network;

import infrastructure.Station;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vertex {
    private static final ArrayList<Vertex> VERTICES = new ArrayList<>();
    @Getter
    private final String id;
    @Getter
    private final String publicName;
    @Getter
    private final Map<String, Double> positions = new HashMap<>();

    public Vertex(String id, String publicName) {
        this.id = id;
        this.publicName = publicName;
        addVertex();
        VERTICES.add(this);
        if (Station.byID(id.split("#")[0]) == null) {
            new Station(this);
        } else {
            assert Station.byID(id.split("#")[0]) != null;
            Station.byID(id.split("#")[0]).addVertex(this);
        }
    }

    @Contract(pure = true)
    public static @Nullable Vertex byID(String query) {
        for (Vertex vertex : VERTICES) {
            if (vertex.id.equals(query)) {
                return vertex;
            }
        }
        return null;
    }

    public void addPosition(String line, double position) {
        positions.put(line, position);
    }

    private void addVertex() {
        Network.GRAPH.addVertex(id);
    }

    @Override
    public String toString() {
        return "Vertex{id='" + id + "'";
    }
}
