package by.incubator.console;

public class Writer {

    public static <T> void print(T text) {
        System.out.println(text);
    }

    public static <T> void printArray(T[] array) {
        for (T item : array) {
            print(item);
        }
    }
}
