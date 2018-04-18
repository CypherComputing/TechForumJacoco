package za.co.mtax.tool.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum BatchSectionType {

    HEADER("HEADER", 1),
    RECORD("RECORD", 2),
    TRAILER("TRAILER", 3);

    private String name;
    private int value;
    private static Map map = new HashMap<>();

    BatchSectionType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    static {
        for (BatchSectionType batchSectionType : BatchSectionType.values()) {
            map.put(batchSectionType.value, batchSectionType);
        }
    }

    public static BatchSectionType valueOf(int batchSectionType) {
        return (BatchSectionType) map.get(batchSectionType);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
