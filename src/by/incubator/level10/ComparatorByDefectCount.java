package by.incubator.level10;

import by.incubator.entity.Vehicle;
import by.incubator.level9.MechanicService;

import java.util.Comparator;

public class ComparatorByDefectCount implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return Integer.compare(MechanicService.getSumNumberOfBreaks(o2),
                MechanicService.getSumNumberOfBreaks(o1));
    }
}
