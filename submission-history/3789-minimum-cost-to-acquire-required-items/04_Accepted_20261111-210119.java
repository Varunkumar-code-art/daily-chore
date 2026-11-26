class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long both = Math.min(costBoth, cost1 + cost2);
        long common = Math.min(need1, need2);
        long ans = common * both;

        if (need1 > need2) ans += (need1 - need2) * (long) Math.min(cost1, costBoth);
        else ans += (need2 - need1) * (long) Math.min(cost2, costBoth);

        return ans;
    }
}