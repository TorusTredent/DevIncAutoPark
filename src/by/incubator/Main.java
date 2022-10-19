package by.incubator;

import by.incubator.level1.VehicleType;
import by.incubator.level2.Sorter;
import by.incubator.level2.Vehicle;
import by.incubator.level2.Writer;
import by.incubator.level2.enums.Color;
import by.incubator.level4.DieselEngine;
import by.incubator.level4.ElectricEngine;
import by.incubator.level4.GasolineEngine;
import by.incubator.level4.Startable;
import by.incubator.level6.Rent;
import by.incubator.level6.VehicleCollection;
import by.incubator.level6.VehicleComparator;
import by.incubator.level7.VehicleWash;
import by.incubator.level8.VehicleGarage;
import by.incubator.level9.DefectedVehicleException;
import by.incubator.level9.MechanicService;
import by.incubator.level9.Randomizer;
import sun.util.resources.LocaleData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Main {

    private static final Sorter sorter = new Sorter();

    public static void main(String[] args) {
        VehicleCollection vehicleCollection = startLevel6();
        fixVehicles(vehicleCollection.getVehicleList());
        Rent rent = rentVehicle(vehicleCollection.getVehicleList()
                .get(Randomizer.getRandomNumber(vehicleCollection.getVehicleList().size())));
    }


    private static Rent rentVehicle(Vehicle vehicle) {
        MechanicService mechanicService = new MechanicService();
        mechanicService.detectBreaking(vehicle);
        try {
            if (mechanicService.isBroken(vehicle)) {
                throw new DefectedVehicleException("The car has some breaks");
            }
            return new Rent(vehicle.getId(), new Date(), Randomizer.getRandomNumber(100));
        } catch (DefectedVehicleException e) {
            Writer.printError(e.getMessage());
        }
        return null;
    }

    private static void fixVehicles(List<Vehicle> vehicles) {
        MechanicService mechanicService = new MechanicService();
        int maxBreaks = 0;
        Vehicle vehicleWithMaxBreaks = null;
        for (Vehicle vehicle : vehicles) {
            Map<String, Integer> map = mechanicService.detectBreaking(vehicle);
            int breaksCount = getBreaksCount(map);
            if (breaksCount == 0) {
                Writer.print("Vehicle without breaks: " + vehicle);
            }
            if (maxBreaks < breaksCount) {
                maxBreaks = breaksCount;
                vehicleWithMaxBreaks = vehicle;
            }
            mechanicService.repair(vehicle);
        }
        Writer.print("Car with max number of breaks: " + vehicleWithMaxBreaks);
    }

    private static int getBreaksCount(Map<String, Integer> map) {
        if (map.isEmpty()) return 0;
        return map.values().stream()
                .mapToInt(i -> i)
                .sum();
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
        Writer.printArray(vehicles);
        sorter.sortingVehicles(vehicles);
        Writer.print("After sorting: ");
        Writer.printArray(vehicles);
        Vehicle vehicleWithMaxMileAge = findVehicleWithMaxMileAge(vehicles);
        Writer.print("Vehicle with max mileage: " + vehicleWithMaxMileAge);
        Vehicle vehicleWithMinMileAge = findVehicleWithMinMileAge(vehicles);
        Writer.print("Vehicle with min mileage: " + vehicleWithMinMileAge);
        return vehicles;
    }

    private static Vehicle[] initVehicleArray(VehicleType[] vehicleTypes) {
        return new Vehicle[]{
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

    private static Vehicle[] startLevel3(VehicleType[] vehicleTypes) {
        Vehicle[] vehicles = initVehicleArrayForLvl3(vehicleTypes);
        Writer.printArray(vehicles);
        printIdenticalVehicles(vehicles);
        Vehicle vehicle = findVehicleThatWillTravelTheMaxDistance(vehicles);
        Writer.print(vehicle);
        return vehicles;
    }

    private static Vehicle[] initVehicleArrayForLvl3(VehicleType[] vehicleTypes) {
        Startable gas = new GasolineEngine(2, 8.1, 75);
        return new Vehicle[]{
                new Vehicle(vehicleTypes[0], new GasolineEngine(2, 8.1, 75),
                        "Volkswagen Crafter", "5427 AX-7", 2022,
                        2015, 376000, Color.BLUE),
                new Vehicle(vehicleTypes[0], new GasolineEngine(2.18, 8.5, 75),
                        "Volkswagen Crafter", "6227 AA-7",
                        2500, 2014, 227010, Color.WHITE),
                new Vehicle(vehicleTypes[0], new ElectricEngine(50, 150),
                        "Electric Bus E321", "6785 BA-7",
                        12080, 2019, 20451, Color.GREEN),
                new Vehicle(vehicleTypes[1], new DieselEngine(1.6, 7.2, 55),
                        "Golf 5", "8682 AX-7",
                        1200, 2006, 230451, Color.GRAY),
                new Vehicle(vehicleTypes[1], new ElectricEngine(25, 70),
                        "Tesla Model S 70D", "0001 AA-7",
                        2200, 2019, 10454, Color.WHITE),
                new Vehicle(vehicleTypes[2], new DieselEngine(3.2, 25, 20),
                        "Hamm HD 12 VV", null,
                        3000, 2016, 122, Color.YELLOW),
                new Vehicle(vehicleTypes[3], new DieselEngine(4.75, 20.1, 135),
                        "МТЗ Беларус-1025.4", "1145 AB-7",
                        1200, 2020, 109, Color.RED)
        };
    }

    private static void printIdenticalVehicles(Vehicle[] vehicles) {
        for (int i = 0; i < vehicles.length; i++) {
            Vehicle vehicle = vehicles[i];
            for (int j = i + 1; j < vehicles.length; j++) {
                if (vehicle.equals(vehicles[j])) {
                    Writer.print(vehicle);
                    Writer.print(vehicles[i]);
                    break;
                }
            }
        }
    }

    private static Vehicle findVehicleThatWillTravelTheMaxDistance(Vehicle[] vehicles) {
        return Arrays.stream(vehicles)
                .max(Comparator.comparing(vehicle -> vehicle.getStartable().getMaxKilometers()))
                .orElse(null);
    }

    private static VehicleCollection startLevel6() {
        VehicleCollection vehicleCollection = new VehicleCollection("rents", "types", "vehicles");
        vehicleCollection.display();
        Vehicle vehicle = createNewVehicle();
        vehicleCollection.insert(6, vehicle);
        vehicleCollection.delete(1);
        vehicleCollection.delete(4);
        vehicleCollection.display();
        vehicleCollection.sort(new VehicleComparator());
        vehicleCollection.display();
        return vehicleCollection;
    }

    private static Vehicle createNewVehicle() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return new Vehicle(
                    8,
                    new VehicleType(
                            "Car",
                            1.4
                    ),
                    "Audi C5",
                    "7771 AB-3",
                    2000,
                    2019,
                    15500,
                    Color.RED,
                    new ElectricEngine(25, 70),
                    Arrays.asList(new Rent(
                                    format.parse("12.12.2019"),
                                    60),
                            new Rent(
                                    format.parse("29.03.2019"),
                                    90)));
        } catch (ParseException e) {
            Writer.printError("Wrong date format");
        }
        return null;
    }

    private static void startLevel7(VehicleCollection vehicleCollection) {
        VehicleWash vehicleWash = new VehicleWash();
        for (Vehicle vehicle : vehicleCollection.getVehicleList()) {
            vehicleWash.checkIn(vehicle);
        }
        vehicleCollection.getVehicleList().forEach(vehicle -> vehicleWash.wash());
    }

    private static void startLevel8(VehicleCollection vehicleCollection) {
        VehicleGarage vehicleGarage = new VehicleGarage();
        for (Vehicle vehicle : vehicleCollection.getVehicleList()) {
            vehicleGarage.checkIn(vehicle);
        }
        Writer.print("Гараж заполнен");
        vehicleCollection.getVehicleList().forEach(vehicle -> vehicleGarage.leave());
    }
}