package by.incubator.level2;

public class Writer {

    public<T> void print(T text) {
        System.out.println(text);
    }

    public<T> void printArray(T[] array) {
        for (T item : array) {
            print(item);
        }
    }
}
