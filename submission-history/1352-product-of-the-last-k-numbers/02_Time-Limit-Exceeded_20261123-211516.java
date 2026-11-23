import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> nums;

    public ProductOfNumbers() {
        nums = new ArrayList<>();
    }

    public void add(int num) {
        nums.add(num);
    }

    public int getProduct(int k) {
        int result = 1;
        int size = nums.size();
        for (int i = size - 1; i >= size - k; i--) {
            if (nums.get(i) == 0) {
                return 0;
            }
            result *= nums.get(i);
        }
        return result;
    }
}