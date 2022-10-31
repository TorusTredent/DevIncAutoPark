package by.incubator.entity.sorter;

import by.incubator.entity.vehicle.Vehicle;

import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getModelName().compareTo(o2.getModelName());
    }
}
