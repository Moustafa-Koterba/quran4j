package quran4j.api.model;

import java.util.Arrays;

public enum Origin {
    MECCAN,
    MEDINAN;

    public static Origin fromString(String type) {
        return Arrays.stream(Origin.values())
                .filter(origin -> origin.toString().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The type passed as parameter " + type + " does not exist. It can be only meccan or medinan only"));
    }
}
