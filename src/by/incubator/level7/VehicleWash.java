package by.incubator.level7;

import by.incubator.level2.Vehicle;
import by.incubator.level2.Writer;
import by.incubator.level6.VehicleCollection;

import java.util.List;

public class VehicleWash {

    private static MyArrayQueue<Vehicle> queue = new MyArrayQueue<>();

    public void checkIn(Vehicle vehicle) {
        queue.enqueue(vehicle);
    }

    public void wash() {
        Vehicle vehicle;
        try {
            vehicle = queue.dequeue();
            Writer.print("\n" + vehicle.getModelName() + " вымыто");
        } catch (IllegalArgumentException e) {
            Writer.printError(e.getMessage());
        }
    }
}
