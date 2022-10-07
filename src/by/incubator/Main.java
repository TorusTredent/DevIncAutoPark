package by.incubator;

import by.incubator.console.Writer;
import by.incubator.entity.Sorter;
import by.incubator.entity.Vehicle;
import by.incubator.entity.VehicleType;
import by.incubator.entity.enums.Color;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        VehicleType[] vehicleTypes = startLevel1();
        startLevel2(vehicleTypes);
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

    private static Vehicle[] startLevel2(VehicleType[] vehicleTypes) {
        Vehicle[] vehicles = initVehicleArray(vehicleTypes);
        Writer.printArray(vehicles);
        Sorter.sortingVehicles(vehicles);
        Writer.print("After sorting: ");
        Writer.printArray(vehicles);
        Vehicle vehicleWithMaxMileAge = findVehicleWithMaxMileAge(vehicles);
        Writer.print("Vehicle with max mileage: " + vehicleWithMaxMileAge);
        Vehicle vehicleWithMinMileAge = findVehicleWithMinMileAge(vehicles);
        Writer.print("Vehicle with min mileage: " + vehicleWithMinMileAge);
        return vehicles;
    }

    private static Vehicle[] initVehicleArray(VehicleType[] vehicleTypes) {
        return new Vehicle[] {
                new Vehicle(vehicleTypes[0], "Volkswagen Crafter", "5427 AX-7",
                        2022, 2015, 376000, Color.BLUE),
                new Vehicle(vehicleTypes[0], "Volkswagen Crafter", "62427 AA-7",
                        2500, 2014, 227010, Color.WHITE),
                new Vehicle(vehicleTypes[0], "Electric Bus E321", "6785 BA-7",
                        12080, 2019, 20451, Color.GREEN),
                new Vehicle(vehicleTypes[1], "Golf 5", "8682 AX-7",
                        1200, 2006, 230451, Color.GRAY),
                new Vehicle(vehicleTypes[1], "Tesla Model S 70D", "0001 AA-7",
                        2200, 2019, 10454, Color.WHITE),
                new Vehicle(vehicleTypes[2], "Hamm HD 12 VV", null,
                        3000, 2016, 122, Color.YELLOW),
                new Vehicle(vehicleTypes[3], "МТЗ Беларус-1025.4", "1145 AB-7",
                        1200, 2020, 109, Color.RED)
        };
    }

    private static Vehicle findVehicleWithMaxMileAge(Vehicle[] vehicles) {
        return Arrays.stream(vehicles)
                .max(Comparator.comparing(Vehicle::getMileage))
                .get();
    }

    private static Vehicle findVehicleWithMinMileAge(Vehicle[] vehicles) {
        return Arrays.stream(vehicles)
                .min(Comparator.comparing(Vehicle::getMileage))
                .get();
    }
}