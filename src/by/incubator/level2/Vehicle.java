package by.incubator.level2;

import by.incubator.level1.VehicleType;
import by.incubator.level2.enums.Color;
import by.incubator.level4.GasolineEngine;
import by.incubator.level4.Startable;

import java.util.Objects;

import static by.incubator.level3.TechnicalSpecialist.*;

public class Vehicle implements Comparable<Vehicle> {

    private VehicleType vehicleType;
    private String modelName;
    private String regNumber;
    private int weight;
    private int manufactureYear;
    private int mileage;
    private Color color;
    private double tankCapacity;
    private Startable startable;

    public Vehicle() {
    }

    public Vehicle(VehicleType vehicleType, String modelName, String regNumber, int weight,
                   int manufactureYear, int mileage, Color color) {
        setVehicleType(vehicleType);
        setModelName(modelName);
        setRegNumber(regNumber);
        setWeight(weight);
        setManufactureYear(manufactureYear);
        setMileage(mileage);
        setColor(color);
    }

    public Vehicle(VehicleType vehicleType, Startable startable, String modelName, String regNumber, int weight,
                   int manufactureYear, int mileage, Color color) {
        setVehicleType(vehicleType);
        this.startable = startable;
        setModelName(modelName);
        setRegNumber(regNumber);
        setWeight(weight);
        setManufactureYear(manufactureYear);
        setMileage(mileage);
        setColor(color);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        if (validateModelName(modelName)) {
            this.modelName = modelName;
        } else {
            this.modelName = "none";
        }
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        if (validateRegistrationNumber(regNumber)) {
            this.regNumber = regNumber;
        } else {
            this.regNumber = "none";
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        if (validateWeight(weight)) {
            this.weight = weight;
        } else {
            this.weight = 0;
        }
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        if (validateManufactureYear(manufactureYear)) {
            this.manufactureYear = manufactureYear;
        } else {
            this.manufactureYear = 0;
        }
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        if (validateMileage(mileage)) {
            this.mileage = mileage;
        } else {
            this.mileage = 0;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (validateColor(color)) {
            this.color = color;
        } else {
            this.color = null;
        }
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public Startable getStartable() {
        return startable;
    }

    public void setStartable(Startable startable) {
        this.startable = startable;
    }

    public double getCalcTaxPerMonth() {
        return (weight * 0.0013) + (startable.getTaxPerMonth() *
                vehicleType.getRoadTaxCoefficient() * 30) + 5;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        if (validateVehicleType(vehicleType)) {
            this.vehicleType = vehicleType;
        } else {
            this.vehicleType = new VehicleType();
        }
    }

    @Override
    public String toString() {
        return vehicleType.getString() + ", "
                + modelName + ", "
                + regNumber + ", "
                + regNumber + ", "
                + weight + ", "
                + manufactureYear + ", "
                + mileage + ", "
                + color + ", "
                + tankCapacity + ", '"
                + getCalcTaxPerMonth() + "', "
                + startable.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleType, vehicle.vehicleType) && Objects.equals(modelName, vehicle.modelName);
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
}
