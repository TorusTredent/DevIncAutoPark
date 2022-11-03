package by.incubator.level9;

import by.incubator.entity.Vehicle;

import java.util.Map;

public interface Fixer {

    Map<String, Integer> detectBreaking(Vehicle vehicle);
    boolean repair(Vehicle vehicle);
    boolean isBroken(Vehicle vehicle);
    default boolean detectAndRepair(Vehicle vehicle) {
        detectBreaking(vehicle);
        if (isBroken(vehicle)) {
            repair(vehicle);
            return true;
        }
        return false;
    }
}
