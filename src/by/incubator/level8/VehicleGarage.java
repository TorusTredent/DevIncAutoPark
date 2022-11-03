package by.incubator.level8;

import by.incubator.entity.Vehicle;
import by.incubator.entity.Writer;
public class VehicleGarage {

    private static MyArrayStack<Vehicle> stack = new MyArrayStack<>();

    public void checkIn(Vehicle vehicle) {
        stack.push(vehicle);
        Writer.print(vehicle.getModelName() + " заехало в гараж");
    }

    public void leave() {
        Vehicle vehicle;
        try {
            vehicle = stack.pop();
            Writer.print(vehicle.getModelName() + " выехало из гаража");
        } catch (IllegalArgumentException e) {
            Writer.printError(e.getMessage());
        }
    }
}
