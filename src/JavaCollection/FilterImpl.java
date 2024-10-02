package JavaCollection;

public class FilterImpl implements Filter{
    @Override
    public Object apply(Object obj) {
        if (obj instanceof Integer) {
            return (Integer) obj * 2;  // Удвоим каждый элемент
        }
        return obj;
    }
}
