package by.incubator.level7;

import by.incubator.entity.Vehicle;
import by.incubator.entity.Writer;

public class VehicleWash {

    private static MyArrayQueue<Vehicle> queue = new MyArrayQueue<>();

    public void checkIn(Vehicle vehicle) {
        queue.enqueue(vehicle);
    }

    public void wash() {
        Vehicle vehicle = queue.dequeue();
        Writer.print("\n" + vehicle.getModelName() + " вымыто");
    }
}
