package by.incubator.level9;

import by.incubator.level2.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MechanicService implements Fixer{

    private static final String[] DETAILS = {"Фильтр", "Втулка", "Вал", "Ось", "Свеча", "Масло", "ГРМ", "ШРУС"};
    private static final int MAX_NUMBER_OF_BROKEN_DETAILS = 8;
    private static final int MAX_NUMBER_OF_BREAKS = 4;
    private static final String ORDERS_FILE_NAME = "orders.csv";

    @Override
    public Map<String, Integer> detectBreaking(Vehicle vehicle) {
        Map<String, Integer> map = new HashMap<>();
        int numberOfBrokenDetails = Randomizer.getRandomNumber(MAX_NUMBER_OF_BROKEN_DETAILS);
        for (int i = 0; i < numberOfBrokenDetails; i++) {
            setMapOfBrokenDetails(map);
        }
        if (!map.isEmpty()) MyFileWriter.printToFile(ORDERS_FILE_NAME, vehicle, map);
        return map;
    }

    @Override
    public boolean repair(Vehicle vehicle) {
        List<String> list = MyFileReader.readInfo(ORDERS_FILE_NAME);
        String regex = vehicle.getId() + ".*";
        list.removeIf(i -> i.matches(regex));
        MyFileWriter.writeListToFile(list, ORDERS_FILE_NAME);
        return true;
    }

    @Override
    public boolean isBroken(Vehicle vehicle) {
        return getLineFromOrderFile(vehicle) != null;
    }


    private String getLineFromOrderFile(Vehicle vehicle) {
        List<String> list = MyFileReader.readInfo(ORDERS_FILE_NAME);
        String regex = vehicle.getId() + ".*";

        return list.stream()
                .filter(x -> x.matches(regex))
                .findFirst().orElse(null);
    }

    private void setMapOfBrokenDetails(Map<String, Integer> map) {
        while(true) {
            String randomStringFromArray = Randomizer.getRandomStringFromArray(DETAILS);
            int numberOfBreaks = Randomizer.getRandomNumber(MAX_NUMBER_OF_BREAKS);
            Integer value = map.get(randomStringFromArray);
            if (value == null) {
                map.put(randomStringFromArray, numberOfBreaks);
                break;
            }
        }
    }
}
