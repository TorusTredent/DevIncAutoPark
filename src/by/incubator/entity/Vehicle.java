package by.incubator.entity;

import by.incubator.entity.enums.Color;

import java.util.Objects;

public class Vehicle implements Comparable<Vehicle>{

    private VehicleType vehicleType;
    private String modelName;
    private String regNumber;
    private int weight;
    private int manufactureYear;
    private int mileage;
    private Color color;
    private double tankCapacity;

    public Vehicle() {
    }

    public Vehicle(VehicleType vehicleType, String modelName, String regNumber, int weight,
                   int manufactureYear, int mileage, Color color, double tankCapacity) {
        this.vehicleType = vehicleType;
        this.modelName = modelName;
        this.regNumber = regNumber;
        this.weight = weight;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.color = color;
        this.tankCapacity = tankCapacity;
    }

    public Vehicle(VehicleType vehicleType, String modelName, String regNumber, int weight, int manufactureYear, int mileage, Color color) {
        this.vehicleType = vehicleType;
        this.modelName = modelName;
        this.regNumber = regNumber;
        this.weight = weight;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.color = color;
    }

    public double getCalcTaxPerMonth() {
        return (weight * 0.0013) + (vehicleType.getRoadTaxCoefficient() * 30) + 5;
    }

    @Override
    public String toString() {
        return  vehicleType.getString() + ", "
                + modelName + ", "
                + regNumber + ", "
                + regNumber + ", "
                + weight + ", "
                + manufactureYear + ", "
                + mileage + ", "
                + color + ", "
                + tankCapacity + ", '"
                + getCalcTaxPerMonth() + "'";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return  Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(modelName, vehicle.modelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleType, modelName);
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.getManufactureYear() == o.manufactureYear) {
            return this.mileage - o.mileage;
        } else {
            return this.manufactureYear - o.manufactureYear;
        }
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }
}
