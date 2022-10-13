package by.incubator.level6;

import by.incubator.level2.Vehicle;

import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getModelName().compareTo(o2.getModelName());
    }
}
