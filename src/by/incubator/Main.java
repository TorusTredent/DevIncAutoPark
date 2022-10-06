package by.incubator;

import by.incubator.entity.VehicleType;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        VehicleType[] vehicleTypes = startLevel1();
    }


    private static VehicleType[] startLevel1() {
        VehicleType[] vehicleType = initVehicleTypeArray();
        displayVehicleTypes(vehicleType);
        vehicleType[vehicleType.length - 1].setRoadTaxCoefficient(1.3);
        double maxTax = getMaxTax(vehicleType);
        double allTax = getAllTax(vehicleType);
        double averageTax = allTax / vehicleType.length;
        combineAllTasksInCycle(vehicleType);
        return vehicleType;
    }

    private static void combineAllTasksInCycle(VehicleType[] vehicleType) {
        double maxTax = 0;
        double averageTax = 0;
        double allTax = 0;
        for (int i = 0; i < vehicleType.length; i++) {
            if (maxTax < vehicleType[i].getRoadTaxCoefficient()) {
                maxTax = vehicleType[i].getRoadTaxCoefficient();
            }
            allTax += vehicleType[i].getRoadTaxCoefficient();
            if (i == vehicleType.length - 1) {
                averageTax = allTax / vehicleType.length;
            }
            vehicleType[i].display();
        }
    }

    private static double getAllTax(VehicleType[] vehicleTypes) {
        return Arrays.stream(vehicleTypes)
                .mapToDouble(VehicleType::getRoadTaxCoefficient)
                .sum();
    }

    private static double getMaxTax(VehicleType[] vehicleTypes) {
        return Arrays.stream(vehicleTypes)
                .mapToDouble(VehicleType::getRoadTaxCoefficient)
                .filter(type -> type >= 0)
                .max()
                .orElse(0);
    }

    private static void displayVehicleTypes(VehicleType[] vehicleTypes) {
        for (VehicleType vehicleType : vehicleTypes) {
            vehicleType.display();
        }
    }

    private static VehicleType[] initVehicleTypeArray() {
        return new VehicleType[]{
                new VehicleType("Bus", 1.2),
                new VehicleType("Car", 1),
                new VehicleType("Rink", 1.5),
                new VehicleType("Tractor", 1.2),
        };
    }
}