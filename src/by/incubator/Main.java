package by.incubator;

import by.incubator.level1.VehicleType;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        VehicleType[] vehicleTypes = startLevel1();
    }

    private static VehicleType[] startLevel1() {
        VehicleType[] vehicleType = initVehicleType();
        for (VehicleType type : vehicleType) {
            type.display();
        }
        vehicleType[vehicleType.length - 1].setRoadTaxCoefficient(1.3);
        double maxTax = Arrays.stream(vehicleType)
                .mapToDouble(VehicleType::getRoadTaxCoefficient)
                .filter(type -> type >= 0)
                .max()
                .orElse(0);
        double allTax = Arrays.stream(vehicleType)
                .mapToDouble(VehicleType::getRoadTaxCoefficient)
                .sum();
        double averageTax = allTax / vehicleType.length;
        maxTax = 0;
        averageTax = 0;
        allTax = 0;
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
        return vehicleType;
    }


    private static VehicleType[] initVehicleType() {
        return new VehicleType[]{
                new VehicleType("Bus", 1.2),
                new VehicleType("Car", 1),
                new VehicleType("Rink", 1.5),
                new VehicleType("Tractor", 1.2),
        };
    }
}