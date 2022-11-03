package by.incubator.entity.engine;

import by.incubator.entity.engine.CombustionEngine;

public class GasolineEngine extends CombustionEngine {

    public GasolineEngine(double engineCapacity, double fuelConsumptionPer100, double fuelTankCapacity) {
        super("Gasoline", 1.1, engineCapacity, fuelConsumptionPer100, fuelTankCapacity);
    }
}
