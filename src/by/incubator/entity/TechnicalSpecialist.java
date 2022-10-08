package by.incubator.entity;

import by.incubator.entity.enums.Color;

public class TechnicalSpecialist {

    public static final int LOWER_LIMIT_MANUFACTURE_YEAR = 1886;

    public static boolean validateManufactureYear(int year) {
        return (year >= LOWER_LIMIT_MANUFACTURE_YEAR && year < 10000);
    }

    public static boolean validateMileage(int mileage) {
        return mileage >= 0;
    }

    public static boolean validateWeight(int weight) {
        return weight >= 0;
    }

    public static boolean validateColor(Color color) {
        return color != null;
    }

    public static boolean validateVehicleType(VehicleType type) {
        return (type!= null && !type.getName().isEmpty() &&
                type.getName() != null && type.getRoadTaxCoefficient() >= 0);
    }

    public static boolean validateRegistrationNumber(String number) {
        return (number != null && number.matches("\\d{4}\\s[A-Z]{2}(-)\\d"));
    }

    public static boolean validateModelName(String name) {
        return !name.isEmpty();
    }
}
