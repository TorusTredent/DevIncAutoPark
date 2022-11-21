package by.incubator.entity.sorter;

import by.incubator.entity.vehicle.Vehicle;

import java.util.Comparator;

public class ComparatorByTaxPerMonth implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return Double.compare(o1.getCalcTaxPerMonth(), o2.getCalcTaxPerMonth());
    }
}
