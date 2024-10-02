package JavaCollection;

public class Filtration {
    public static Object[] filter(Object[] array, Filter filter) {
        Object[] result = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = filter.apply(array[i]);
        }
        return result;
    }
}
