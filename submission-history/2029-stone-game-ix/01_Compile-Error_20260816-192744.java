import java.util.*;

public class StoneGameIX {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int s : stones) {
            cnt[s % 3]++;
        }
        
        if (cnt[0] % 2 == 0) {
            return cnt[1] >= 1 && cnt[2] >= 1;
        } else {
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }
    }
    
    public static void main(String[] args) {
        StoneGameIX solution = new StoneGameIX();
        
        System.out.println(solution.stoneGameIX(new int[]{2, 1}));          // true
        System.out.println(solution.stoneGameIX(new int[]{2}));             // false
        System.out.println(solution.stoneGameIX(new int[]{5, 1, 2, 4, 3})); // false
    }
}