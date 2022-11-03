package by.incubator.entity.vehicle.garage;

import by.incubator.console.Writer;
import by.incubator.collection.MyArrayStack;
import by.incubator.entity.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class VehicleGarageStream {

    private static MyArrayStack<Vehicle> stack = new MyArrayStack<>();

    public static void checkIn(List<Vehicle> vehicles) {
        vehicles.forEach(vehicle -> {
            stack.push(vehicle);
            Writer.print("Auto " + vehicle.getId() + " заехало в гараж");
        });
    }

    public static void leave() {
        int size = stack.size();
        List<Vehicle> vehicles = new ArrayList<>();
        IntStream.range(0, size).mapToObj(i -> stack.pop()).forEach(vehicle -> {
            vehicles.add(vehicle);
            Writer.print("Auto " + vehicle.getModelName() + " выехало в гараж");
        });
    }
}
