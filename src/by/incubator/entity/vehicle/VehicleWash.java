package by.incubator.entity.vehicle;

import by.incubator.collection.MyArrayQueue;
import by.incubator.console.Writer;

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
