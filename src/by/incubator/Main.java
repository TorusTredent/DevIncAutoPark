package by.incubator;

import by.incubator.level1.VehicleType;
import by.incubator.level2.Sorter;
import by.incubator.level2.Vehicle;
import by.incubator.level2.Writer;
import by.incubator.level2.enums.Color;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    private static final Writer writer = new Writer();
    private static final Sorter sorter = new Sorter();

    public static void main(String[] args) {
        VehicleType[] vehicleTypes = startLevel1();
        Vehicle[] vehicles = startLevel2(vehicleTypes);
        Vehicle[] vehiclesLevel3 = startLevel3(vehicleTypes);
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

    private static Vehicle[] startLevel2(VehicleType[] vehicleTypes) {
        Vehicle[] vehicles = initVehicleArray(vehicleTypes);
        writer.printArray(vehicles);
        sorter.sortingVehicles(vehicles);
        writer.print("After sorting: ");
        writer.printArray(vehicles);
        Vehicle vehicleWithMaxMileAge = findVehicleWithMaxMileAge(vehicles);
        writer.print("Vehicle with max mileage: " + vehicleWithMaxMileAge);
        Vehicle vehicleWithMinMileAge = findVehicleWithMinMileAge(vehicles);
        writer.print("Vehicle with min mileage: " + vehicleWithMinMileAge);
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