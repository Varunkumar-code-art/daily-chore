import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> nums;
    private int lastZero;

    public ProductOfNumbers() {
        nums = new ArrayList<>();
        lastZero = -1;
    }

    public void add(int num) {
        if (num == 0) {
            lastZero = nums.size();
        }
        nums.add(num);
    }

    public int getProduct(int k) {
        int size = nums.size();
        if (lastZero >= size - k) {
            return 0;
        }
        int result = 1;
        for (int i = size - k; i < size; i++) {
            result *= nums.get(i);
        }
        return result;
    }
}