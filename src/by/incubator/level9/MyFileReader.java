package by.incubator.level9;

import by.incubator.entity.Writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFileReader {

    public static List<String> readInfo(String fileName) {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String fileContent = null;
            while ((fileContent = bufferedReader.readLine()) != null) {
                list.add(fileContent);
            }
        } catch (IOException e) {
            Writer.printError(e.getMessage());
        }
        return list;
    }
}
