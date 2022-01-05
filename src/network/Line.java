package network;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

public class Line {
    @Getter
    String type;
    @Getter
    String name;
    @Getter
    ArrayList<Map<String, Object>> route;
}
