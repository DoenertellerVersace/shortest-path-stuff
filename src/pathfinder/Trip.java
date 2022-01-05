package pathfinder;

import lombok.Getter;
import network.Edge;

import java.util.ArrayList;

public class Trip {
    @Getter
    private ArrayList<Leg> trip = new ArrayList<>();
    @Getter
    private double length;
    private Leg thisLeg;

    public Trip(Edge initialEdge) {
        Leg leg = new Leg(initialEdge);
        trip.add(leg);
        thisLeg = leg;
        length = initialEdge.getLength();
    }

    public void addEdge(Edge edge) {
        if (edge.getRoutes().equals(thisLeg.getRoutes())) {
            thisLeg.addEdge(edge);
            length += edge.getLength();
        } else {
            Leg leg = new Leg(edge);
            trip.add(leg);
            thisLeg = leg;
            length += edge.getLength();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Leg leg : trip) {
            sb.append(leg).append("\n");
        }
        int changeCount = 0;
        for (Leg leg : trip) {
            if (!leg.isFootpath()) {
                changeCount++;
            }
        }
        changeCount--;
        sb.append(changeCount + " Mal umsteigen");
        return sb.toString();
    }
}
