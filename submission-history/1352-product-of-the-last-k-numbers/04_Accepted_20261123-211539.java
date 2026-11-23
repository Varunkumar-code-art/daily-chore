import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefix;
    private int lastZero;
    private int count;

    public ProductOfNumbers() {
        prefix = new ArrayList<>();
        prefix.add(1);
        lastZero = -1;
        count = 0;
    }

    public void add(int num) {
        if (num == 0) {
            lastZero = count;
            prefix.add(1);
        } else {
            prefix.add(prefix.get(prefix.size() - 1) * num);
        }
        count++;
    }

    public int getProduct(int k) {
        if (lastZero >= count - k) {
            return 0;
        }
        int end = prefix.get(prefix.size() - 1);
        int start = prefix.get(prefix.size() - 1 - k);
        return end / start;
    }
}